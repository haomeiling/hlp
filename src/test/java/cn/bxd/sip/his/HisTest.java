package cn.bxd.sip.his;

import cn.bxd.sip.bxd.model.respond.inhos.InHosDetailRespond;
import cn.bxd.sip.bxd.model.respond.inhos.PayDetailRespond;
import cn.bxd.sip.bxd.service.ISeqService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.bxd.sip.his.utils.DateUtils;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description: TODO
 * Package: cn.bxd.sip.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-12
 */
@SpringBootTest
public class HisTest {
    @Autowired
    private ISeqService seqService;

    @Test
    public void betweenTest() throws ParseException {
        String startTime = "2018-07-13";
        String endTime = "2018-07-13";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse(startTime);
        Date end = sdf.parse(endTime);
        System.out.println(start == end);
        System.out.println(start.equals(end));
        List<Date> allBetweenDates = DateUtils.getAllBetweenDates(start, end);
        System.out.println(allBetweenDates.size());
        for (Date d : allBetweenDates) {
            System.out.println(d);
        }
    }

    @Test
    public void  testGetOrderId(){
        Long orderId=seqService.getOrderId();
        System.out.println("订单ID==="+orderId);
    }

    @Test
    public void testHisReturnJson(){
        String in="{\n" +
                "    \"inHosList\":[\n" +
                "        {\n" +
                "            \"chargeDate\":\"2018-09-21\",\n" +
                "            \"drugType\":\"材料费\",\n" +
                "            \"projectCode\":\"501\",\n" +
                "            \"projectName\":\"一次性注射器 (30ML)\",\n" +
                "            \"feeItemAmount\":0.76,\n" +
                "            \"feeItemNum\":\"1\",\n" +
                "            \"feeItemUnit\":\"付\",\n" +
                "            \"feeItemAllAmount\":\"0.76\",\n" +
                "            \"feeItemStandard\":null,\n" +
                "            \"payDate\":\"2018-09-21\",\n" +
                "            \"amountMoney\":\"\",\n" +
                "            \"MmedicareMoney\":\"\",\n" +
                "            \"payMoney\":\"\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"resultCode\":\"00\",\n" +
                "    \"resultMsg\":\"返回成功\"\n" +
                "}";

        InHosDetailRespond inHosDetailRespond=new Gson().fromJson(in,InHosDetailRespond.class);
    }



    //json字符串-简单对象型
    private static final String  JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";
    //json字符串-数组类型
    private static final String  JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";
    //复杂格式json字符串
    private static final String  COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

    @Test
    public void testJSONStrToJSONObject(){
        JSONObject jsonObject=JSON.parseObject(JSON_OBJ_STR);
        System.out.println(jsonObject.getString("studentName")+jsonObject.getInteger("studentAge"));

    }

    @Test
    public void testJSONArrayToJSONObject(){
        JSONArray jsonArray=JSON.parseArray(JSON_ARRAY_STR);
        //遍历方式1
        int size=jsonArray.size();
        for (int i = 0; i <size ; i++) {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            System.out.println(jsonObject.getString("studentName")+"-----"+jsonObject.getInteger("studentAge"));
        }

        //遍历方式2
        for(Object obj:jsonArray){
            JSONObject jsonObject=(JSONObject)obj;
            System.out.println(jsonObject.getString("studentName")+"------"+jsonObject.getInteger("studentAge"));
        }
    }

    @Test
    public void testComplexJsonToJSONObject(){
        JSONObject jsonObject=JSON.parseObject(COMPLEX_JSON_STR);
        System.out.println(jsonObject.getString("teacherName"));
        System.out.println(jsonObject.getJSONObject("course"));
        System.out.println(jsonObject.getJSONArray("students"));
    }


    @Test
    public void testFastJson(){
        User user=new User();
        user.setAge("33");
        user.setName("haomeiling");
        System.out.println(JSON.toJSONString(user));

        User user1=new User();
        user1.setAge("33");
        System.out.println(JSON.toJSONString(user1,SerializerFeature.WriteMapNullValue));

    }

}