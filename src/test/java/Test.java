package indi.twc.boot.redis;
import com.alibaba.fastjson.JSON;
import indi.twc.boot.mongodb.entity.UserEntity;
import indi.twc.boot.redis.utils.ExpireTime;
import indi.twc.boot.redis.utils.RedisCacheUtils;

public class Test {
    public static void main(String[] args) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassWord("123456");
        userEntity.setUserName("twc");

        String str = "v2";

        System.out.println(JSON.toJSONString(str));

    }

}
