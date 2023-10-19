package com.noti.api.impl.action;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.base.Throwables;
import com.noti.api.impl.domain.SendTaskModel;
import com.noti.common.enums.RespStatusEnum;
import com.noti.service.api.enums.BusinessCode;
import com.noti.support.mq.SendMqService;
import com.noti.support.pipeline.BusinessProcess;
import com.noti.support.pipeline.ProcessContext;
import com.noti.common.vo.BasicResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author xhyyxl
 * 将消息发送到MQ
 */
@Slf4j
@Service
public class SendMqAction implements BusinessProcess<SendTaskModel> {


    @Autowired
    private SendMqService sendMqService;

    @Value("${notipush.business.topic.name}")
    private String sendMessageTopic;

    @Value("${notipush.business.recall.topic.name}")
    private String notipushRecall;
    @Value("${notipush.business.tagId.value}")
    private String tagId;

    @Value("${notipush.mq.pipeline}")
    private String mqPipeline;


    @Override
    public void process(ProcessContext<SendTaskModel> context) {
        SendTaskModel sendTaskModel = context.getProcessModel();
        try {
            if (BusinessCode.COMMON_SEND.getCode().equals(context.getCode())) {
                String message = JSON.toJSONString(sendTaskModel.getTaskInfo(), new SerializerFeature[]{SerializerFeature.WriteClassName});
                sendMqService.send(sendMessageTopic, message, tagId);
            } else if (BusinessCode.RECALL.getCode().equals(context.getCode())) {
                String message = JSON.toJSONString(sendTaskModel.getMessageTemplate(), new SerializerFeature[]{SerializerFeature.WriteClassName});
                sendMqService.send(notipushRecall, message, tagId);
            }
        } catch (Exception e) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.SERVICE_ERROR));
            log.error("send {} fail! e:{},params:{}", mqPipeline, Throwables.getStackTraceAsString(e)
                    , JSON.toJSONString(CollUtil.getFirst(sendTaskModel.getTaskInfo().listIterator())));
        }
    }

}
