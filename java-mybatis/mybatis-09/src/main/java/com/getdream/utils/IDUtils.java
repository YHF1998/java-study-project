package com.getdream.utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IDUtils {

    public static String getId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Test
    public void test() {
        System.out.println(IDUtils.getId());
        System.out.println(IDUtils.getId());
        System.out.println(IDUtils.getId());

        Map map = new HashMap();
    }
}
