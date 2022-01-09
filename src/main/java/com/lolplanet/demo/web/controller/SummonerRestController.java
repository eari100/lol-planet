package com.lolplanet.demo.web.controller;

import com.lolplanet.demo.service.SummonerService;
import com.lolplanet.demo.web.dto.SummonerResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lol/summoner")
public class SummonerRestController {

    private final SummonerService summonerService;

    @GetMapping("/v4/summoners/by-name/{summonerName}")
    public SummonerResDto findByName(@PathVariable("summonerName") String summonerName) {
        return summonerService.findByName(summonerName);
    }
}
