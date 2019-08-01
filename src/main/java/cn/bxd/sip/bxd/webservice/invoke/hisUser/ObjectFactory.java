
package cn.bxd.sip.bxd.webservice.invoke.hisUser;

import cn.bxd.sip.bxd.model.request.hisuser.*;
import cn.bxd.sip.bxd.model.respond.hisuser.*;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.bxd.sip.bxd.webservice.invoke.hisUser package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetUserNoResponse_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "getUserNoResponse");
    private final static QName _TreatmentInfo_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "treatmentInfo");
    private final static QName _QueryPatientInfoBySocialsecurityNOResponse_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "queryPatientInfoBySocialsecurityNOResponse");
    private final static QName _CertificationUserResponse_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "certificationUserResponse");
    private final static QName _QueryPatientInfoByPhoneResponse_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "queryPatientInfoByPhoneResponse");
    private final static QName _QueryPatientInfoByIdNOResponse_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "queryPatientInfoByIdNOResponse");
    private final static QName _QueryPatientInfoBySocialsecurityNO_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "queryPatientInfoBySocialsecurityNO");
    private final static QName _InsuredPayInfo_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "insuredPayInfo");
    private final static QName _RemoteInfo_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "remoteInfo");
    private final static QName _UpdatePatientInfoResponse_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "updatePatientInfoResponse");
    private final static QName _GetSMSCodeResponse_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "getSMSCodeResponse");
    private final static QName _SiCheckPassWordResponse_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "siCheckPassWordResponse");
    private final static QName _GetCardInfo_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "getCardInfo");
    private final static QName _QueryPatientInfoByIdNO_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "queryPatientInfoByIdNO");
    private final static QName _GetUserNo_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "getUserNo");
    private final static QName _RegistterUser_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "registterUser");
    private final static QName _UpdatePatientInfo_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "updatePatientInfo");
    private final static QName _RegisterConfirmsExist_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "registerConfirmsExist");
    private final static QName _RegisteredConfirmsExist_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "registeredConfirmsExist");
    private final static QName _RegisterConfirmsExistResponse_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "registerConfirmsExistResponse");
    private final static QName _TreatmentInfoResponse_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "treatmentInfoResponse");
    private final static QName _GetSMSCode_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "getSMSCode");
    private final static QName _GetOverallArea_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "getOverallArea");
    private final static QName _MedicalPayInfo_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "medicalPayInfo");
    private final static QName _QueryPatientInfoByPhone_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "queryPatientInfoByPhone");
    private final static QName _InsuredPayInfoResponse_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "insuredPayInfoResponse");
    private final static QName _RegisteredConfirmsExistResponse_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "registeredConfirmsExistResponse");
    private final static QName _UpdateExaminationRecordStatusResponse_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "updateExaminationRecordStatusResponse");
    private final static QName _CertificationUser_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "certificationUser");
    private final static QName _UpdateExaminationRecordStatus_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "updateExaminationRecordStatus");
    private final static QName _MedicalPayInfoResponse_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "medicalPayInfoResponse");
    private final static QName _RemoteInfoResponse_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "remoteInfoResponse");
    private final static QName _GetCardInfoResponse_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "getCardInfoResponse");
    private final static QName _GetOverallAreaResponse_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "getOverallAreaResponse");
    private final static QName _RegistterUserResponse_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "registterUserResponse");
    private final static QName _SiCheckPassWord_QNAME = new QName("http://webservice.bxd.sip.bxd.cn/", "siCheckPassWord");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.bxd.sip.bxd.webservice.invoke.hisUser
     * 
     */
    public ObjectFactory() {
    }


    /**
     * Create an instance of {@link QueryPatientInfoBySocialsecurityNO }
     * 
     */
    public QueryPatientInfoBySocialsecurityNO createQueryPatientInfoBySocialsecurityNO() {
        return new QueryPatientInfoBySocialsecurityNO();
    }


    /**
     * Create an instance of {@link TCertificationUserResponse }
     * 
     */
    public TCertificationUserResponse createCertificationUserResponse() {
        return new TCertificationUserResponse();
    }

    /**
     * Create an instance of {@link TreatmentInfo }
     * 
     */
    public TreatmentInfo createTreatmentInfo() {
        return new TreatmentInfo();
    }

    /**
     * Create an instance of {@link TGetUserNoResponse }
     * 
     */
    public TGetUserNoResponse createGetUserNoResponse() {
        return new TGetUserNoResponse();
    }

    /**
     * Create an instance of {@link QueryPatientInfoByIdNO }
     * 
     */
    public QueryPatientInfoByIdNO createQueryPatientInfoByIdNO() {
        return new QueryPatientInfoByIdNO();
    }

    /**
     * Create an instance of {@link TGetUserNo }
     * 
     */
    public TGetUserNo createGetUserNo() {
        return new TGetUserNo();
    }

    /**
     * Create an instance of {@link TSiCheckPassWordResponse }
     * 
     */
    public TSiCheckPassWordResponse createSiCheckPassWordResponse() {
        return new TSiCheckPassWordResponse();
    }

    /**
     * Create an instance of {@link TGetCardInfo }
     * 
     */
    public TGetCardInfo createGetCardInfo() {
        return new TGetCardInfo();
    }


    /**
     * Create an instance of {@link TGetSMSCodeResponse }
     * 
     */
    public TGetSMSCodeResponse createGetSMSCodeResponse() {
        return new TGetSMSCodeResponse();
    }


    /**
     * Create an instance of {@link TGetOverallArea }
     * 
     */
    public TGetOverallArea createGetOverallArea() {
        return new TGetOverallArea();
    }

    /**
     * Create an instance of {@link QueryPatientInfoByPhone }
     * 
     */
    public QueryPatientInfoByPhone createQueryPatientInfoByPhone() {
        return new QueryPatientInfoByPhone();
    }


    /**
     * Create an instance of {@link TGetSMSCode }
     * 
     */
    public TGetSMSCode createGetSMSCode() {
        return new TGetSMSCode();
    }


    /**
     * Create an instance of {@link TRegisterConfirmsExistResponse }
     * 
     */
    public TRegisterConfirmsExistResponse createRegisterConfirmsExistResponse() {
        return new TRegisterConfirmsExistResponse();
    }

    /**
     * Create an instance of {@link UpdatePatientInfo }
     * 
     */
    public UpdatePatientInfo createUpdatePatientInfo() {
        return new UpdatePatientInfo();
    }

    /**
     * Create an instance of {@link TRegisterUser }
     * 
     */
    public TRegisterUser createRegistterUser() {
        return new TRegisterUser();
    }

    /**
     * Create an instance of {@link TRegisteredConfirmsExist }
     * 
     */
    public TRegisteredConfirmsExist createRegisteredConfirmsExist() {
        return new TRegisteredConfirmsExist();
    }

    /**
     * Create an instance of {@link TRegisterConfirmsExist }
     * 
     */
    public TRegisterConfirmsExist createRegisterConfirmsExist() {
        return new TRegisterConfirmsExist();
    }


    /**
     * Create an instance of {@link TSiCheckPassWord }
     * 
     */
    public TSiCheckPassWord createSiCheckPassWord() {
        return new TSiCheckPassWord();
    }


    /**
     * Create an instance of {@link TRegisterUserResponse }
     * 
     */
    public TRegisterUserResponse createRegistterUserResponse() {
        return new TRegisterUserResponse();
    }

    /**
     * Create an instance of {@link TGetOverallAreaResponse }
     * 
     */
    public TGetOverallAreaResponse createGetOverallAreaResponse() {
        return new TGetOverallAreaResponse();
    }

    /**
     * Create an instance of {@link TGetCardInfoResponse }
     * 
     */
    public TGetCardInfoResponse createGetCardInfoResponse() {
        return new TGetCardInfoResponse();
    }

    /**
     * Create an instance of {@link UpdateExaminationRecordStatus }
     * 
     */
    public UpdateExaminationRecordStatus createUpdateExaminationRecordStatus() {
        return new UpdateExaminationRecordStatus();
    }


    /**
     * Create an instance of {@link TRegisteredConfirmsExistResponse }
     * 
     */
    public TRegisteredConfirmsExistResponse createRegisteredConfirmsExistResponse() {
        return new TRegisteredConfirmsExistResponse();
    }

    /**
     * Create an instance of {@link TCertificationUser }
     * 
     */
    public TCertificationUser createCertificationUser() {
        return new TCertificationUser();
    }

    /**
     * Create an instance of {@link TCertificationUserRespond }
     * 
     */
    public TCertificationUserRespond createCertificationUserRespond() {
        return new TCertificationUserRespond();
    }

    /**
     * Create an instance of {@link Treatment }
     * 
     */
    public Treatment createTreatment() {
        return new Treatment();
    }

    /**
     * Create an instance of {@link TRegisterConfirmsExistRespond }
     * 
     */
    public TRegisterConfirmsExistRespond createRegisterConfirmsExistRespond() {
        return new TRegisterConfirmsExistRespond();
    }

    /**
     * Create an instance of {@link TGetCardInfoRespond }
     * 
     */
    public TGetCardInfoRespond createGetCardInfoRespond() {
        return new TGetCardInfoRespond();
    }

    /**
     * Create an instance of {@link THiUserModel }
     * 
     */
    public THiUserModel createHiUserModel() {
        return new THiUserModel();
    }

    /**
     * Create an instance of {@link TGetUserNoRespond }
     * 
     */
    public TGetUserNoRespond createGetUserNoRespond() {
        return new TGetUserNoRespond();
    }

    /**
     * Create an instance of {@link HisCase }
     * 
     */
    public HisCase createHisCase() {
        return new HisCase();
    }

    /**
     * Create an instance of {@link TRegisteredConfirmsExistRespond }
     * 
     */
    public TRegisteredConfirmsExistRespond createRegisteredConfirmsExistRespond() {
        return new TRegisteredConfirmsExistRespond();
    }

    /**
     * Create an instance of {@link TGetOverallAreaRespond }
     * 
     */
    public TGetOverallAreaRespond createGetOverallAreaRespond() {
        return new TGetOverallAreaRespond();
    }

    /**
     * Create an instance of {@link TSiCheckPassWordRespond }
     * 
     */
    public TSiCheckPassWordRespond createSiCheckPassWordRespond() {
        return new TSiCheckPassWordRespond();
    }

    /**
     * Create an instance of {@link TGetSMSCodeRespond }
     * 
     */
    public TGetSMSCodeRespond createGetSMSCodeRespond() {
        return new TGetSMSCodeRespond();
    }

    /**
     * Create an instance of {@link TRegisterUserRespond }
     * 
     */
    public TRegisterUserRespond createRegistterUserRespond() {
        return new TRegisterUserRespond();
    }

    /**
     * Create an instance of {@link Wipe }
     * 
     */
    public Wipe createWipe() {
        return new Wipe();
    }

    /**
     * Create an instance of {@link THiUser }
     * 
     */
    public THiUser createHiUser() {
        return new THiUser();
    }

    /**
     * Create an instance of {@link PayMoney }
     * 
     */
    public PayMoney createPayMoney() {
        return new PayMoney();
    }

    /**
     * Create an instance of {@link HiFee }
     * 
     */
    public HiFee createHiFee() {
        return new HiFee();
    }

    /**
     * Create an instance of {@link TBaseRespond }
     * 
     */
    public TBaseRespond createBaseRespond() {
        return new TBaseRespond();
    }

    /**
     * Create an instance of {@link HisReportPrintState }
     * 
     */
    public HisReportPrintState createHisReportPrintState() {
        return new HisReportPrintState();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TGetUserNoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "getUserNoResponse")
    public JAXBElement<TGetUserNoResponse> createGetUserNoResponse(TGetUserNoResponse value) {
        return new JAXBElement<TGetUserNoResponse>(_GetUserNoResponse_QNAME, TGetUserNoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TreatmentInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "treatmentInfo")
    public JAXBElement<TreatmentInfo> createTreatmentInfo(TreatmentInfo value) {
        return new JAXBElement<TreatmentInfo>(_TreatmentInfo_QNAME, TreatmentInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TCertificationUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "certificationUserResponse")
    public JAXBElement<TCertificationUserResponse> createCertificationUserResponse(TCertificationUserResponse value) {
        return new JAXBElement<TCertificationUserResponse>(_CertificationUserResponse_QNAME, TCertificationUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPatientInfoBySocialsecurityNO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "queryPatientInfoBySocialsecurityNO")
    public JAXBElement<QueryPatientInfoBySocialsecurityNO> createQueryPatientInfoBySocialsecurityNO(QueryPatientInfoBySocialsecurityNO value) {
        return new JAXBElement<QueryPatientInfoBySocialsecurityNO>(_QueryPatientInfoBySocialsecurityNO_QNAME, QueryPatientInfoBySocialsecurityNO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TGetSMSCodeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "getSMSCodeResponse")
    public JAXBElement<TGetSMSCodeResponse> createGetSMSCodeResponse(TGetSMSCodeResponse value) {
        return new JAXBElement<TGetSMSCodeResponse>(_GetSMSCodeResponse_QNAME, TGetSMSCodeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TSiCheckPassWordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "siCheckPassWordResponse")
    public JAXBElement<TSiCheckPassWordResponse> createSiCheckPassWordResponse(TSiCheckPassWordResponse value) {
        return new JAXBElement<TSiCheckPassWordResponse>(_SiCheckPassWordResponse_QNAME, TSiCheckPassWordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TGetCardInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "getCardInfo")
    public JAXBElement<TGetCardInfo> createGetCardInfo(TGetCardInfo value) {
        return new JAXBElement<TGetCardInfo>(_GetCardInfo_QNAME, TGetCardInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPatientInfoByIdNO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "queryPatientInfoByIdNO")
    public JAXBElement<QueryPatientInfoByIdNO> createQueryPatientInfoByIdNO(QueryPatientInfoByIdNO value) {
        return new JAXBElement<QueryPatientInfoByIdNO>(_QueryPatientInfoByIdNO_QNAME, QueryPatientInfoByIdNO.class, null, value);
    }


    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TGetUserNo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "getUserNo")
    public JAXBElement<TGetUserNo> createGetUserNo(TGetUserNo value) {
        return new JAXBElement<TGetUserNo>(_GetUserNo_QNAME, TGetUserNo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TRegisterUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "registterUser")
    public JAXBElement<TRegisterUser> createRegistterUser(TRegisterUser value) {
        return new JAXBElement<TRegisterUser>(_RegistterUser_QNAME, TRegisterUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePatientInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "updatePatientInfo")
    public JAXBElement<UpdatePatientInfo> createUpdatePatientInfo(UpdatePatientInfo value) {
        return new JAXBElement<UpdatePatientInfo>(_UpdatePatientInfo_QNAME, UpdatePatientInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TRegisterConfirmsExist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "registerConfirmsExist")
    public JAXBElement<TRegisterConfirmsExist> createRegisterConfirmsExist(TRegisterConfirmsExist value) {
        return new JAXBElement<TRegisterConfirmsExist>(_RegisterConfirmsExist_QNAME, TRegisterConfirmsExist.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TRegisteredConfirmsExist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "registeredConfirmsExist")
    public JAXBElement<TRegisteredConfirmsExist> createRegisteredConfirmsExist(TRegisteredConfirmsExist value) {
        return new JAXBElement<TRegisteredConfirmsExist>(_RegisteredConfirmsExist_QNAME, TRegisteredConfirmsExist.class, null, value);
    }


    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TRegisterConfirmsExistResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "registerConfirmsExistResponse")
    public JAXBElement<TRegisterConfirmsExistResponse> createRegisterConfirmsExistResponse(TRegisterConfirmsExistResponse value) {
        return new JAXBElement<TRegisterConfirmsExistResponse>(_RegisterConfirmsExistResponse_QNAME, TRegisterConfirmsExistResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TGetSMSCode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "getSMSCode")
    public JAXBElement<TGetSMSCode> createGetSMSCode(TGetSMSCode value) {
        return new JAXBElement<TGetSMSCode>(_GetSMSCode_QNAME, TGetSMSCode.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TGetOverallArea }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "getOverallArea")
    public JAXBElement<TGetOverallArea> createGetOverallArea(TGetOverallArea value) {
        return new JAXBElement<TGetOverallArea>(_GetOverallArea_QNAME, TGetOverallArea.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPatientInfoByPhone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "queryPatientInfoByPhone")
    public JAXBElement<QueryPatientInfoByPhone> createQueryPatientInfoByPhone(QueryPatientInfoByPhone value) {
        return new JAXBElement<QueryPatientInfoByPhone>(_QueryPatientInfoByPhone_QNAME, QueryPatientInfoByPhone.class, null, value);
    }


    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TRegisteredConfirmsExistResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "registeredConfirmsExistResponse")
    public JAXBElement<TRegisteredConfirmsExistResponse> createRegisteredConfirmsExistResponse(TRegisteredConfirmsExistResponse value) {
        return new JAXBElement<TRegisteredConfirmsExistResponse>(_RegisteredConfirmsExistResponse_QNAME, TRegisteredConfirmsExistResponse.class, null, value);
    }


    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TCertificationUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "certificationUser")
    public JAXBElement<TCertificationUser> createCertificationUser(TCertificationUser value) {
        return new JAXBElement<TCertificationUser>(_CertificationUser_QNAME, TCertificationUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateExaminationRecordStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "updateExaminationRecordStatus")
    public JAXBElement<UpdateExaminationRecordStatus> createUpdateExaminationRecordStatus(UpdateExaminationRecordStatus value) {
        return new JAXBElement<UpdateExaminationRecordStatus>(_UpdateExaminationRecordStatus_QNAME, UpdateExaminationRecordStatus.class, null, value);
    }


    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TGetCardInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "getCardInfoResponse")
    public JAXBElement<TGetCardInfoResponse> createGetCardInfoResponse(TGetCardInfoResponse value) {
        return new JAXBElement<TGetCardInfoResponse>(_GetCardInfoResponse_QNAME, TGetCardInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TGetOverallAreaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "getOverallAreaResponse")
    public JAXBElement<TGetOverallAreaResponse> createGetOverallAreaResponse(TGetOverallAreaResponse value) {
        return new JAXBElement<TGetOverallAreaResponse>(_GetOverallAreaResponse_QNAME, TGetOverallAreaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TRegisterUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "registterUserResponse")
    public JAXBElement<TRegisterUserResponse> createRegistterUserResponse(TRegisterUserResponse value) {
        return new JAXBElement<TRegisterUserResponse>(_RegistterUserResponse_QNAME, TRegisterUserResponse.class, null, value);
    }


    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TSiCheckPassWord }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.bxd.sip.bxd.cn/", name = "siCheckPassWord")
    public JAXBElement<TSiCheckPassWord> createSiCheckPassWord(TSiCheckPassWord value) {
        return new JAXBElement<TSiCheckPassWord>(_SiCheckPassWord_QNAME, TSiCheckPassWord.class, null, value);
    }

}
