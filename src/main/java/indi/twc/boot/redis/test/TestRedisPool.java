package indi.twc.boot.redis.test;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.net.UnknownHostException;
import java.util.Set;

public class TestRedisPool {
    private Jedis jedisMaster;

    private Jedis jedisSlaver;

    private JedisPool jedisPool;

    private Jedis jedis;

    @Before
    public void before() throws UnknownHostException {
        jedisMaster = new Jedis("127.0.0.1",6379);
        jedisSlaver = new Jedis("127.0.0.1",6380);

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(1000);
        poolConfig.setMaxIdle(32);
        poolConfig.setMaxWaitMillis(1000);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);

        //配置jedisPool
        jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
    }

    @Test
    public void testSlave() throws UnknownHostException {
        jedisSlaver.slaveof("127.0.0.1",6379);
        jedisMaster.set("class","7788123");
        System.out.println(jedisSlaver.get("class"));
    }

    @Test
    public void testKeys() throws UnknownHostException {
        jedis = jedisPool.getResource();
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        System.out.println(keys.getClass());

    }

    @Test
    public void testRedisPool() throws UnknownHostException {
        jedis = jedisPool.getResource();
        String result = jedis.get("k4");
        System.out.println(result);
    }

}
