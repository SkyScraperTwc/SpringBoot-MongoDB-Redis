package indi.twc.boot.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisTemplateConfiguration {

    private Logger logger = LoggerFactory.getLogger(RedisTemplateConfiguration.class);

    @Bean
    public Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer(ObjectMapper objectMapper) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }


    @Bean
    public RedisTemplate<String, Object> initRedisTemplate(@Qualifier("connectionFactory") JedisConnectionFactory connectionFactory,
               Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer){
        logger.info("RedisTemplate注入开始:");
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();

        redisTemplate.setConnectionFactory(connectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        redisTemplate.setDefaultSerializer(stringRedisSerializer);

//        redisTemplate.setKeySerializer(stringRedisSerializer);
//
//        redisTemplate.setValueSerializer(stringRedisSerializer);
//
//        redisTemplate.setHashKeySerializer(stringRedisSerializer);
//
//        redisTemplate.setHashValueSerializer(stringRedisSerializer);

        return redisTemplate;
    }

}
