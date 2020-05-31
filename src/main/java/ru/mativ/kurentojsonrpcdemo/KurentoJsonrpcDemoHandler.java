package ru.mativ.kurentojsonrpcdemo;

import org.kurento.jsonrpc.DefaultJsonRpcHandler;
import org.kurento.jsonrpc.Transaction;
import org.kurento.jsonrpc.message.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

public class KurentoJsonrpcDemoHandler extends DefaultJsonRpcHandler<JsonObject> {
	private static Logger log = LoggerFactory.getLogger(KurentoJsonrpcDemoHandler.class);

	@Override
	public void handleRequest(Transaction transaction, Request<JsonObject> request) throws Exception {

		log.debug("Request id:" + request.getId());
		log.debug("Request method:" + request.getMethod());
		log.debug("Request params:" + request.getParams());

		transaction.sendResponse(request.getParams());

	}
}
