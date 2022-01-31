package com.lolplanet.demo.web.controller;

import com.lolplanet.demo.service.MatchService;
import com.lolplanet.demo.web.dto.MatchResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lol/match")
public class MatchRestController {
    private final MatchService matchService;

    @GetMapping("/list")
    public Page<MatchResDto> findList(@RequestParam(name = "start") int start, @RequestParam(name = "count") int count, @RequestParam(name = "summonerName") String summonerName) {
        return matchService.findList(PageRequest.of(start, count), summonerName);
    }
}
