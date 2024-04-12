package com.auth.system.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 6为验证码工具类
 */
@Component
public class RandomCode {
    public static String generateverificationCode(){
        int min = 100000;
        int max = 999999;
        int randomCode = ThreadLocalRandom.current().nextInt(min,max + 1);
        return String.format("%06d",randomCode);
    }
}
