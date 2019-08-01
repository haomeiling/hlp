package cn.bxd.sip.bxd.model.respond.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : cRyann
 * @create 2018-09-06
 **/
@Data
public class QueryDoctorBysourceDateRespond {
    List<HisDoctor> doctor = new ArrayList<>();
}
