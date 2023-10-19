package com.noti.service.api.service;


import com.noti.service.api.domain.SendRequest;
import com.noti.service.api.domain.SendResponse;

/**
 * 撤回接口
 *
 * @author xhyyxl
 */
public interface RecallService {


    /**
     * 根据模板ID撤回消息
     *
     * @param sendRequest
     * @return
     */
    SendResponse recall(SendRequest sendRequest);
}
