package com.chat.server;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableSimpleBroker("/topic");
    config.setApplicationDestinationPrefixes("/app");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/register").setAllowedOrigins("*");
  }

  @Override
  public void configureWebSocketTransport(WebSocketTransportRegistration wstr) {
    wstr.setSendTimeLimit(15 * 1000).setSendBufferSizeLimit(512 * 1024);
  }

  @Override
  public void configureClientInboundChannel(ChannelRegistration cr) {
  }

  @Override
  public void configureClientOutboundChannel(ChannelRegistration cr) {
    cr.taskExecutor().corePoolSize(4).maxPoolSize(10);
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {
  }

  @Override
  public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {
  }

  @Override
  public boolean configureMessageConverters(List<MessageConverter> list) {
    return false;
  }

}
