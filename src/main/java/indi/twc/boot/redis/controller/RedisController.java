package indi.twc.boot.redis.controller;

import indi.twc.boot.mongodb.entity.UserEntity;
import indi.twc.boot.redis.utils.ExpireTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/RedisController")
public class RedisController {
    Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @RequestMapping("/addUserEntity")
    public void addUserEntity() throws Exception {
        UserEntity userEntity = new UserEntity();

        userEntity.setUserName("hhh");
        userEntity.setPassWord("ppp");

        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();

        valueOperations.set(userEntity.getUserName(), userEntity);
    }

    @RequestMapping("/testSet")
    public void testSet(@RequestParam("key") String key, @RequestParam("value") String value) throws Exception {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);

    }

    @RequestMapping("/testGet")
    public String testGet(@RequestParam("key") String key) throws Exception {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        String result = (String) valueOperations.get(key);
        System.out.println(result);
        return result;
    }

    @RequestMapping("/testHashSet")
    public void testHashSet() throws Exception {
        HashOperations<String,Object,Object> hashOperations = redisTemplate.opsForHash();
        //添加 一个 hash集合
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "lp");
        map.put("age", "26");
        hashOperations.putAll("lpMap",map);
    }

    @RequestMapping("/testHashGet")
    public Map<String, Object> testHashGet(@RequestParam("key") String key) throws Exception {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        //获取 map
        Map<String, Object> map = hashOperations.entries("lpMap");
        System.out.println(map);
        return map;
    }

    @RequestMapping("/testExpire")
    public void testExpire(@RequestParam("key") String key, @RequestParam("value") String value) throws Exception {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
        redisTemplate.expire(key, 20, TimeUnit.SECONDS);
//        System.out.println(redisTemplate.getExpire(key));

    }

    @RequestMapping("/testExpire2")
    public void testExpire2() throws Exception {
    }

}
