package com.lolplanet.demo.domain.match;

import com.lolplanet.demo.web.dto.MatchResDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import static com.lolplanet.demo.domain.match.QMatch.match;
import static com.lolplanet.demo.domain.participant.QParticipant.participant;

@RequiredArgsConstructor
public class MatchRepositoryImpl implements MatchRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MatchResDto> findList(Pageable pageable, String summonerName) {
        List<Match> entity = queryFactory.selectFrom(match)
                .leftJoin(match.participants, participant).fetchJoin()
                .orderBy(match.gameCreation.desc(), participant.participantId.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<MatchResDto> dto = entity.stream()
                .map(m -> new MatchResDto(m, summonerName))
                .collect(Collectors.toList());

        return new PageImpl<>(dto, pageable, dto.size());
    }
}
