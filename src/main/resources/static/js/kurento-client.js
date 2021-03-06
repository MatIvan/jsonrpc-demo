
var jsonRpcClientWs = null; // клиент
var mySessionId; // id текущей/последней сесии.

window.onload = function() {

    // Настройки подключения
	var configuration = {
		heartbeat: 10000,		// Время в миллисекундах между ping-pong сообщениями. Если не указать, то пинги будут отключены.
		sendCloseMessage: true, // true / false посылать сообщение "closeSession" перед отключением или просто оборвать связь. (сервер сразу узнает об отключении или по пингу)
		ws: {
			uri: 'https://' + location.host + '/jsonrpc', 	// URI до сервера
			useSockJS: true, 								// true (использовать SockJS) / false (использовать WebSocket)
			onconnected: connectCallback, 					// callback на подлючение
			ondisconnect: disconnectCallback,				// callback на обрыв связи
			onreconnecting: reconnectingCallback,			// callback на старт повторной попытки подключиться
			onreconnected: reconnectedCallback,				// callback переподключение удалось
			onerror: errorCallback							// callback на ошибку
		},
		rpc: {
			requestTimeout: 10000,       // время в миллисекундах на отправку запроса. (через какое время придет ошибка, если сервер не доступен)
			myOwnMethod: myOwnMethodCall // определяем свой метод
		}
	}

	// Создание подключения
	jsonRpcClientWs = new RpcBuilder.clients.JsonRpcClient(configuration);
};

window.onbeforeunload = function() {
	jsonRpcClientWs.close();
}

function connectCallback() {
	console.log("connect");
	sendConnect();
}

function disconnectCallback() {
	console.log("disconnect");
}

function reconnectingCallback() {
	console.log("REconnectING");
}

function reconnectedCallback() {
	console.log("REconnectED");
	sendConnect();
}

function errorCallback(error) {
	console.log("error: ", error);
}

// отправить текстовое сообщение на сервер
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

// закрыть соккет
// вызывается нажатием кнопки на странице
function closeSocket() {
	console.log("closeSocket");
	jsonRpcClientWs.close();
}

// переподключиться
// вызывается нажатием кнопки на странице
function reconnect() {
	console.log("reconnect");
	jsonRpcClientWs.reconnect();
}

// Функция отправляет на севрвер команду подключения с уже имеющимся ИД сессии.
function sendConnect(){
    var method = "connect";
    var params = {};

    if(mySessionId){
        params = {
            sessionId: mySessionId
        };
    }

    jsonRpcClientWs.send(method, params, function(error, response) {
        mySessionId = undefined;
        if (error) {
            console.log("error:", error);
            return;
        }
        console.log("response:", response);
        if (response.value == "OK"){
            mySessionId = response.sessionId;
        }
    });

}

// Свой метод.
function myOwnMethodCall(params, request){
    console.log("==== myOwnMethodCall ====");
    console.log("params: ", params);
    console.log("request: ", request);
}