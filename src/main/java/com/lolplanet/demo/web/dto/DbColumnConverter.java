package com.lolplanet.demo.web.dto;

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
}
