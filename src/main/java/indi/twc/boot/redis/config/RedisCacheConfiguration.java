package indi.twc.boot.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisCacheConfiguration {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;

    @Value("${spring.redis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.database}")
    private int database;

    private Logger logger = LoggerFactory.getLogger(RedisCacheConfiguration.class);

    @Bean
    public JedisPoolConfig initJedisPoolConfig(){
        logger.info("JedisPoolConfig注入开始:");
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setBlockWhenExhausted(true);

        return poolConfig;
    }

    @Bean
    public JedisPool initJedisPool(@Autowired JedisPoolConfig poolConfig){
        logger.info("JedisPool注入开始:");
        JedisPool jedisPool = new JedisPool(poolConfig, host, port, timeout);
        return jedisPool;
    }

    @Bean(name = "connectionFactory")
    public JedisConnectionFactory initJedisConnectionFactory(@Autowired JedisPoolConfig poolConfig){
        logger.info("JedisConnectionFactory注入开始:");

        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setPort(port);
        connectionFactory.setHostName(host);
        connectionFactory.setTimeout(timeout);
        connectionFactory.setUsePool(true);
        connectionFactory.setPoolConfig(poolConfig);
        connectionFactory.setDatabase(database);

        return connectionFactory;
    }

}
