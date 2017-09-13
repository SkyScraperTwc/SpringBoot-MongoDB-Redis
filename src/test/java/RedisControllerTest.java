package indi.twc.boot.redis;

import indi.twc.boot.redis.controller.RedisController;
import indi.twc.boot.redis.utils.ExpireTime;
import indi.twc.boot.redis.utils.RedisCacheUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RedisControllerTest {

    @Autowired
    private RedisController redisController;

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Test
    public void testRedisController1() throws Exception {
        System.out.println("*************");
        redisController.testExpire("k1","v1");

    }

    @Test
    public void testRedisController2() throws Exception {
        System.out.println("*************");
        System.out.println( redisController.testHashGet("lpMap"));

    }

    @Test
    public void testRedisController3() throws Exception {
        System.out.println("*************");
        redisController.testSet("mmm","ccc");
    }

    @Test
    public void testRedisCacheUtils() throws Exception {
        System.out.println("*************");
        redisCacheUtils.set("k2","v2");
    }

    @Test
    public void testRedisCacheUtilsMap() throws Exception {
        System.out.println("*************");
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("name", "lp");
//        map.put("age", "26");
        System.out.println(redisCacheUtils.getMap("lpMap"));
    }

    @Test
    public void testValue() throws Exception {
        System.out.println(new ExpireTime().getTimeOut());
        System.out.println(new ExpireTime().getTimeUnit());
    }

}
