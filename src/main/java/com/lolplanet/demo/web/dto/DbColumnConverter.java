package com.lolplanet.demo.web.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// https://developer.riotgames.com/docs/lol#data-dragon
public class DbColumnConverter {

    // http://static.developer.riotgames.com/docs/lol/queues.json
    public String convertToMapName(Integer queueId) {
        String mapName = "";

        if(queueId==420) mapName = "솔랭";
        else if(queueId==430) mapName = "일반";
        else if(queueId==440) mapName = "자유랭";
        else if(queueId==450) mapName = "무작위 총력전";
        else if(queueId==1020) mapName = "단일 챔피언";
        else mapName = "특수 모드";

        return mapName;
    }

    public String convertToGameCreationDate(Long gameCreation) {
        SimpleDateFormat sdf = new SimpleDateFormat( "yy-MM-dd" , Locale.KOREA );
        return sdf.format(new Date(gameCreation));
    }

    public String convertToGameResult(Boolean win) {
        return win ? "승리" : "패배";
    }

    public String convertToGameDurationMinutesSeconds(Long gameDuration) {
        return String.format("%d분%2d초", gameDuration/60, gameDuration%60);
    }

    /* http://ddragon.leagueoflegends.com/cdn/11.9.1/data/en_US/champion.json
     * update : v12.2.1
     */
    public String convertToChampionName(Integer championId) {
        String championName = "";

        switch (championId) {
            case 266: championName = "Aatrox"; break;
            case 103: championName = "Ahri"; break;
            case 84: championName = "Akali"; break;
            case 166: championName = "Akshan"; break;
            case 12: championName = "Alistar"; break;
            case 32: championName = "Amumu"; break;
            case 34: championName = "Anivia"; break;
            case 1: championName = "Annie"; break;
            case 523: championName = "Aphelios"; break;
            case 22: championName = "Ashe"; break;
            case 136: championName = "AurelionSol"; break;
            case 268: championName = "Azir"; break;
            case 432: championName = "Bard"; break;
            case 53: championName = "Blitzcrank"; break;
            case 63: championName = "Brand"; break;
            case 201: championName = "Braum"; break;
            case 51: championName = "Caitlyn"; break;
            case 164: championName = "Camille"; break;
            case 69: championName = "Cassiopeia"; break;
            case 31: championName = "Chogath"; break;
            case 42: championName = "Corki"; break;
            case 122: championName = "Darius"; break;
            case 131: championName = "Diana"; break;
            case 119: championName = "Draven"; break;
            case 36: championName = "DrMundo"; break;
            case 245: championName = "Ekko"; break;
            case 60: championName = "Elise"; break;
            case 28: championName = "Evelynn"; break;
            case 81: championName = "Ezreal"; break;
            case 9: championName = "Fiddlesticks"; break;
            case 114: championName = "Fiora"; break;
            case 105: championName = "Fizz"; break;
            case 3: championName = "Galio"; break;
            case 41: championName = "Gangplank"; break;
            case 86: championName = "Garen"; break;
            case 150: championName = "Gnar"; break;
            case 79: championName = "Gragas"; break;
            case 104: championName = "Graves"; break;
            case 887: championName = "Gwen"; break;
            case 120: championName = "Hecarim"; break;
            case 74: championName = "Heimerdinger"; break;
            case 420: championName = "Illaoi"; break;
            case 39: championName = "Irelia"; break;
            case 427: championName = "Ivern"; break;
            case 40: championName = "Janna"; break;
            case 59: championName = "JarvanIV"; break;
            case 24: championName = "Jax"; break;
            case 126: championName = "Jayce"; break;
            case 202: championName = "Jhin"; break;
            case 222: championName = "Jinx"; break;
            case 145: championName = "Kaisa"; break;
            case 429: championName = "Kalista"; break;
            case 43: championName = "Karma"; break;
            case 30: championName = "Karthus"; break;
            case 38: championName = "Kassadin"; break;
            case 55: championName = "Katarina"; break;
            case 10: championName = "Kayle"; break;
            case 141: championName = "Kayn"; break;
            case 85: championName = "Kennen"; break;
            case 121: championName = "Khazix"; break;
            case 203: championName = "Kindred"; break;
            case 240: championName = "Kled"; break;
            case 96: championName = "KogMaw"; break;
            case 7: championName = "Leblanc"; break;
            case 64: championName = "LeeSin"; break;
            case 89: championName = "Leona"; break;
            case 876: championName = "Lillia"; break;
            case 127: championName = "Lissandra"; break;
            case 236: championName = "Lucian"; break;
            case 117: championName = "Lulu"; break;
            case 99: championName = "Lux"; break;
            case 54: championName = "Malphite"; break;
            case 90: championName = "Malzahar"; break;
            case 57: championName = "Maokai"; break;
            case 11: championName = "MasterYi"; break;
            case 21: championName = "MissFortune"; break;
            case 62: championName = "MonkeyKing"; break;
            case 82: championName = "Mordekaiser"; break;
            case 25: championName = "Morgana"; break;
            case 267: championName = "Nami"; break;
            case 75: championName = "Nasus"; break;
            case 111: championName = "Nautilus"; break;
            case 518: championName = "Neeko"; break;
            case 76: championName = "Nidalee"; break;
            case 56: championName = "Nocturne"; break;
            case 20: championName = "Nunu"; break;
            case 2: championName = "Olaf"; break;
            case 61: championName = "Orianna"; break;
            case 516: championName = "Ornn"; break;
            case 80: championName = "Pantheon"; break;
            case 78: championName = "Poppy"; break;
            case 555: championName = "Pyke"; break;
            case 246: championName = "Qiyana"; break;
            case 133: championName = "Quinn"; break;
            case 497: championName = "Rakan"; break;
            case 33: championName = "Rammus"; break;
            case 421: championName = "RekSai"; break;
            case 526: championName = "Rell"; break;
            case 58: championName = "Renekton"; break;
            case 107: championName = "Rengar"; break;
            case 92: championName = "Riven"; break;
            case 68: championName = "Rumble"; break;
            case 13: championName = "Ryze"; break;
            case 360: championName = "Samira"; break;
            case 113: championName = "Sejuani"; break;
            case 235: championName = "Senna"; break;
            case 147: championName = "Seraphine"; break;
            case 875: championName = "Sett"; break;
            case 35: championName = "Shaco"; break;
            case 98: championName = "Shen"; break;
            case 102: championName = "Shyvana"; break;
            case 27: championName = "Singed"; break;
            case 14: championName = "Sion"; break;
            case 15: championName = "Sivir"; break;
            case 72: championName = "Skarner"; break;
            case 37: championName = "Sona"; break;
            case 16: championName = "Soraka"; break;
            case 50: championName = "Swain"; break;
            case 517: championName = "Sylas"; break;
            case 134: championName = "Syndra"; break;
            case 223: championName = "TahmKench"; break;
            case 163: championName = "Taliyah"; break;
            case 91: championName = "Talon"; break;
            case 44: championName = "Taric"; break;
            case 17: championName = "Teemo"; break;
            case 412: championName = "Thresh"; break;
            case 18: championName = "Tristana"; break;
            case 48: championName = "Trundle"; break;
            case 23: championName = "Tryndamere"; break;
            case 4: championName = "TwistedFate"; break;
            case 29: championName = "Twitch"; break;
            case 77: championName = "Udyr"; break;
            case 6: championName = "Urgot"; break;
            case 110: championName = "Varus"; break;
            case 67: championName = "Vayne"; break;
            case 45: championName = "Veigar"; break;
            case 161: championName = "Velkoz"; break;
            case 711: championName = "Vex"; break;
            case 254: championName = "Vi"; break;
            case 234: championName = "Viego"; break;
            case 112: championName = "Viktor"; break;
            case 8: championName = "Vladimir"; break;
            case 106: championName = "Volibear"; break;
            case 19: championName = "Warwick"; break;
            case 498: championName = "Xayah"; break;
            case 101: championName = "Xerath"; break;
            case 5: championName = "XinZhao"; break;
            case 157: championName = "Yasuo"; break;
            case 777: championName = "Yone"; break;
            case 83: championName = "Yorick"; break;
            case 350: championName = "Yuumi"; break;
            case 154: championName = "Zac"; break;
            case 238: championName = "Zed"; break;
            case 221: championName = "Zeri"; break;
            case 115: championName = "Ziggs"; break;
            case 26: championName = "Zilean"; break;
            case 142: championName = "Zoe"; break;
            case 143: championName = "Zyra"; break;
            default: championName = "undefined"; break;
        }

        return championName;
    }

