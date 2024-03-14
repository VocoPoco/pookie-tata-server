package com.pookietata.hacktues.repositories;

import com.pookietata.hacktues.models.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    List<Challenge> findByStartTime(Date startDate);

    // Find challenges within a specific date range
    List<Challenge> findByStartTimeBetween(Date startDate, Date endDate);

    // Find challenges by difficulty level, assuming there's a 'difficultyLevel' field

    List<Challenge> findByRewardValue(Integer rewardValue);
}
