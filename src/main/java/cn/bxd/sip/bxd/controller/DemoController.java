package cn.bxd.sip.bxd.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.ZolozIdentificationUserWebInitializeRequest;
import com.alipay.api.response.ZolozIdentificationUserWebInitializeResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-05-05
 * Time: 15:28
 */
@Slf4j
@Controller
@RequestMapping("/")
public class DemoController {

    @RequestMapping(value = "demo")
    @ResponseBody
    public String demo() {
        return "demo";
    }

    @RequestMapping(value = "jsonTest")
    @ResponseBody
    public String jsonTest() {
        return "{\n" +
                "    \"apdidToken\":\"Dqe3+uYhuwE2rNiTOdJd7jpkLL6ieRk3buNPGwsss+EejYaypbmWagEB\",\n" +
                "    \"appName\":\"com.alipay.zoloz.smile\",\n" +
                "    \"appVersion\":\"3.5.0.201807301434\",\n" +
                "    \"bioMetaInfo\":\"3.12.0:2129920,2\",\n" +
                "    \"deviceModel\":\"BND-AL10\",\n" +
                "    \"deviceType\":\"android\",\n" +
                "    \"machineInfo\":{\n" +
                "        \"cameraDriveVer\":\"\",\n" +
                "        \"cameraModel\":\"Android\",\n" +
                "        \"cameraName\":\"Android\",\n" +
                "        \"cameraVer\":\"\",\n" +
                "        \"ext\":\"\",\n" +
                "        \"group\":\"\",\n" +
                "        \"machineCode\":\"XNKeHaZqODYDAB7ftGn/ro3I\",\n" +
                "        \"machineModel\":\"BND-AL10\",\n" +
                "        \"machineVer\":\"8.0.0\"\n" +
                "    },\n" +
                "    \"merchantInfo\":{\n" +
                "        \"alipayStoreCode\":\"TEST\",\n" +
                "        \"appId\":\"2016091200495527\",\n" +
                "        \"areaCode\":\"TEST\",\n" +
                "        \"brandCode\":\"TEST\",\n" +
                "        \"deviceMac\":\"TEST\",\n" +
                "        \"deviceNum\":\"TEST_ZOLOZ_TEST\",\n" +
                "        \"geo\":\"0.000000,0.000000\",\n" +
                "        \"merchantId\":\"TEST\",\n" +
                "        \"partnerId\":\"TEST\",\n" +
                "        \"storeCode\":\"TEST\",\n" +
                "        \"wifiMac\":\"TEST\",\n" +
                "        \"wifiName\":\"TEST\"\n" +
                "    },\n" +
                "    \"osVersion\":\"8.0.0\",\n" +
                "    \"remoteLogID\":\"9c095c0c0b784c63a3e596a53418fc771587412973\",\n" +
                "    \"zimVer\":\"1.0.0\"\n" +
                "}";
    }

