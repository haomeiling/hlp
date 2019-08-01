package cn.bxd.sip.his;

import cn.bxd.sip.bxd.dao.*;
import cn.bxd.sip.bxd.model.entity.SiMedicareRecordsWithBLOBs;
import cn.bxd.sip.bxd.model.entity.SyncEvt;
import cn.bxd.sip.bxd.service.IOrderStatusService;
import cn.bxd.sip.bxd.service.ISeqService;
import cn.bxd.sip.his.job.SynEvtServerJob;
import cn.bxd.sip.his.model.dto.his.DoRegReq;
import cn.bxd.sip.his.model.dto.his.Payment;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author:tangliang
 * @date:2018/7/12
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSyn {

    @Autowired
    private SyncEvtPendingMapper syncPendingMapper;
    @Autowired
    private SysMapper syncMapper;
    @Autowired
    private OrderMapper tRhipOrderMapper;
    @Autowired
    private SynEvtServerJob synEntService;
    @Autowired
    private OrderDetailMapper tRhipOrderDetailMapper;
    @Autowired
    private SyncEvtMapper tSyncEvtMapper;

    @Test
    public void Test(){
        //TRhipOrder QueryOrderRecordsRespond = tRhipOrderMapper.selectByPrimaryKey((long)3961);
        DoRegReq obj = new DoRegReq();
        obj.setIsDopay("1");
        obj.setTerminalCode("1");

        Payment payment = new Payment();
        payment.setHasMPay("a");
        payment.setPayRecord("b");
        payment.setPayType("c");
        payment.setTotalMoney("d");
        obj.setPaymentData(payment);
        System.out.println(JSON.toJSONString(obj));
        /* 查询出id列表
        Map map = new HashMap<>();
        syncPendingMapper.getPendingLing(map);
        String strId = "";
        if(map.get("out_list")!=null){
            strId = map.get("out_list").toString();
            strId = strId.substring(0,strId.length() - 1);

            if(list!=null && !list.isEmpty()){
                //调用接口
            }
        }*/

        /*通过id列表查询出消息和接口地址
        List<Integer> idLIst = new ArrayList<>();
        idLIst.add(16);
        idLIst.add(17);
        idLIst.add(18);
        List<HSyncEvn> list = syncMapper.getByListId(idLIst);
        for (HSyncEvn item:list
             ) {
            System.out.println(item.getSyncSeqId().toString() + "--" + item.getHospitalId() + "--" + item.getWsUrl());
        }*/

        /*
        * 处理完成后删除pending记录
        * */


        /*
        * 如果处理3次不成功，插入异常表
        * */
    }


    @Test
    public void testSync(){
       /* List<Integer> ids=new ArrayList<>();
        ids.add(2907);
        List<HSyncEvn> list=tSyncEvtMapper.getByListId(ids);
        for (HSyncEvn item:list
                ) {
            boolean sus = synEntService.callWsUrl3Time(item);
            System.out.println(sus);
        }*/
    }

    @Autowired
    private IOrderStatusService orderStatusService;
    @Autowired
    private ISeqService seqService;
    @Test
    public void testInsert(){
        /*OrderStatus status = OrderStatus.PAY_BUDGET_FAILS;
        int res = orderStatusService.insertOrderStatus((long)4431,status,"");
        System.out.println(res);
        int res0 = orderStatusService.insertOrderStatus((long)4435,OrderStatus.PAYING_BUDGET,"");
        System.out.println("写入预结算中状态结果：" + res0);

        TRhipOrder order = tRhipOrderMapper.selectByPrimaryKey((long)4473);
        System.out.println(JSON.toJSONString(order));*/
        System.out.println("1231231========================================================================");
        SyncEvt evt = tSyncEvtMapper.selectByPrimaryKey((long)2926);
        System.out.println(evt.toString());
        evt.setStatusCode("DONE");
        evt.setFirstProcTime(new Date());
        evt.setLastProcTime(new Date());
        int res = tSyncEvtMapper.updateStatusByPrimaryKey(evt);
        System.out.println("【结果：】" + res);
    }

    @Autowired
    private SiMedicareRecordsMapper siMedicareRecordsMapper;

    @Test
    public void testMedicareRecord(){
        SiMedicareRecordsWithBLOBs siMedicareRecordsWithBLOBs = siMedicareRecordsMapper.selectByPrimaryKey(34);
        System.out.println("结果==="+JSON.toJSON(siMedicareRecordsWithBLOBs));
    }
}
