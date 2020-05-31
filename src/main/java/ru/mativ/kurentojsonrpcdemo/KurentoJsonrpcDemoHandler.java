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

		log.info("[SESSION] isNew: {}, SessionId: {}", kurentoSession.isNew(), kurentoSession.getSessionId());
		log.info("[SESSION] RegisterInfo: " + kurentoSession.getRegisterInfo());
		log.info("[SESSION] Attributes: " + kurentoSession.getAttributes());

		// TODO setReconnectionTimeout
		// TODO close
		// TODO transaction.startAsync();

		log.info("[REQUEST] id: {}, method: {}, params: {}", request.getId(), request.getMethod(), request.getParams());

		transaction.sendResponse(request.getParams());
	}

	// TODO Other overrides methods
}
