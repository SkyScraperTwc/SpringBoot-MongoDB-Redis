package indi.twc.boot.redis.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

@Component
public class RedisCacheUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 删除缓存<br>
     * 根据key精确匹配删除
     * @param key
     */
    public void delete(String... key){
        if(key!=null && key.length > 0){
            if(key.length == 1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(Arrays.asList(key));
            }
        }
    }


    /**
     * 将value对象写入缓存
     * @param key
     * @param value
     * @param time 失效时间
     */
    public void setValueWithExpire(String key, Object value, ExpireTime time){
        redisTemplate.opsForValue().set(key, value.toString());
        if(time.getTimeOut() > 0){
            redisTemplate.expire(key, time.getTimeOut(), time.getTimeUnit());
        }
    }

    /**
     * 将value对象写入缓存
     * @param key
     * @param value
     */
    public void set(String key, Object value){
        redisTemplate.opsForValue().set(key, value.toString());
    }

    /**
     * 取得缓存（字符串类型）
     * @param key
     * @return
     */
    public String get(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }

    /**
     * 递减操作
     * @param key
     * @param by
     * @return
     */
    public double decr(String key, double by){
        return redisTemplate.opsForValue().increment(key, -by);
    }

    /**
     * 递增操作
     * @param key
     * @param by
     * @return
     */
    public double incr(String key, double by){
        return redisTemplate.opsForValue().increment(key, by);
    }

    /**
     * 将map写入缓存
     * @param key
     * @param map
     * @param time 失效时间
     */
    public <T> void setMapWithExpire(String key, Map<String, Object> map, ExpireTime time){
        redisTemplate.opsForHash().putAll(key, map);
        if(time.getTimeOut() > 0){
            redisTemplate.expire(key, time.getTimeOut(), time.getTimeUnit());
        }
    }

    /**
     * 将map写入缓存
     * @param key
     * @param map
     */
    public <T> void setMap(String key, Map<String, Object> map){
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 获取map缓存
     * @param key
     * @return
     */
    public Map<String, Object> getMap(String key){
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        Map<String, Object> map = hashOperations.entries(key);
        return map;
    }

    /**
     * 指定缓存的失效时间
     *
     * @param key 缓存KEY
     * @param time 失效时间(秒)
     */
    public void expire(String key, ExpireTime time) {
        if(time.getTimeOut() > 0){
            redisTemplate.expire(key, time.getTimeOut(), time.getTimeUnit());
        }
    }
}
