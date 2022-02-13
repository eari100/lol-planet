package com.lolplanet.demo.domain.match;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.lolplanet.demo.domain.match.QMatch.match;
import static com.lolplanet.demo.domain.participant.QParticipant.participant;

@RequiredArgsConstructor
public class MatchRepositoryImpl implements MatchRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Match> findList(Pageable pageable, String summonerName) {
        List<Match> matchList = queryFactory.selectFrom(match)
                .leftJoin(match.participants, participant).fetchJoin()
                .orderBy(match.gameCreation.desc(), participant.participantId.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return matchList;
    }
}
