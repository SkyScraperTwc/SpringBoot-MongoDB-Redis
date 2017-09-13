package indi.twc.boot.redis.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

public class ExpireTime {

    /**
     * 超时时间
     */
    private static final Integer TIME_OUT = 20;

    /**
     * 时间种类(分钟)
     */
    private static final TimeUnit TIME_UNIT = TimeUnit.MINUTES;

    public static Integer getTimeOut() {
        return TIME_OUT;
    }

    public static TimeUnit getTimeUnit() {
        return TIME_UNIT;
    }
}
