
var jsonRpcClientWs = null;

window.onload = function() {

	var configuration = {
		hearbeat: 1000,			//interval in ms for each heartbeat message. If has any value, the ping-pong will work with the interval
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
			requestTimeout: 1000, 		//timeout for a request
			//sessionStatusChanged: 	//callback method for changes in session status,
			//mediaRenegotiation: 		//mediaRenegotiation
		}
	}

	jsonRpcClientWs = new RpcBuilder.clients.JsonRpcClient(configuration);

};

window.onbeforeunload = function() {
	jsonRpcClientWs.close();
}

function connectCallback() {
	console.log("connect");
	sendTextMessage("hello!")
}

function disconnectCallback() {
	console.log("disconnect");
}

function reconnectingCallback() {
	console.log("REconnectING");
}

function reconnectedCallback() {
	console.log("REconnectED");
	sendTextMessage("hello Agayn !")
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
