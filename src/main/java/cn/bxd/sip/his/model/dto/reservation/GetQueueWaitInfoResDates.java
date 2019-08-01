package cn.bxd.sip.his.model.dto.reservation;

import java.util.List;

/**
 * Description:
 * User: LiSheng
 * Date: 2018-11-01
 * Time: 15:16
 */
public class GetQueueWaitInfoResDates{
   private List<GetQueueWaitInfoRes> waitNumList;//son字符串
   private String resultCode;//状态码00：已取号，01：未取号， 02：已过期
   private String resultMsg;//返回信息

    public List<GetQueueWaitInfoRes> getWaitNumList() {
        return waitNumList;
    }

    public void setWaitNumList(List<GetQueueWaitInfoRes> waitNumList) {
        this.waitNumList = waitNumList;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
