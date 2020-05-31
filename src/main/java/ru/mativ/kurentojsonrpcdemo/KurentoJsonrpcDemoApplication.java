package ru.mativ.kurentojsonrpcdemo;

import org.kurento.jsonrpc.internal.server.config.JsonRpcConfiguration;
import org.kurento.jsonrpc.server.JsonRpcConfigurer;
import org.kurento.jsonrpc.server.JsonRpcHandlerRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(JsonRpcConfiguration.class)
public class KurentoJsonrpcDemoApplication implements JsonRpcConfigurer {

	@Override
	public void registerJsonRpcHandlers(JsonRpcHandlerRegistry registry) {
		registry.addHandler(new KurentoJsonrpcDemoHandler(), "/jsonrpc");
	}

	public static void main(String[] args) {
		SpringApplication.run(KurentoJsonrpcDemoApplication.class, args);
	}

}
