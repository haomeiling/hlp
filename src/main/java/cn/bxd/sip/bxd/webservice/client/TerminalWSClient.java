package cn.bxd.sip.bxd.webservice.client;

import cn.bxd.sip.bxd.config.YmlTerminalConfig;
import cn.bxd.sip.bxd.webservice.invoke.hisUser.THisUserService;
import cn.bxd.sip.bxd.webservice.invoke.hisUser.TIHisUser;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.URL;

/**
 * Description: 终端实例化
 * User: HaoMeiLing
 * Date: 2018-10-12
 * Time: 16:52
 */
@Component
@Configuration
@Slf4j
public class TerminalWSClient {
    @Autowired
    private YmlTerminalConfig ymlTerminalConfig;

    //-----------------------------------分割线 开始--------------------------------------------------------
    private static final String HIS_USER = "hisUser";
    private TIHisUser hisUser;
    public static TIHisUser getHisUserInstance() {
        TerminalWSClient terminalWSClient = new TerminalWSClient();
        return terminalWSClient.getHisUser();
    }
    public TIHisUser getHisUser() {
        if (hisUser == null) {
            synchronized (HIS_USER) {
                try {
                    THisUserService tHisUserService = new THisUserService(new URL(ymlTerminalConfig.getHisUserUrl()));
                    hisUser = tHisUserService.getHisUserPort();
                    return hisUser;
                } catch (Exception e) {
                    log.error("",e);
                }
            }
        }
        return hisUser;
    }
    //-----------------------------------分割线 结束--------------------------------------------------------


}
