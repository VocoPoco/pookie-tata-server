package com.pookietata.hacktues.services;

import com.pookietata.hacktues.models.PersonalChallenge;
import com.pookietata.hacktues.models.User;
import com.pookietata.hacktues.models.enums.ChallengeStatus;
import com.pookietata.hacktues.repositories.PersonalChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ChallengeService {

    @Autowired
    private PersonalChallengeRepository personalChallengeRepository;

    @Transactional
    @Scheduled(fixedRate = 300000) // 5 minutes in milliseconds
    public void checkChallengeCompletion() {
        List<PersonalChallenge> challenges = personalChallengeRepository.findByStatusIn(Arrays.asList(ChallengeStatus.UPCOMING, ChallengeStatus.ACTIVE));

        for (PersonalChallenge challenge : challenges) {
            if (challenge.getEndDate().before(new Date())) {
                challenge.setStatus(ChallengeStatus.EXPIRED);
                personalChallengeRepository.save(challenge);
                continue;
            }

            if (challenge.getStatus().equals(ChallengeStatus.ACTIVE) && !challenge.getCompleted()) {
                // Implement logic to check challenge completion here.
                // This is a placeholder; replace it with actual logic.
                boolean isCompleted = checkIfChallengeIsCompleted(challenge);

                if (isCompleted) {
                    challenge.setCompleted(true);
                    challenge.setStatus(ChallengeStatus.EXPIRED);

                    // Update user's currency balance or rewards
                    User user = challenge.getUser();
                    if (user != null) {
                        user.setCurrencyBalance(user.getCurrencyBalance() + challenge.getReward());
                        personalChallengeRepository.save(challenge); // Save changes to the challenge
                        // Save changes to the user if necessary, assuming userRepository exists and is autowired.
                    }
                }
            }
        }
    }

    // This method is a placeholder. You should implement it according to your application's logic.
    private boolean checkIfChallengeIsCompleted(PersonalChallenge challenge) {
        // Your logic here. For now, let's assume every challenge automatically completes.
        return true;
    }
}
