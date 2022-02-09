package com.lolplanet.demo.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lolplanet.demo.service.MatchService;
import com.lolplanet.demo.service.SummonerService;
import com.lolplanet.demo.web.dto.DefaultResDto;
import com.lolplanet.demo.web.dto.SummonerResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lol/summoner")
public class SummonerRestController {

    private final SummonerService summonerService;
    private final MatchService matchService;

    @GetMapping("/v4/summoners/by-name/{summonerName}")
    public ResponseEntity findByName(@PathVariable("summonerName") String summonerName) throws JsonProcessingException {
        return new ResponseEntity(summonerService.findByName(summonerName), HttpStatus.OK);
    }

    @PostMapping("/renew/by-name/{summonerName}")
    public void renew(@PathVariable("summonerName") String summonerName) {
        SummonerResDto summonerResDto = summonerService.update(summonerName);
        matchService.renew(summonerResDto.getPuuid(), 0, 20);
    }
}
