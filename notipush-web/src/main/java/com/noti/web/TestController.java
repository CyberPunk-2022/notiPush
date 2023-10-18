package com.noti.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {


    @RequestMapping("/test")
    private String test() {
        System.out.println("sout:我真的是醉了，这都没人给三连吗？");
        log.info("log:我真的是醉了，这都没人给三连吗？");
        return "请给我三连好吗？";
    }
}