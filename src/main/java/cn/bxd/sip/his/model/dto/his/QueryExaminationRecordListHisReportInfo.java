package cn.bxd.sip.his.model.dto.his;
import lombok.Data;
/**
 * Description:
 * User: HaoMeiLing
 * Date: 2018-12-14
 * Time: 17:17
 */


@Data
public class QueryExaminationRecordListHisReportInfo {

    private String reportNo;//	报告编号
    private String patientNo;//	患者编号
    private String patientName;//	患者姓名
    private String reportDate;//	检查时间
    private String createTime;//	报告生成时间
    private String contentpicsrc;//	报告内容文件下载路径
    private String reportUrl;//	医院内网图像地址，供医院终端使用
    private String checkName;//	检查名称
    private String doctorName;//	出报告医生名字
    private String chekckdoctorName;//	检查医生名字
    private String reportStatus;//	报告状态,0：未出报告，1：已出报告
    private String isPrint;//	打印状态,0：表示未打印，1：表示已打印
    private String reportType;//	报告类型,1：检验，2：B超，3：放射影像，4：内镜，5：病理，6：心电图
    private String isPic;//	报告输出形式,0：图片，,1：数据流
    private String reportItems;//	报告详情：isPic为1时，此字段非空。例如 ,序号|项目名称|检查结果|检验状态(↑,↓等)|结果单位|参考值|特殊描述^1|血糖|偏高|↑|mmol/L|5.0|糖尿病^……
    private String extend;//	Json值

}
