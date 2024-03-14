package com.pookietata.hacktues.repositories;

import com.pookietata.hacktues.models.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {

    List<Reward> findByChallengeId(Long challengeId);
    List<Reward> findByRewardValue(Integer rewardValue);

}
