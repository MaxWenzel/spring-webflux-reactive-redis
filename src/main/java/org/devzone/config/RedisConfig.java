package org.devzone.config;

import org.devzone.model.Address;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.*;

@Configuration
public class RedisConfig {

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;

    @Bean
    @Primary
    public ReactiveRedisConnectionFactory factory() {
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public ReactiveRedisTemplate<String, Address> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {

        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Address> valueSerializer = new Jackson2JsonRedisSerializer<>(Address.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, Address> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);
        RedisSerializationContext<String, Address> context =
                builder.value(valueSerializer).build();
        return new ReactiveRedisTemplate<>(factory, context);
    }

    @Bean
    public ReactiveKeyCommands keyCommands(final ReactiveRedisConnectionFactory factory) {
        return factory.getReactiveConnection()
                .keyCommands();
    }

    @Bean
    public ReactiveRedisConnection reactiveRedisConnection(final ReactiveRedisConnectionFactory factory) {
        return factory.getReactiveConnection();
    }

}