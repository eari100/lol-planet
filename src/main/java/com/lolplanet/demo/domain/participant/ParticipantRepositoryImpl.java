package com.lolplanet.demo.domain.participant;

import com.lolplanet.demo.web.dto.MatchListBySummonerResDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import static com.lolplanet.demo.domain.participant.QParticipant.participant;

@RequiredArgsConstructor
public class ParticipantRepositoryImpl implements ParticipantRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public Page<MatchListBySummonerResDto> findBySummonerId(Pageable pageable, String summonerId) {

        List<Participant> entity = queryFactory.selectFrom(participant)
                .where(participant.id.summonerId.eq(summonerId))
                .orderBy(participant.id.match.gameCreation.desc())
                .offset(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .fetch();

        List<MatchListBySummonerResDto> dto = entity.stream()
                .map(MatchListBySummonerResDto::new)
                .collect(Collectors.toList());

        return new PageImpl(dto, pageable, entity.size());
    }
}
