package cn.bxd.sip.bxd.service.impl;

import cn.bxd.sip.bxd.model.dto.HSyncEvn;
import cn.bxd.sip.bxd.service.IExceptionService;
import org.springframework.stereotype.Service;

/**
 * @author:tangliang
 * @date:2018/7/16
 * @description:
 */
@Service
public class ExceptionService implements IExceptionService {
    @Override
    public int insertException(HSyncEvn model) {
        int res = 0;
        /*TException exceModel = new TException();
        exceModel.setCreatedTime(new Date());
        exceModel.setExcSeqId(common.getNewId("t_rhip_exception"));
        exceModel.setExcTypeId((short)1);
        exceModel.setHandleTime(new Date());
        exceModel.setMsgSeqId(model.getSyncSeqId());
        exceModel.setRetries((short)3);
        exceModel.setHandleNotes("调用接口错误3次");
        exceModel.setHandlerName("接口名");
        res += exceMapper.insert(exceModel);
        TExceptionPending excePending = new TExceptionPending();
        excePending.setExcSeqId(common.getNewId("t_rhip_exception_pending"));
        res += excePendingMapper.insert(excePending);*/
        return res;
    }
}
