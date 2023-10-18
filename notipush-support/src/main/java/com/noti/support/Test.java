package com.noti.support;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        try {
            LombokTest lombokTest1 = LombokTest.builder().id("1").name("能给我三连吗").build();
            LombokTest lombokTest2 = LombokTest.builder().id("2").name("对我真的很重要").build();
            List<LombokTest> lombokTests = Arrays.asList(lombokTest1, lombokTest2);
            if (CollUtil.isNotEmpty(lombokTests)) {
                System.out.println(JSON.toJSONString(lombokTests));
            }
        } catch (Exception e) {
            System.out.println(Throwables.getStackTraceAsString(e));

        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class LombokTest{
        private String name;
        private String id;
    }

}