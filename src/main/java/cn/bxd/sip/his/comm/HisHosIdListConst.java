package cn.bxd.sip.his.comm;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 * Package: cn.bxd.sip.his.comm
 *
 * @author Leeves
 * @version 1.0.0  2018-08-22
 */
@Deprecated
@Data
@Component
@ConfigurationProperties("his")
public class HisHosIdListConst {

    private List<String> hosIdList;

}