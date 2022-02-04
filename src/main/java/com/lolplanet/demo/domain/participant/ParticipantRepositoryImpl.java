package com.lolplanet.demo.domain.participant;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.lolplanet.demo.domain.participant.QParticipant.participant;

@RequiredArgsConstructor
public class ParticipantRepositoryImpl implements ParticipantRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public Page<Participant> findBysummonerId(Pageable pageable, String summonerId) {

        ParticipantId id = new ParticipantId();
        id.setSummonerId(summonerId);

        List<Participant> entity = queryFactory.selectFrom(participant)
                .where(participant.id.summonerId.eq(summonerId))
                .orderBy(participant.id.match.gameCreation.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl(entity, pageable, entity.size());
    }
}
