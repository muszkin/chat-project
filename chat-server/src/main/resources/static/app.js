var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('http://localhost:8080/register');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        $("#welcome-form").show();
        setConnected(true);
        console.log('Connected: ' + frame);
        var sessionId = socket._transport.url.split('/')[5];
        console.log('Session id: ' + sessionId);
        stompClient.subscribe('/topic/private/' + sessionId, function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
    $("#message-form").hide();
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
    $("#welcome-form").hide();
    $("#message-form").show();
}

function sendMessage() {
    stompClient.send("/app/chat", {}, JSON.stringify({'content': $("#message").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#welcome-form").hide();
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#send-message" ).click(function() { sendMessage(); });
});

