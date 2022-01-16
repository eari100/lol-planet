package com.lolplanet.demo.api.riot.dto.match;

import com.lolplanet.demo.domain.participant.Participant;
import lombok.Getter;

@Getter
public class ParticipantReqDto {
    private Integer assists;
    private Integer baronKills;
    private Integer bountyLevel;
    private Integer champExperience;
    private Integer champLevel;
    private Integer championId;
    private String championName;
    private Integer championTransform;
    private Integer consumablesPurchased;
    private Integer damageDealtToBuildings;
    private Integer damageDealtToObjectives;
    private Integer damageDealtToTurrets;
    private Integer damageSelfMitigated;
    private Integer deaths;
    private Integer detectorWardsPlaced;
    private Integer doubleKills;
    private Integer dragonKills;
    private Boolean firstBloodAssist;
    private Boolean firstBloodKill;
    private Boolean firstTowerAssist;
    private Boolean firstTowerKill;
    private Boolean gameEndedInEarlySurrender;
    private Boolean gameEndedInSurrender;
    private Integer goldEarned;
    private Integer goldSpent;
    private String individualPosition;
    private Integer inhibitorKills;
    private Integer inhibitorTakedowns;
    private Integer inhibitorsLost;
    private Integer item0;
    private Integer item1;
    private Integer item2;
    private Integer item3;
    private Integer item4;
    private Integer item5;
    private Integer item6;
    private Integer itemsPurchased;
    private Integer killingSprees;
    private Integer kills;
    private String lane;
    private Integer largestCriticalStrike;
    private Integer largestKillingSpree;
    private Integer largestMultiKill;
    private Integer longestTimeSpentLiving;
    private Integer magicDamageDealt;
    private Integer magicDamageDealtToChampions;
    private Integer magicDamageTaken;
    private Integer neutralMinionsKilled;
    private Integer nexusKills;
    private Integer nexusTakedowns;
    private Integer nexusLost;
    private Integer objectivesStolen;
    private Integer objectivesStolenAssists;
    private Integer participantId;
    private Integer pentaKills;
    private PerksReqDto perks;
    private Integer physicalDamageDealt;
    private Integer physicalDamageDealtToChampions;
    private Integer physicalDamageTaken;
    private Integer profileIcon;
    private String puuid;
    private Integer quadraKills;
    private String riotIdName;
    private String riotIdTagline;
    private String role;
    private Integer sightWardsBoughtInGame;
    private Integer spell1Casts;
    private Integer spell2Casts;
    private Integer spell3Casts;
    private Integer spell4Casts;
    private Integer summoner1Casts;
    private Integer summoner1Id;
    private Integer summoner2Casts;
    private Integer summoner2Id;
    private String summonerId;
    private Integer summonerLevel;
    private String summonerName;
    private Boolean teamEarlySurrendered;
    private Integer teamId;
    private String teamPosition;
    private Integer timeCCingOthers;
    private Integer timePlayed;
    private Integer totalDamageDealt;
    private Integer totalDamageDealtToChampions;
    private Integer totalDamageShieldedOnTeammates;
    private Integer totalDamageTaken;
    private Integer totalHeal;
    private Integer totalHealsOnTeammates;
    private Integer totalMinionsKilled;
    private Integer totalTimeCCDealt;
    private Integer totalTimeSpentDead;
    private Integer totalUnitsHealed;
    private Integer tripleKills;
    private Integer trueDamageDealt;
    private Integer trueDamageDealtToChampions;
    private Integer trueDamageTaken;
    private Integer turretKills;
    private Integer turretTakedowns;
    private Integer turretsLost;
    private Integer unrealKills;
    private Integer visionScore;
    private Integer visionWardsBoughtInGame;
    private Integer wardsKilled;
    private Integer wardsPlaced;
    private Boolean win;

    public Participant toEntity() {
        return Participant.builder()
                .championId(championId)
                .teamId(teamId)
                .summoner1Id(summoner1Id)
                .summoner2Id(summoner2Id)
                .item0(item0)
                .item1(item1)
                .item2(item2)
                .item3(item3)
                .item4(item4)
                .item5(item5)
                .item6(item6)
                .kills(kills)
                .deaths(deaths)
                .assists(assists)
                .champLevel(champLevel)
                .totalMinionsKilled(totalMinionsKilled)
                .neutralMinionsKilled(neutralMinionsKilled)
                .visionWardsBoughtInGame(visionWardsBoughtInGame)
                .win(win)
                .summonerName(summonerName)
                .summonerId(summonerId)
                .participantId(participantId)
                .build();
    }
}
