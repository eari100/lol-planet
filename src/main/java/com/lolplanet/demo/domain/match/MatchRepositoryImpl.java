package com.lolplanet.demo.domain.match;

import com.lolplanet.demo.web.dto.MatchListResDto;
import com.lolplanet.demo.web.dto.MatchResDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.lolplanet.demo.domain.match.QMatch.match;
import static com.lolplanet.demo.domain.participant.QParticipant.participant;

@RequiredArgsConstructor
public class MatchRepositoryImpl implements MatchRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public MatchListResDto findList(int start, int count) {
        List<Match> matches = queryFactory.selectFrom(match)
                .leftJoin(match.participants, participant).fetchJoin()
                .orderBy(match.createdDate.desc())
                .offset(start)
                .limit(count)
                .fetch();

        List<MatchResDto> matchResDtos = matches.stream()
                .map(MatchResDto::new)
                .collect(Collectors.toList());

        return new MatchListResDto(matchResDtos);
    }
}
