package com.lolplanet.demo.web.controller;

import com.lolplanet.demo.service.ParticipantService;
import com.lolplanet.demo.web.dto.MatchListBySummonerResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lol/participant")
public class ParticipantRestController {
    private final ParticipantService participantService;

    @GetMapping("/list")
    public Page<MatchListBySummonerResDto> findBySummonerName(@RequestParam(name = "start") int start, @RequestParam(name = "count") int count, @RequestParam(name = "summonerName") String summonerName) {
        return participantService.findBySummonerName(PageRequest.of(start, count), summonerName);
    }
}
