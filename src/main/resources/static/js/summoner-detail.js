const summoner_detail = {
    init: function () {
        $( "#tabs" ).tabs()

        const summonerName = $('#summonerName').val()
        findSummonerInfo()
        findMatchList(0)

        $('#btn-renew').click(function (e){
            e.preventDefault()
            let l = Ladda.create(this)
            l.start()
            l.setProgress(0.3)

            renew(l)
        })

        function findSummonerInfo() {
            $.ajax({
                type: 'GET',
                url: `/lol/summoner/v4/summoners/by-name/${summonerName}`,
                dataType: 'json'
            }).done(function (summoner) {
                renderSummonerEl(summoner)
            }).fail(function (error) {
                alert(JSON.stringify(error))
            })
        }

        function renderSummonerEl(summoner) {
            $('.summoner_profile').html(`<img src="https://ddragon.leagueoflegends.com/cdn/12.2.1/img/profileicon/${summoner.profileIconId}.png" class="profile_img">`)
            $('.summoner_name').html(`<h1>${summoner.name}</h1>`)
            $('.summoner_level').html(`LV.${summoner.summonerLevel}`)
        }

        function findMatchList(start) {
            $.ajax({
                type: 'GET',
                url: `/lol/participant/list?start=${start}&count=20&summonerName=${summonerName}`,
                dataType: 'json'
            }).done(function (res) {
                res.content.forEach(match => renderMatchEl(match))
            }).fail(function (error) {
                alert(JSON.stringify(error))
            })
        }

        function renderMatchEl(match) {

            let gameResultCss = ''
            if(match.gameResult == '승리')
                gameResultCss = 'Win'
            else if(match.gameResult == '패배')
                gameResultCss = 'Lose'

            $('.MatchesList').append(
                `<div class="MatchWrap">
                    <div class="GameItem ${gameResultCss}">
                        <div class="Content">
                            <div class="GameStats">
                                <div class="GameType">${match.mapName}</div>
                                <div class="TimeStamp">
                                    <span class="_timeago _timeCountAssigned tip">${match.gameCreationToDate}</span>
                                </div>
                                <div class="Bar"></div>
                                <div class="GameResult">${match.gameResult}</div>
                                <div class="GameLength">${match.gameDurationToString}</div>
                            </div>
                            <div class="GameSettingInfo">
                                <div class="ChampionImage">
                                    <img src="//opgg-static.akamaized.net/images/lol/champion/${match.playedChampionName}.png?image=c_scale,q_auto,w_46&amp;v=1642648372">
                                </div>
                                <div class="SummonerSpell">
                                    <div class="Spell">
                                        <img src="//opgg-static.akamaized.net/images/lol/spell/${match.usedSpell1Name}.png?image=c_scale,q_auto,w_22&amp;v=1619585878">
                                    </div>
                                    <div class="Spell">
                                        <img src="//opgg-static.akamaized.net/images/lol/spell/${match.usedSpell2Name}.png?image=c_scale,q_auto,w_22&amp;v=1619585878">
                                    </div>
                                </div>
                                <div class="ChampionName">${match.playedChampionName}</div>
                            </div>
                            <div class="KDA">
                                <div class="KDA">
                                    <span class="Kill">${match.kills}</span> /
                                    <span class="Death">${match.deaths}</span> /
                                    <span class="Assist">${match.assists}</span>
                                </div>
                                <div class="KDARatio">
                                    <span class="KDARatio">${match.kda}</span>
                                </div>
                            </div>
                            <div class="Stats">
                                <div class="Level">레벨 ${match.playedChampLevel}</div>
                                <div class="CS">
                                    <span class="CS tip">${match.totalCs} (${match.csPerMinute})</span> CS
                                </div>
                            </div>
                            <div class="Items">
                                <div class="ItemList">
                                    <div class="Item">
                                        ${match.item0!=0?
                                            `<img src="//opgg-static.akamaized.net/images/lol/item/${match.item0}.png?image=q_auto:best&amp;v=1619585878" class="Image tip">`:
                                            `<div class="Image NoItem"></div>`
                                        }
                                    </div>
                                    <div class="Item">
                                        ${match.item1!=0?
                                            `<img src="//opgg-static.akamaized.net/images/lol/item/${match.item1}.png?image=q_auto:best&amp;v=1619585878" class="Image tip">`:
                                            `<div class="Image NoItem"></div>`
                                        }
                                    </div>
                                    <div class="Item">
                                        ${match.item2!=0?
                                            `<img src="//opgg-static.akamaized.net/images/lol/item/${match.item2}.png?image=q_auto:best&amp;v=1619585878" class="Image tip">`:
                                            `<div class="Image NoItem"></div>`
                                        }
                                    </div>
                                    <div class="Item">
                                        ${match.item6!=0?
                                            `<img src="//opgg-static.akamaized.net/images/lol/item/${match.item6}.png?image=q_auto:best&amp;v=1619585878" class="Image tip">`:
                                            `<div class="Image NoItem"></div>`
                                        }
                                    </div>
                                    <div class="Item">
                                        ${match.item3!=0?
                                            `<img src="//opgg-static.akamaized.net/images/lol/item/${match.item3}.png?image=q_auto:best&amp;v=1619585878" class="Image tip">`:
                                            `<div class="Image NoItem"></div>`
                                        }
                                    </div>
                                    <div class="Item">
                                        ${match.item4!=0?
                                            `<img src="//opgg-static.akamaized.net/images/lol/item/${match.item4}.png?image=q_auto:best&amp;v=1619585878" class="Image tip">`:
                                            `<div class="Image NoItem"></div>`
                                        }
                                    </div>
                                    <div class="Item">
                                        ${match.item5!=0?
                                            `<img src="//opgg-static.akamaized.net/images/lol/item/${match.item5}.png?image=q_auto:best&amp;v=1619585878" class="Image tip">`:
                                            `<div class="Image NoItem"></div>`
                                        }
                                    </div>   
                                </div>
                                <div class="Trinket">
                                    <img src="//opgg-static.akamaized.net/images/site/summoner/icon-ward-blue.png">
                                    제어 와드 <span class="wards vision">${match.visionWardsBoughtInGame}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>`)
        }

        function renew(ladda) {
           $.ajax({
               type: 'POST',
               url: `/lol/summoner/renew/by-name/${summonerName}`,
               success: function () {
                   ladda.setProgress(0.9)
               }
           }).done(function () {
               $('.MatchesList').empty()
               findSummonerInfo()
               findMatchList(0)
           }).fail(function (error) {
               alert(JSON.stringify(error))
           }).always(function() {
               ladda.stop();
           })
       }
    }
}

summoner_detail.init()