    @RequestMapping("getH5Identification")
    @ResponseBody
    public String getH5Identification() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2017122101030181", "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCk/mAyh4+qCRbzfGVfJF5NP1qXuXnBc4oVvQk+XK6Wvtijv2HD3XP8t8Vq+0lPDNC8g14PBmj7x+WE3GMOb3uRHg6V1dxGGmZOk3yfJycwhEsMeTXSgnVaoi1OcLnEYdR0wEbUem2n8bqkZkQlUVss+QbihCKJjTts1q3+Azsq7JyjKBnMekS7sDq0Ea6FrrwH4X9ZMcnL17P1FOrnjYubCuBGH0dYSkfo3JGE+BvqmrSWPDbKJjFr5BKbBK/f32QS8IrYio5omkSouErSSmKuoQithycbVZfqP8TKZ7bNIfNTzu+6nV+U7btEVu9gdirQvTOB38Olji4bNmD3wddvAgMBAAECggEARm0mFqtykTvMBiaFBrV5YzO1sanD5OUyfdDwzyO0ijfwNQUN8BLo3Wh75gxY4FsyBiV00qkDyuUqfSmtRo4jim/v/dkJawr9vHCL80s9GSJ+/lYGuVJiUUuEV0Uapk6z5xnjofKYN+Wgq2fTkrJFXs71N177dwdH5E9GeXRbnxgsZ4ZocfFwKmEePHRrf/RAgSpx9G3amf7hDi2zqYbEIqb1K7FrqueSeEfTqTYB7zcw++/FLGCPHWmCBp0A0VCTUDnfc369Tgr/Uv6bWIzpgLa3YkKsoz0WqBtH/hXk2fqgqQZyQTIWXAVd+ZCyH3J9o/+jyhXkFGPI7e8PIEVAwQKBgQDcngUDI+aPx82RqyEwQWz+p+//tt6Vw1kf3Zv+cmLN/3BbheiuzeNI0GSNNTlU6Z0AbJE2RLuEODlqrnbNVPrJhnSxsNYIg4eDSvQNlQHittYhjAvJqQalvkecP38YQR+Kioew05OSsdGGV6jj73p2KKcFvk2ZMqfldbhvRz8q8QKBgQC/dJfXfh2ilBPHdTsFtAihj1tjRnJ5IJXnokXvC6EuOs8RN9pIaIydQCooW7yNYDEBIjY9KByoIdo2Jz3aNj+eCYkMJvioHR0HaSt2oEe7DXFUGJ9zItJ9D2GQEYtgcm1PQdQACTTQ1uxO/1L911OMekgS6phjITc+Lgxwdc9oXwKBgGucTHeWqFzTqcr2/S5Li32/rso9id7lNXDcqL15UAaJJWYPMEsrtESNGa7jGj734JQXYNFmv0ykHCWUSfCoFdf81Kjjx9LJTW1WPyrs1smeQ16DsDBCWTK5WLa3JihPg7IRFlTDLwVr5PaxhXkBE+vUsnuTssgaNFkxjbABwacRAoGAUVF4XPMYK0Gx1PoaA6YgNF7rdhMp9AC/Kw9lnM7XuwcHhEywzih58SCbx2CPiYzdw5mAaCFtaoxWXsH0J4b7JmjuaNb0rpMPDWLCLzSMwwpGpG2A7CpSNQQfcE3DYUe43f0bypgecsWWwUb5PRAEUJpvmnlCGHi3OhXc+rdPTQkCgYBMVC0XgrvHTAUHwhWkbDhKBemvOTTOR3kxQ0/ldTlaPAQ9I9XWQ9ZXm2ES7+KWlsp8kkSVOYNRch7+FxSVk5gZTnFB9Fsq1tWTntO/yb18+BVz797fnfmFhgvoIYz84vz0oZeh1iS/ZmIsE54ZFyVWsYwxIf7R4qX2XAKu1XhPqA==", "json", "GBK", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo+SpqDrtvM4Bma/I4AMFmrdx+jsv5bbLQHCrq2sBn6ohMf2epukjCvQD99IiadFbhaMUFIzlk4DVR3ZenNS4TDLfmGhKPG7S5elaovefadLHFoxmduNAEwti5uCZ2tgKYzeWy4A470FboUJKh830T1LUddVUdvicxBNvADzJ03A/o4FABuN2PjKKr/ZVCk88ccFS9ROSi0fJGXdZygsBsXB+izhxiy/R+cR6+7xEO4YVdXMJCHCEza+OTd2nlst9H+Mf+ejHMVnTRthBrZFxkOQKJJ7gxazmN5bQC4PbBrDKjgOFmaEiecpErCX8jWGPvadTpY/lsOFoQIAcS2K0hQIDAQAB", "RSA2");
        ZolozIdentificationUserWebInitializeRequest request = new ZolozIdentificationUserWebInitializeRequest();
        request.setBizContent("{" +
                "\"biz_id\":\"5456897876546767654\"," +
                "\"identity_param\":{" +
                "\"identity_type\":\"CERT\"," +
                "\"cert_type\":\"IDCARD\"," +
                "\"cert_name\":\"张*\"," +
                "\"cert_no\":\"34113********24\"," +
                "\"user_id\":\"208810*****91410\"" +
                "    }," +
                "\"metainfo\":\"{\\\"apdidToken\\\":\\\"0/5NLJUTQBwgg8YiPXyMs/7gUtUxDpFEzVF46QQ029qmqXn2aDlQYQEB\\\"}\"," +
                "\"extern_param\":\"{}\"" +
                "  }");
        ZolozIdentificationUserWebInitializeResponse response = alipayClient.execute(request);
        log.info(new Gson().toJson(response));
        if (response.isSuccess()) {
            log.info("调用成功");
            return "调用成功";
        } else {
            log.info("调用失败");
            return "调用失败";
        }
    }
}
