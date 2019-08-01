package cn.bxd.sip.his;

import com.alibaba.fastjson.JSON;

import lombok.Data;

/**
 * Description:
 * Package: cn.bxd.sip.his
 *
 * @author Leeves
 * @version 1.0.0  2018-08-14
 */
@Data
public class Preview {

    private String cno;
    private int shop_id;
    private int cashier_id;
    private int money;
    private int reward_money;
    private int charge_type;
    private String remark;
    private String is_diy;
    private int recommenderecode;
    private String biz_id;

    public static void main(String[] args) {
        Preview preview = new Preview();
        preview.setCno("1535178031641110");
        preview.setShop_id(111);
        preview.setCashier_id(-1);
        preview.setMoney(100);
        preview.setReward_money(0);
        preview.setCharge_type(1);
        preview.setRemark("test");

        preview.setBiz_id("20180814113301239");
        String s = JSON.toJSONString(preview);
        System.out.println(s);

    }
}