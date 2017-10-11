package indi.twc.boot.redis;
import com.alibaba.fastjson.JSON;
import indi.twc.boot.mongodb.entity.User;

public class Test {
    public static void main(String[] args) {
        User user = new User();
        user.setPassWord("123456");
        user.setUserName("twc");

        String str = "v2";

        System.out.println(JSON.toJSONString(str));

    }

}
