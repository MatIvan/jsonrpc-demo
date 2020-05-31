
var jsonRpcClientWs = null;

window.onload = function() {

	var configuration = {
		heartbeat: 10000,			//interval in ms for each heartbeat message. If has any value, the ping-pong will work with the interval
		sendCloseMessage: true, //true / false, before closing the connection, it sends a closeSession message
		ws: {
			uri: 'https://' + location.host + '/jsonrpc', 	//URI to conntect to
			useSockJS: true, 								//true (use SockJS) / false (use WebSocket) by default
			onconnected: connectCallback, 					//callback method to invoke when connection is successful
			ondisconnect: disconnectCallback,				//callback method to invoke when the connection is lost
			onreconnecting: reconnectingCallback,			//callback method to invoke when the client is reconnecting
			onreconnected: reconnectedCallback,				//callback method to invoke when the client succesfully reconnects
			onerror: errorCallback							//callback method to invoke when there is an error
		},
		rpc: {
			requestTimeout: 5000, 		//timeout for a request
			heartbeatRequestTimeout: 10000,
		}
	}

	jsonRpcClientWs = new RpcBuilder.clients.JsonRpcClient(configuration);
};

window.onbeforeunload = function() {
	jsonRpcClientWs.close();
}

function connectCallback() {
	console.log("connect");
}

function disconnectCallback() {
	console.log("disconnect");
}

function reconnectingCallback() {
	console.log("REconnectING");
}

function reconnectedCallback() {
	console.log("REconnectED");
}

function errorCallback(error) {
	console.log("error: ", error);
}

function sendTextMessage(textMsg) {
	var method = "text-message";
	var params = {
		text: textMsg
	};

	jsonRpcClientWs.send(method, params, function(error, response) {
		if (error) {
			console.log("error:", error);
			return;
		}
		console.log("response:", response);
	});
}

function closeSocket() {
	console.log("closeSocket");
	jsonRpcClientWs.close();
}

function reconnect() {
	console.log("reconnect");
	jsonRpcClientWs.reconnect();
}
