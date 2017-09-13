package indi.twc.boot.mongodb.test;

import com.google.gson.Gson;

import java.util.Date;

public class TestGson {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Date date = new Date();
        System.out.println(date);

        String str = gson.toJson(date);
        System.out.println(str);
    }
}
