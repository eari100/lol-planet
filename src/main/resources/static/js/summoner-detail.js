const summoner_detail = {
    init: function () {
        $( "#tabs" ).tabs()

        const summonerName = $('#summonerName').val()
        findSummonerInfo()
        findMatchList()

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
                url: '/lol/summoner/v4/summoners/by-name/' + summonerName,
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

        function findMatchList() {
            $.ajax({
                type: 'GET',
                url: '/lol/match/list?start=0&count=20',
                dataType: 'json'
            }).done(function (res) {
                res.content.forEach(match => renderMatchEl(match))
            }).fail(function (error) {
                alert(JSON.stringify(error))
            })
        }

        function renderMatchEl(match) {
            $('.MatchesList').append(
                `<div class="MatchWrap">
                    <div class="GameItem">
                        <div class="Content">
                            <div class="GameStats">
                                <div class="GameType">${match.mapName}</div>
                            </div>
                        </div>
                    </div>
                </div>`)
        }

        function renew(ladda) {
           $.ajax({
               type: 'POST',
               url: '/lol/summoner/renew/by-name/' + summonerName,
               success: function () {
                   ladda.setProgress(0.9)
               }
           }).done(function () {
               $('.MatchesList').empty()
               findSummonerInfo()
               findMatchList()
           }).fail(function (error) {
               alert(JSON.stringify(error))
           }).always(function() {
               ladda.stop();
           })
       }
    }
}

summoner_detail.init()