    package com.pookietata.hacktues.services;

    import com.pookietata.hacktues.Dtos.MatchDto;
    import com.pookietata.hacktues.Dtos.ParticipantDto;
    import com.pookietata.hacktues.models.PersonalChallenge;
    import com.pookietata.hacktues.models.User;
    import com.pookietata.hacktues.models.enums.ChallengeStatus;
    import com.pookietata.hacktues.repositories.PersonalChallengeRepository;
    import com.pookietata.hacktues.repositories.UserRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.http.HttpEntity;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.HttpMethod;
    import org.springframework.http.ResponseEntity;
    import org.springframework.scheduling.annotation.Scheduled;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.web.client.RestTemplate;
    import java.util.Arrays;
    import java.util.Date;
    import java.util.List;

    @Service
    public class ChallengeService {

        @Autowired
        private PersonalChallengeRepository personalChallengeRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private RestTemplate restTemplate;

        @Value("${riot.api.key}")
        private String riotApiKey;

        @Value("${riot.api.url}")
        private String riotApiUrl;

        @Transactional
        @Scheduled(fixedRate = 300000) // 5 minutes in milliseconds
        public void checkChallengeCompletion() {
            // Fetch challenges that are either upcoming or active
            List<PersonalChallenge> challenges = personalChallengeRepository.findByStatusIn(
                    Arrays.asList(ChallengeStatus.UPCOMING, ChallengeStatus.ACTIVE));

            for (PersonalChallenge challenge : challenges) {
                // Check if challenge has expired
                if (challenge.getEndDate().before(new Date())) {
                    challenge.setStatus(ChallengeStatus.EXPIRED);
                    personalChallengeRepository.save(challenge);
                    continue;
                }

                // Check if the challenge is active and not yet completed
                if (challenge.getStatus().equals(ChallengeStatus.ACTIVE) && !challenge.getCompleted()) {
                    User user = challenge.getUser();
                    if (user != null) {
                        boolean isCompleted = checkIfChallengeIsCompleted(user.getPUUID(), challenge);
                        if (isCompleted) {
                            completeChallenge(challenge, user);
                        }
                    }
                }

                personalChallengeRepository.save(challenge);
            }
        }


        private boolean checkIfChallengeIsCompleted(String puuid, PersonalChallenge challenge) {
            long startTimeInSeconds = challenge.getStartDate().getTime() / 1000;
            String matchListUrl = String.format("%s/lol/match/v5/matches/by-puuid/%s/ids?startTime=%d", riotApiUrl, puuid, startTimeInSeconds);

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Riot-Token", riotApiKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String[]> response = restTemplate.exchange(matchListUrl, HttpMethod.GET, entity, String[].class);
            if (response.getBody() != null && response.getBody().length > 0) {
                int totalStatValue = 0;
                for (String matchId : response.getBody()) {
                    String matchUrl = String.format("%s/lol/match/v5/matches/%s", riotApiUrl, matchId);
                    ResponseEntity<MatchDto> matchResponse = restTemplate.exchange(matchUrl, HttpMethod.GET, entity, MatchDto.class);
                    MatchDto matchDto = matchResponse.getBody();

                    if (matchDto != null && matchDto.getInfo() != null) {
                        for (ParticipantDto participant : matchDto.getInfo().getParticipants()) {
                            if (puuid.equals(participant.getPuuid()) && participant.getStats() != null) {
                                totalStatValue += participant.getStats().getStatValue(challenge.getChallengeGoal());
                            }
                        }
                    }
                }
                return totalStatValue >= challenge.getGoalValue();
            }
            return false;
        }


        private void completeChallenge(PersonalChallenge challenge, User user) {
            challenge.setCompleted(true);
            challenge.setStatus(ChallengeStatus.EXPIRED);
            user.setCurrencyBalance(user.getCurrencyBalance() + challenge.getReward());
            userRepository.save(user);
            personalChallengeRepository.save(challenge);
        }
    }