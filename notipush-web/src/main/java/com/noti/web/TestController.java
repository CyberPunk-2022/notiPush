package com.noti.web;

import com.alibaba.fastjson.JSON;
import com.noti.service.api.domain.MessageParam;
import com.noti.service.api.domain.SendRequest;
import com.noti.service.api.domain.SendResponse;
import com.noti.service.api.enums.BusinessCode;
import com.noti.service.api.service.SendService;
import com.noti.support.dao.MessageTemplateDao;
import com.noti.support.domain.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class TestController {


    @Autowired
    private MessageTemplateDao messageTemplateDao;
    @Autowired
    private SendService sendService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/redis")
    private String testRedis() {
        stringRedisTemplate.opsForValue().set("xhyyxl", "notipush");
        return stringRedisTemplate.opsForValue().get("xhyyxl");
    }

    @RequestMapping("/test")
    private String test() {
        System.out.println("sout:我真的是醉了，这都没人给三连吗？");
        log.info("log:我真的是醉了，这都没人给三连吗？");
        return "请给我三连好吗？";
    }

    @RequestMapping("/database")
    private String testDataBase() {
        List<MessageTemplate> list = messageTemplateDao.findAllByIsDeletedEquals(0, PageRequest.of(0, 10));
        return JSON.toJSONString(list);
    }

    @RequestMapping("/send")
    private String testSend() {
        SendRequest sendRequest = SendRequest.builder()
                .code(BusinessCode.COMMON_SEND.getCode())
                .messageTemplateId(1L)
                .messageParam(MessageParam.builder().receiver("13722222222").build()).build();

        SendResponse response = sendService.send(sendRequest);
        return JSON.toJSONString(response);

    }
}