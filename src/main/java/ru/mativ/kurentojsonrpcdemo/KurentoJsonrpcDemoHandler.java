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

	/**
	 * Обработка входящих сообщений
	 */
	@Override
	public void handleRequest(Transaction transaction, Request<JsonObject> request) throws Exception {

	    // Информация о сессии
		Session kurentoSession = transaction.getSession();

		log.info("========== REQUEST ==========");
		log.info("[SESSION] isNew: {}", 		kurentoSession.isNew());
		log.info("[SESSION] SessionId: {}", 	kurentoSession.getSessionId());
		log.info("[SESSION] RegisterInfo: {}", 	kurentoSession.getRegisterInfo());
		log.info("[SESSION] Attributes: {}", 	kurentoSession.getAttributes());

		// Информация от запросе
		log.info("[REQUEST] id: {}", 		request.getId());      // id запроса от клиента
		log.info("[REQUEST] method: {}", 	request.getMethod());  // команда
		log.info("[REQUEST] params: {}", 	request.getParams());  // параметры json

		// TODO about Transaction methods;

		// Ответ. Для примера возвращаем входящие данные.
		transaction.sendResponse(request.getParams());
	}

	/**
	 * Вызывается при подключении пового клиента
	 */
	@Override
	public void afterConnectionEstablished(Session session) throws Exception {
		log.info("========== ESTABLISHED ==========");
		log.info("[SESSION] SessionId: {}", session.getSessionId());
		session.setReconnectionTimeout(15000);
	}

	/**
	 * Вызавается при обрыве соединения
	 * String status - причина отключения.
	 */
	@Override
	public void afterConnectionClosed(Session session, String status) throws Exception {
		log.info("========== CLOSED ==========");
		log.info("[STATUS] {}", status);
		log.info("[SESSION] SessionId: {}", session.getSessionId());
	}

	/**
	 * Вызывается когда клиент подключился после разрыва связи (не новый клиент)
	 */
	@Override
	public void afterReconnection(Session session) throws Exception {
		log.info("========== RECONNECTION ==========");
		log.info("[SESSION] SessionId: {}", session.getSessionId());
	}
}
