package com.lolplanet.demo.domain.summoner;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.lolplanet.demo.domain.summoner.QSummoner.summoner;

@RequiredArgsConstructor
public class SummonerRepositoryImpl implements SummonerRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Summoner> findByNameWithBlankRemoved(String nameWithBlankRemoved) {

        Optional<Summoner> entity = Optional.ofNullable(queryFactory.selectFrom(summoner)
                .where(Expressions.stringTemplate("replace({0},' ','')", summoner.name).eq(nameWithBlankRemoved))
                .fetchOne());

        return entity;
    }
}