    /* http://ddragon.leagueoflegends.com/cdn/11.9.1/data/en_US/summoner.json
     * update : v12.2.1
     */
    public String convertToSpellName(Integer spellId) {
        String spellName = "";

        switch (spellId) {
            case 21: spellName = "SummonerBarrier"; break;
            case 1: spellName = "SummonerBoost"; break;
            case 14: spellName = "SummonerDot"; break;
            case 3: spellName = "SummonerExhaust"; break;
            case 4: spellName = "SummonerFlash"; break;
            case 6: spellName = "SummonerHaste"; break;
            case 7: spellName = "SummonerHeal"; break;
            case 13: spellName = "SummonerMana"; break;
            case 30: spellName = "SummonerPoroRecall"; break;
            case 31: spellName = "SummonerPoroThrow"; break;
            case 11: spellName = "SummonerSmite"; break;
            case 39: spellName = "SummonerSnowURFSnowball_Mark"; break;
            case 32: spellName = "SummonerSnowball"; break;
            case 12: spellName = "SummonerTeleport"; break;
            case 54: spellName = "Summoner_UltBookPlaceholder"; break;
            case 55: spellName = "Summoner_UltBookSmitePlaceholder"; break;
            default: spellName = "undefined"; break;
        }

        return spellName;
    }

    public String convertToKda(float kills, float deaths, float assists) {
        return deaths==0 ? "OMG !!!" : String.format("%.2f:1 평점", ((kills+assists)/deaths));
    }

    public String convertToCsPerMinute(float totalCs, float gameDuration) {
        return String.format("%.1f", totalCs/(gameDuration/60));
    }
}
