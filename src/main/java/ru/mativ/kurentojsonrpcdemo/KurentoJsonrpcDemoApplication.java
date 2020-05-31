package ru.mativ.kurentojsonrpcdemo;

import org.kurento.jsonrpc.JsonRpcHandler;
import org.kurento.jsonrpc.internal.server.config.JsonRpcConfiguration;
import org.kurento.jsonrpc.server.JsonRpcConfigurer;
import org.kurento.jsonrpc.server.JsonRpcHandlerRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@SpringBootApplication
@Import(JsonRpcConfiguration.class)
public class KurentoJsonrpcDemoApplication implements JsonRpcConfigurer {

	@Override
	public void registerJsonRpcHandlers(JsonRpcHandlerRegistry registry) {
		registry.addHandler(kurentoJsonrpcDemoHandler(), "/jsonrpc");

		/*
		 * TODO kjrserver.conf.json
		 * "ws.sessionReconnectionTime", 10
		 * "ws.maxSessions", Long.MAX_VALUE
		 * "jsonRpcServerWebSocket.timeout", 10000
		 */
	}

	@Bean
	public JsonRpcHandler<?> kurentoJsonrpcDemoHandler() {
		KurentoJsonrpcDemoHandler handler = new KurentoJsonrpcDemoHandler();

		handler
		.withSockJS()
		.withLabel("DEMO_LABEL")
		.withAllowedOrigins("*")
		.withPingWatchdog(true);

		// TODO handler.withInterceptors(interceptors)

		return handler;
	}

//	  @Bean
//	  public ServletServerContainerFactoryBean createWebSocketContainer() {
//	    ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
//	    container.setMaxSessionIdleTimeout(10000l);
//	    return container;
//	  }

	public static void main(String[] args) {
		SpringApplication.run(KurentoJsonrpcDemoApplication.class, args);
	}

}
