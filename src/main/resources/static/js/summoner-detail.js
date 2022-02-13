const summoner_detail = {
    init: function () {

        const summonerName = $('#summonerName').val()
        let summonerId

        findSummonerInfo(initRederTabsEl)

        function findSummonerInfo(callback) {
            $.ajax({
                type: 'GET',
                url: `/lol/summoner/v4/summoners/by-name/${summonerName}`,
                dataType: 'json'
            }).done(function (res) {
                if(res.errorStatus === null) {
                    renderSummonerEl(res.data)
                    summonerId = res.data.id
                    if(callback) callback()
                } else
                    $(document.body).append(`<h1>소환사 검색 오류 입니다. 오타를 확인해주세요.</h1>`)
            }).fail(function (error) {
                alert(JSON.stringify(error))
            })
        }

        function renderSummonerEl(summoner) {
            $('.summoner_info_box').html(
                `<div className="summoner_profile">
                    <img src="https://ddragon.leagueoflegends.com/cdn/12.2.1/img/profileicon/${summoner.profileIconId}.png" class="profile_img">
                </div>
                <div>
                    <div className="summoner_name">
                        <h1>${summoner.name}</h1>
                    </div>
                    <div className="summoner_level">
                        LV.${summoner.summonerLevel}
                    </div>
                </div>`
            )
        }

        function initRederTabsEl() {
            $('.tabs_box').html(
                `<div id="tabs">
                    <ul>
                        <li><a href="#tabs-review">리뷰</a></li>
                        <li><a href="#tabs-match">전적</a></li>
                    </ul>
                    <div id="tabs-review">
                        <p>준비 중 입니다...</p>
                    </div>
                    <div id="tabs-match">
                        <button id="btn-renew" class="btn btn-primary ladda-button Button Blue" data-style="expand-right" data-size="s">
                            <span class="ladda-label">업데이트</span>
                        </button>
                        <div class="MatchesList"></div>
                    </div>
                </div>`
            )
            $( "#tabs" ).tabs()
            renew(false, 0)
            $('#btn-renew').click(function (e){
                e.preventDefault()
                let l = Ladda.create(this)
                l.start()
                l.setProgress(0.3)

                renew(l, 0)
            })
        }

        function findMatchList(start) {
            $('.GameMoreButton.Box').remove()

            $.ajax({
                type: 'GET',
                url: `/lol/participant/list?start=${start}&count=20&summonerId=${summonerId}`,
                dataType: 'json'
            }).done(function (res) {
                res.content.forEach(match => renderMatchEl(match))

                const contentLen = res.content.length
                if(contentLen === 20) {
                    renderGameMoreBtn()
                    $('#btn-gameMore').click(function (e){
                        e.preventDefault()
                        let l = Ladda.create(this)
                        l.start()
                        l.setProgress(0.3)

                        renew(l, start+20)
                    })
                }
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

        function renderGameMoreBtn() {
            $('#tabs-match').append(
            `<div class="GameMoreButton Box">
                <a class="Button ladda-button" id="btn-gameMore" data-style="slide-right" data-size="s">더 보기</a>
            </div>`)
        }

        function renew(ladda, start) {
           $.ajax({
               type: 'POST',
               url: `/lol/summoner/renew/by-name/${summonerName}?start=${start}&count=20`,
               success: function () {
                   if(ladda) ladda.setProgress(0.9)
               }
           }).done(function () {
               if(start===0) $('.MatchesList').empty() // 업데이트 시 최근의 20건만 나타난다
               findSummonerInfo()
               findMatchList(start)
           }).fail(function (error) {
               alert(JSON.stringify(error))
           }).always(function() {
               if(ladda) ladda.stop();
           })
       }
    }
}

summoner_detail.init()