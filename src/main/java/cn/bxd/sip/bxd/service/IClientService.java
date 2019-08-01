package cn.bxd.sip.bxd.service;

import cn.bxd.sip.bxd.model.dto.PayScanResData;

import java.io.IOException;

/**
 * @author:tangliang
 * @date:2018/8/8
 * @description:
 */
public interface IClientService {
    PayScanResData cancel(String requestNo,String reason) throws IllegalAccessException, IOException;
}
