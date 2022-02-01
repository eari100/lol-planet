const main = {
    init: function () {
        const _this = this
        $('#summonerName').keydown(function(key) {
            if (key.keyCode == 13) {
                _this.findByName()
                return false
            }
        })
    },
    findByName : function() {
        const name = $('#summonerName').val()
        window.location.href = `/userName=${name}`
    }
}

main.init()