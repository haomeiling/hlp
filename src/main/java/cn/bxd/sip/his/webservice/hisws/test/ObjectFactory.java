
package cn.bxd.sip.his.webservice.hisws.test;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bxd.hisws.test package.
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bxd.hisws.test
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QueryToRegDoctorList }
     * 
     */
    public QueryToRegDoctorList createQueryToRegDoctorList() {
        return new QueryToRegDoctorList();
    }

    /**
     * Create an instance of {@link QueryToRegDoctorTimes }
     * 
     */
    public QueryToRegDoctorTimes createQueryToRegDoctorTimes() {
        return new QueryToRegDoctorTimes();
    }

    /**
     * Create an instance of {@link QueryToRegDoctorListByDoctorId }
     * 
     */
    public QueryToRegDoctorListByDoctorId createQueryToRegDoctorListByDoctorId() {
        return new QueryToRegDoctorListByDoctorId();
    }

    /**
     * Create an instance of {@link QueryToRegDoctorListByDoctorIdResponse }
     * 
     */
    public QueryToRegDoctorListByDoctorIdResponse createQueryToRegDoctorListByDoctorIdResponse() {
        return new QueryToRegDoctorListByDoctorIdResponse();
    }

    /**
     * Create an instance of {@link QueryToRegDoctorTimesResponse }
     * 
     */
    public QueryToRegDoctorTimesResponse createQueryToRegDoctorTimesResponse() {
        return new QueryToRegDoctorTimesResponse();
    }

    /**
     * Create an instance of {@link QueryToRegDoctorListResponse }
     * 
     */
    public QueryToRegDoctorListResponse createQueryToRegDoctorListResponse() {
        return new QueryToRegDoctorListResponse();
    }

}
