const summoner_detail = {
    init: function () {
        $( "#tabs" ).tabs()

        const summonerName = $('#summonerName').val()
        findSummonerInfo(summonerName)

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

    }
}

summoner_detail.init()