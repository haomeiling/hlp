package cn.bxd.sip.si;

import cn.bxd.sip.bxd.dao.ProceduresMapper;
import cn.bxd.sip.bxd.dao.ConnectParmMapper;
import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.service.ISiMedicareRecordsService;
import cn.bxd.sip.his.model.dto.reservation.DoSettlementInput;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import cn.bxd.sip.bxd.dao.SysMapper;
import cn.bxd.sip.bxd.dao.SyncEvtPendingMapper;
import cn.bxd.sip.bxd.model.entity.SyncEvtPending;
import cn.bxd.sip.si.model.dto.user.UserInfoReqData;

import static cn.bxd.sip.si.utils.XStreamUtil.generateXML;

/**
 * Description:
 * Package: cn.bxd.sip.si
 *
 * @author Leeves
 * @version 1.0.0  2018-07-16
 */
//@RunWith(SpringRunner.class)
@SpringBootTest
public class WsTest {

    @Test
    public void getUserInfo() {
        UserInfoReqData userInfoReqData = new UserInfoReqData();
        userInfoReqData.setUserNo("4509014504594");
        String generateXML = generateXML("110101", userInfoReqData);
        System.out.println(generateXML);
    }

    @Test
    public void test() {
        String s = "13520962|20022410|450121198905095137|黎明|1||19890509000000||20130516103910|1067884568|11|3053153-1|1|1||||南宁市凯旋信息科技有限公司|450103|10|150|3132 |0|2016|1675.23|187.6|94|0|0|0|0|0|33|4|1889.84|0|66.99|0|66.99|0|0|0|0|0|0|.5|0|0|0|0|0|0||0|0|0|0|0|0||4|0|0|9|1|0|450100|0||||0|";
        String[] split = s.split("\\|");
        System.out.println(split.length);
    }

    @Autowired
    ProceduresMapper proceduresMapper;
    @Autowired
    SysMapper sysMapper;

    @Autowired
    SyncEvtPendingMapper mTSyncEvtPendingMapper;

//    @Autowired
//    ProceduresMapper mProceduresMapper;

    @Test
    public void dataTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("appID", "45090001");
        map.put("clientIP", "127.0.0.1");
        int i = sysMapper.checkIP(map);
        System.out.println("--输出--：" + i);
        SyncEvtPending tSyncEvtPending = mTSyncEvtPendingMapper.selectByPrimaryKey((long) 2889);
        System.out.println("--tSyncEvtPending--:" + tSyncEvtPending);

        long new_id = (long)0;
        Map hashMap = new HashMap<>();
        hashMap.put("tableName","t_rhip_exception");
        hashMap.put("countParm",1);
        hashMap.put("creator","sys");
        proceduresMapper.selectSequenceByTableName(hashMap);
        if(hashMap.get("retVal")!=null){
            try{
                new_id = Integer.parseInt(hashMap.get("retVal").toString());
            }
            catch (Exception e){
                new_id = 0;
            }
        }
        System.out.println("--new_id--"+new_id);
    }

    @Autowired
    private ConnectParmMapper connectParmMapper;
    @Test
    public void testhos(){
        ConnectParm tRhipConnectParm = connectParmMapper.selectByPrimaryKey(7962);
        System.out.println(tRhipConnectParm.getHospitalId());
    }

    @Autowired
    private ISiMedicareRecordsService ser;
    @Test
    public void testR(){
        DoSettlementInput obj = new DoSettlementInput();

        obj.setHosId((long)230058);
        obj.setIdNumber("452528198009278777");
        obj.setOperCode((long)5001);
        obj.setOrderId((long)603);
        obj.setRecordId((long)137);
        obj.setOverMoney("1.00");
        obj.setPayMoney("1.00");
        obj.setCashMoney("1.00");
        obj.setTotalMoney("1.00");
        System.out.println(JSON.toJSONString(obj));
    }

}