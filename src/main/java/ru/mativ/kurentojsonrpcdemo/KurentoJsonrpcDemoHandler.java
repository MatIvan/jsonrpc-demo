package ru.mativ.kurentojsonrpcdemo;

import org.kurento.jsonrpc.DefaultJsonRpcHandler;
import org.kurento.jsonrpc.Session;
import org.kurento.jsonrpc.Transaction;
import org.kurento.jsonrpc.message.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

public class KurentoJsonrpcDemoHandler extends DefaultJsonRpcHandler<JsonObject> {
	private static Logger log = LoggerFactory.getLogger(KurentoJsonrpcDemoHandler.class);

	@Override
	public void handleRequest(Transaction transaction, Request<JsonObject> request) throws Exception {

		Session kurentoSession = transaction.getSession();

		log.info("[SESSION] SessionId: " + kurentoSession.getSessionId());
		log.info("[SESSION] RegisterInfo: " + kurentoSession.getRegisterInfo());
		log.info("[SESSION] isNew: " + kurentoSession.isNew());
		log.info("[SESSION] Attributes: " + kurentoSession.getAttributes());

		// TODO setReconnectionTimeout
		// TODO close

		log.info("[REQUEST] id:" + request.getId());
		log.info("[REQUEST] method:" + request.getMethod());
		log.info("[REQUEST] params:" + request.getParams());

		transaction.sendResponse(request.getParams());

	}
}
