package com.lolplanet.demo.service;

import com.lolplanet.demo.domain.participant.ParticipantRepository;
import com.lolplanet.demo.web.dto.MatchListBySummonerResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    @Transactional(readOnly = true)
    public Page<MatchListBySummonerResDto> findBySummonerName(Pageable pageable, String summonerName) {
        return participantRepository.findBySummonerName(pageable, summonerName);
    }
}
