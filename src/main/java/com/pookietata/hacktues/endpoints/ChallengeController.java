package com.pookietata.hacktues.endpoints;

import com.pookietata.hacktues.Dtos.ChallengeDto;
import com.pookietata.hacktues.services.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @PostMapping("/check-completion")
    public ResponseEntity<String> manuallyCheckChallengeCompletion() {
        challengeService.checkChallengeCompletion();
        return ResponseEntity.ok("Challenge completion check triggered.");
    }
//    @GetMapping("/user/{userId}/challenges")
//    public ResponseEntity<List<ChallengeDto>> getChallengesForUser(@PathVariable("userId") Long userId) {
//        // Logic to fetch challenges for the user
//        return ResponseEntity.ok(/* list of challenges for the user */);
//    }
}
