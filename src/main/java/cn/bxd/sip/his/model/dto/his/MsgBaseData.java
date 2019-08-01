package cn.bxd.sip.his.model.dto.his;


/**
 * 新的消息发送数据格式
 *
 * @author moqp
 * @Date 2016/12/12
 */
public class MsgBaseData extends BaseData {

    private Integer isPrivateHospital;//0 不用医院自身短信，1 使用医院自身短信
    private Integer hosId4App;//0 ：属于平台， 其他编码问为：医院编码
    private String appid;//微信应用ID
    private String secret;//秘钥
    private String openIdWC;//微信外部账户ID
    private String openIdAli;//阿里外部账户ID
    private String msgTypeAli;//属于阿里消息的消息类型，定义有text，image 格式，
    private Integer receiverUId;//接收用户ID
    private Integer senderUId = 0;//发送用户ID，0 表示 平台
    private Integer hospitalId;//医院ID
    private String contactPhone;//联系人电话
    private Short msgType;//消息类型  0 ：1 对多发布，1： 1对1  发布

    public String getOpenIdAli() {
        return openIdAli;
    }

    public void setOpenIdAli(String openIdAli) {
        this.openIdAli = openIdAli;
    }

    public String getMsgTypeAli() {
        return msgTypeAli;
    }

    public void setMsgTypeAli(String msgTypeAli) {
        this.msgTypeAli = msgTypeAli;
    }

    public Integer getIsPrivateHospital() {
        return isPrivateHospital;
    }

    public void setIsPrivateHospital(Integer isPrivateHospital) {
        this.isPrivateHospital = isPrivateHospital;
    }

    public Integer getHosId4App() {
        return hosId4App;
    }

    public void setHosId4App(Integer hosId4App) {
        this.hosId4App = hosId4App;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getOpenIdWC() {
        return openIdWC;
    }

    public void setOpenIdWC(String openIdWC) {
        this.openIdWC = openIdWC;
    }

    public Integer getReceiverUId() {
        return receiverUId;
    }

    public void setReceiverUId(Integer receiverUId) {
        this.receiverUId = receiverUId;
    }

    public Integer getSenderUId() {
        return senderUId;
    }

    public void setSenderUId(Integer senderUId) {
        this.senderUId = senderUId;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Short getMsgType() {
        return msgType;
    }

    public void setMsgType(Short msgType) {
        this.msgType = msgType;
    }
    /*
    @SerializedName("Msg_Type")
    private int msgType;//消息类型

    @SerializedName("Msg_ID")
    private int msgId;//消息ID

    @SerializedName("Wechat_Msg_Content")
    private String wechatMsgContent;//微信消息内容

    @SerializedName("Msg_Content")
    private String msgContent;//消息内容，电脑端消息

    @SerializedName("Ali_Msg_Content")
    private String aliMsgContent;//阿里云消息内容

    @SerializedName("Sender_UID")
    private int senderUId = 0;//发送者ID，默认为系统发送

    @SerializedName("Receiver_UID")
    private int receiverUId;//接送者ID;

    @SerializedName("Send_Address_WC")
    private String sendAddressWC;//该条信息需要发送的微信地址

    @SerializedName("Send_Address_Ali")
    private String sendAddressAli;//该条信息需要发送的阿里云地址

    @SerializedName("Hospital_ID")
    private Integer hospital_ID;//该条信息归属的医院,

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public int getSenderUId() {
        return senderUId;
    }

    public void setSenderUId(int senderUId) {
        this.senderUId = senderUId;
    }

    public int getReceiverUId() {
        return receiverUId;
    }

    public void setReceiverUId(int receiverUId) {
        this.receiverUId = receiverUId;
    }

    public String getWechatMsgContent() {
        return wechatMsgContent;
    }

    public void setWechatMsgContent(String wechatMsgContent) {
        this.wechatMsgContent = wechatMsgContent;
    }

    public String getAliMsgContent() {
        return aliMsgContent;
    }

    public void setAliMsgContent(String aliMsgContent) {
        this.aliMsgContent = aliMsgContent;
    }

    public String getSendAddressWC() {
        return sendAddressWC;
    }

    public void setSendAddressWC(String sendAddressWC) {
        this.sendAddressWC = sendAddressWC;
    }

    public String getSendAddressAli() {
        return sendAddressAli;
    }

    public void setSendAddressAli(String sendAddressAli) {
        this.sendAddressAli = sendAddressAli;
    }

    public Integer getHospital_ID() {
        return hospital_ID;
    }

    public void setHospital_ID(Integer hospital_ID) {
        this.hospital_ID = hospital_ID;
    }

    */
}
