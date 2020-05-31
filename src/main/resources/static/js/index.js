

window.onload = function() {

	ws = new SockJS('/jsonrpc', null, { transports: 'websocket' });

	ws.onopen = function() {
		console.log("onopen");
		ws.send('{"jsonrpc": "2.0", "method": "textMessage", "params": {"text":"hello !!!"}, "id": 1}');
	};

	ws.onclose = function(event) {
		console.log("onclose: ", event);
	};

	ws.onmessage = function(message) {
		console.log("onmessage: ", message);
	};

}
