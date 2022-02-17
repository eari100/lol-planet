package com.lolplanet.demo.domain.summoner;

import java.util.Optional;

public interface SummonerRepositoryCustom {
    Optional<Summoner> findByNameWithBlankRemoved(String nameWithBlankRemoved);
}
