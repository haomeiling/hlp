package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.webservice.Header;
import cn.bxd.sip.bxd.webservice.HeaderBody;
import cn.bxd.sip.bxd.webservice.client.WSClient;
import cn.bxd.sip.his.model.dto.reservation.GetSocialPolicyInput;
import cn.bxd.sip.his.model.dto.reservation.GetSocialPolicyOutput;
import cn.bxd.sip.his.model.dto.reservation.GetSocialPolicyOutputItem;
import cn.bxd.sip.his.model.dto.reservation.GetSocialPolicyOutputXml;
import cn.bxd.sip.his.utils.JaxbUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.util.List;

import static cn.bxd.sip.si.utils.XStreamUtil.generateXML;

/**
 * 获取政策
 * @author:tangliang
 * @date:2018/7/28
 * @description:
 */
@Component
public class GetSocialPolicy extends AbstractOperationProcessor {

    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) throws JAXBException {
        String res = "";
        GetSocialPolicyOutput output = new GetSocialPolicyOutput();
        GetSocialPolicyInput inputObj = JSON.parseObject(reqMsg, GetSocialPolicyInput.class);
        HeaderBody xmlInput = new HeaderBody();
        Header head = new Header();
        head.setResultCode("1");
        head.setResultMsg("");
        head.setAppID("");
        head.setMsgID("");
        head.setMsgTime("");
        head.setMsgType("1");
        head.setVersion("1.0");
        xmlInput.setHEADER(head);
        xmlInput.setBODY(inputObj);

        String xmlStrInput = generateXML("110110", inputObj);
        //String xmlStrInput = XStreamUtils.object2XML(HeaderBody.class,xmlInput);
        System.out.println("call 110110 input = " + xmlStrInput);
        String xmlOut = WSClient.getInstance().sendWS(xmlStrInput);
        System.out.println("call 110110 output = " + xmlOut);
        if (xmlOut != null) {
            GetSocialPolicyOutputXml xmlOutObj = JaxbUtil.converyToJavaBean(xmlOut, GetSocialPolicyOutputXml.class);
            if (xmlOutObj != null) {
                List<GetSocialPolicyOutputItem> list = xmlOutObj.getBody().getItem();
                output.setData(list);
                output.setSuccess((long) 1);
            } else {
                output.setOperCode((long) 0);
            }
        } else {

        }
        output.setOperCode((long) 6000);
        TypeUtils.compatibleWithFieldName = true;
        res = JSON.toJSONString(output);
        return res;
    }
}
