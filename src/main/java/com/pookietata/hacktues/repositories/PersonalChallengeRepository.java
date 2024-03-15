package com.pookietata.hacktues.repositories;

import com.pookietata.hacktues.models.PersonalChallenge;
import com.pookietata.hacktues.models.enums.ChallengeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalChallengeRepository extends JpaRepository<PersonalChallenge, Long> {
    List<PersonalChallenge> findByStatusIn(List<ChallengeStatus> statuses);
}
