package ru.mativ.kurentojsonrpcdemo;

import org.kurento.jsonrpc.JsonRpcHandler;
import org.kurento.jsonrpc.internal.server.config.JsonRpcConfiguration;
import org.kurento.jsonrpc.server.JsonRpcConfigurer;
import org.kurento.jsonrpc.server.JsonRpcHandlerRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(JsonRpcConfiguration.class)
public class KurentoJsonrpcDemoApplication implements JsonRpcConfigurer {

	@Override
	public void registerJsonRpcHandlers(JsonRpcHandlerRegistry registry) {
		registry.addHandler(new KurentoJsonrpcDemoHandler(), "/jsonrpc");
	}

	@Bean
	public JsonRpcHandler<?> kurentoJsonrpcDemoHandler() {
		KurentoJsonrpcDemoHandler handler = new KurentoJsonrpcDemoHandler();

		handler.withSockJS();
		handler.withLabel("DEMO_LABEL");
		handler.withAllowedOrigins("*");
		handler.withPingWatchdog(true);
		// TODO handler.withInterceptors(interceptors)

		return handler;
	}

	public static void main(String[] args) {
		SpringApplication.run(KurentoJsonrpcDemoApplication.class, args);
	}

}
