$(function () {
    $('#searchbox').keypress(function (e) {
        var key = e.which; //e.which是按键的值
        if (key == 13) {
            var q = $.trim($(this).val());
            if (q && q != '') {
                window.location.href = '/search?keyword=' + encodeURIComponent(q);
            }
        }
    });
});

function search() {
    var q = $.trim($('#searchbox').val());
    if (q && q != '') {
        window.location.href = '/search?keyword=' + encodeURIComponent(q);
    }
}
