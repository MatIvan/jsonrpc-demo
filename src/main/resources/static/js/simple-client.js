var ws = null;

window.onload = function() {
    startSocket();
}

function startSocket() {
    if (ws) {
        ws.close();
    }

    ws = new SockJS('/jsonrpc', null, {
        transports : 'websocket'
    });

    ws.onopen = function() {
        console.log("onopen");
    };

    ws.onclose = function(event) {
        console.log("onclose: ", event);
    };

    ws.onmessage = function(message) {
        console.log("onmessage: ", message);
    };

}

function sendTextMessage(text) {
    ws.send('{"jsonrpc": "2.0", "method": "textMessage", "params": {"text":"'+ text + '"}, "id": 1}');
}

function closeSocket() {
    ws.close();
}

function reconnect() {
    startSocket();
}
