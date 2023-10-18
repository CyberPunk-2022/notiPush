package com.noti.service.api.service;


import com.noti.service.api.domain.BatchSendRequest;
import com.noti.service.api.domain.SendRequest;
import com.noti.service.api.domain.SendResponse;

/**
 * 发送接口
 *
 * @author 3y
 */
public interface SendService {

    /**
     * 单文案发送接口
     *
     * @param sendRequest
     * @return
     */
    SendResponse send(SendRequest sendRequest);


    /**
     * 多文案发送接口
     *
     * @param batchSendRequest
     * @return
     */
    SendResponse batchSend(BatchSendRequest batchSendRequest);

}
