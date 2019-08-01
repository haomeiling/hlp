package cn.bxd.sip.his.controller;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.pay.BillDownService;
import cn.bxd.sip.bxd.service.IConnectParm;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.model.dto.platform.HosInfoReq;
import cn.bxd.sip.his.model.dto.platform.R;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 接收平台来的请求
 * Package: cn.bxd.sip.his.controller
 *
 * @author Leeves
 * @version 1.0.0  2018-08-23
 */
@Slf4j
@RestController
public class BGReceiveController {

    @Autowired
    private IConnectParm iTRhipConnectParm;
    
    @Autowired
    private BillDownService billDownService;

    /**
     * 接收平台更新ws请求
     */
    @PostMapping("/platform/hosMap/update")
    public R updateHosMap(@Validated @RequestBody HosInfoReq hosInfoReq, BindingResult bindingResult) {
        //判断入参
        if (bindingResult.hasErrors()) {
            return R.resultError(bindingResult.getFieldError().getDefaultMessage());
        }

        int hosId = hosInfoReq.getHosId();
        ConnectParm tRhipConnectParm = iTRhipConnectParm.getHosConnectParamByHosId(hosId);

        if (tRhipConnectParm == null) {
            return R.resultError("没有此医院信息");
        }

        Map<String, ConnectParm> allHosWsMap = HisConvertConst.allHosWsMap;
        Map<String, Object> hosWsMap = new HashMap<>();
        if (tRhipConnectParm.getWsUrl() != null) {
            hosWsMap.put(HisConvertConst.WS_CLIENT_KEY_HIS_USER, HisWSClient.getHisWSClientSoap(tRhipConnectParm.getHisUserSoap(),String.valueOf(tRhipConnectParm.getWsUrl())));
        }
        if (tRhipConnectParm.getWsUrl2() != null) {
            hosWsMap.put(HisConvertConst.WS_CLIENT_KEY_HIS_COM, HisWSClient.getHisWSClientSoap(tRhipConnectParm.getHisComSoap(),String.valueOf(tRhipConnectParm.getWsUrl2())));
        }
        if (tRhipConnectParm.getWsUrl3() != null) {
            hosWsMap.put(HisConvertConst.WS_CLIENT_KEY_PAY, HisWSClient.getHisWSClientSoap(tRhipConnectParm.getPaySoap(),String.valueOf(tRhipConnectParm.getWsUrl3())));
        }
        if (tRhipConnectParm.getWsUrl4() != null) {
            hosWsMap.put(HisConvertConst.WS_CLIENT_KEY_REG, HisWSClient.getHisWSClientSoap(tRhipConnectParm.getRegSoap(),String.valueOf(tRhipConnectParm.getWsUrl4())));
        }
        if (tRhipConnectParm.getWsUrl5() != null) {
            hosWsMap.put(HisConvertConst.WS_CLIENT_KEY_IN_HOS, HisWSClient.getHisWSClientSoap(tRhipConnectParm.getInHosSoap(),String.valueOf(tRhipConnectParm.getWsUrl5())));
        }
        tRhipConnectParm.setHosWsClientMap(hosWsMap);

        allHosWsMap.put(String.valueOf(hosId), tRhipConnectParm);

        log.info("allHosWsMap:" + allHosWsMap);

        return R.ok();
    }
    
    @RequestMapping(value = "/downBill", method = RequestMethod.POST)
    @ResponseBody
    public String syncBillData(@RequestParam String startTime, @RequestParam String endTime,
    		@RequestParam Integer hospitalId) {
    	return billDownService.down(hospitalId, startTime, endTime);
    }

}