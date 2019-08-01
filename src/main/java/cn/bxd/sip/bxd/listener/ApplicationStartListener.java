package cn.bxd.sip.bxd.listener;

import cn.bxd.sip.bxd.var.SIVar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Description: 启动监听
 * Package: com.bxd.config.listener
 *
 * @author Leeves
 * @date 2018-01-11
 */
@Component
public class ApplicationStartListener implements CommandLineRunner {

    private static Logger log = LoggerFactory.getLogger(ApplicationStartListener.class);
    @Autowired
    SIVar siVar;

    @Override
    public void run(String... strings) {
        log.info("---开始执行ApplicationStartListener-----");
        //常量初始化
        siVar.ini();
    }
}
