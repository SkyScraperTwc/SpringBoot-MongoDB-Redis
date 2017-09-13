package indi.twc.boot.redis.test;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.net.UnknownHostException;
import java.util.Map;

public class RedisRun {
    private Jedis jedis;

    @Before
    public void before() throws UnknownHostException {
        jedis = new Jedis("127.0.0.1",6379);
    }

    @Test
    public void testPing() throws UnknownHostException {
        System.out.println(jedis.ping());
    }

    @Test
    public void testString() throws UnknownHostException {
        jedis.select(0);
        System.out.println(jedis.get("k1"));
    }

    @Test
    public void testHash() throws UnknownHostException {
        jedis.select(2);
        Map<String, String> map = jedis.hgetAll("user");
        for (Map.Entry<String, String> entry: map.entrySet()) {
            System.out.println(entry.getKey()+"--"+entry.getValue());
        }
        jedis.hset("user","age","8");
    }

    @Test
    public void testTransaction() throws UnknownHostException {
        Transaction transaction = jedis.multi();

        transaction.set("k44","v44");
        transaction.set("k55","v55");

        transaction.exec();
//        transaction.discard();
    }

}
