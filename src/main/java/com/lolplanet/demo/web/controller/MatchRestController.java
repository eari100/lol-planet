package com.lolplanet.demo.web.controller;

import com.lolplanet.demo.service.MatchService;
import com.lolplanet.demo.web.dto.MatchListReqDto;
import com.lolplanet.demo.web.dto.MatchListResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lol/match")
public class MatchRestController {
    private final MatchService matchService;

    @GetMapping("/list")
    public MatchListResDto findList(@RequestBody MatchListReqDto dto) {
        return matchService.findList(dto.getStart(), dto.getCount());
    }
}
