var stompClient = null;
var sessionId = null;
var socket = null;
var isConnected = false;
$(document).ready(function() {
//    socket = new SockJS('http://localhost:8080/livescore-websocket');
    socket = new SockJS('https://decode.pagekite.me/livescore-websocket');
    sessionId = (Math.random() * (10000000 - 1) + 1).toString();
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function(frame) {
        stompClient.subscribe('/topic/livescore-' + sessionId, function(scoredata) {

            var obj = jQuery.parseJSON(scoredata.body);
            $("#ta2").val(obj.result);
        });
        isConnected = true;
    });
    $('#buttonCode').click(function(event) {
        event.preventDefault();
        // sendDateToSocket(sessionId);
        // getData(stompClient);
        if (isConnected) {
            var data = $('#ta').val();
            console.log("this is client");
            stompClient.send("/app/scorecard", {},
                JSON.stringify({
                    'code': data,
                    'sessionId': sessionId,
                })
            );
        }
    });
});

