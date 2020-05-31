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

		log.debug("[SESSION] SessionId: " + kurentoSession.getSessionId());
		log.debug("[SESSION] RegisterInfo: " + kurentoSession.getRegisterInfo());
		log.debug("[SESSION] isNew: " + kurentoSession.isNew());
		log.debug("[SESSION] Attributes: " + kurentoSession.getAttributes());

		// TODO setReconnectionTimeout
		// TODO close

		log.debug("[REQUEST] id:" + request.getId());
		log.debug("[REQUEST] method:" + request.getMethod());
		log.debug("[REQUEST] params:" + request.getParams());

		transaction.sendResponse(request.getParams());

	}
}
