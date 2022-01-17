package com.lolplanet.demo.domain.summoner;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SummonerRepository extends JpaRepository<Summoner, String> {

    Optional<Summoner> findByName(String name);
}
