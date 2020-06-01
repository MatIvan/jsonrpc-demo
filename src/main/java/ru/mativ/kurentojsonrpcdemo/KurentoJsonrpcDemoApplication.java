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
         * Дополнительные настройки.
         * Можно управлять в файле kurento.conf.json
         * <параметр>                       , <значение по умолчанию>
         * "ws.sessionReconnectionTime"     , 10
         * "ws.maxSessions"                 , Long.MAX_VALUE
         * "jsonRpcServerWebSocket.timeout" , 10000
         */
    }

    @Bean
    public JsonRpcHandler<?> kurentoJsonrpcDemoHandler() {
        KurentoJsonrpcDemoHandler handler = new KurentoJsonrpcDemoHandler();

        handler
                .withSockJS()               // Использовать SockJS
                .withLabel("DEMO_LABEL")    // Метка сокета. Выводится в логах с ошибками.
                .withAllowedOrigins("*")    // Разрешенные узлы для подключения
                .withPingWatchdog(false);    // Включить пинги в подключениях

        return handler;
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {

        // Если в течении этого времени нет активности в сесии, то подключение закрывается.
        final long maxSessionIdleTimeout = 10000l;

        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxSessionIdleTimeout(maxSessionIdleTimeout);
        return container;
    }

    public static void main(String[] args) {
        SpringApplication.run(KurentoJsonrpcDemoApplication.class, args);
    }

}
