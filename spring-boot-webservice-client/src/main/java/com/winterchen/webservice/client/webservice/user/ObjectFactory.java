
package com.winterchen.webservice.client.webservice.user;


import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.winterchen.webservice package. 
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


    private final static QName _FindUserById_QNAME = new QName("http://user.winterchen.com/webservice", "findUserById");
    private final static QName _FindUserByIdResponse_QNAME = new QName("http://user.winterchen.com/webservice", "findUserByIdResponse");
    private final static QName _PageUserInfo_QNAME = new QName("http://user.winterchen.com/webservice", "pageUserInfo");
    private final static QName _PageUserInfoResponse_QNAME = new QName("http://user.winterchen.com/webservice", "pageUserInfoResponse");
    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.winterchen.webservice
     * 
     */
    public ObjectFactory() {
    }

    

    /**
     * Create an instance of {@link FindUserById }
     *
     */
    public FindUserById createFindUserById() {
        return new FindUserById();
    }

    /**
     * Create an instance of {@link FindUserByIdResponse }
     *
     */
    public FindUserByIdResponse createFindUserByIdResponse() {
        return new FindUserByIdResponse();
    }

    /**
     * Create an instance of {@link PageUserInfo }
     *
     */
    public PageUserInfo createPageUserInfo() {
        return new PageUserInfo();
    }

    /**
     * Create an instance of {@link PageUserInfoResponse }
     *
     */
    public PageUserInfoResponse createPageUserInfoResponse() {
        return new PageUserInfoResponse();
    }

    /**
     * Create an instance of {@link UserInfoDTO }
     *
     */
    public UserInfoDTO createUserInfoDTO() {
        return new UserInfoDTO();
    }

    /**
     * Create an instance of {@link BasePage }
     *
     */
    public BasePage createBasePage() {
        return new BasePage();
    }

    /**
     * Create an instance of {@link CommonPage }
     *
     */
    public CommonPage createCommonPage() {
        return new CommonPage();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUserById }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://user.winterchen.com/webservice", name = "findUserById")
    public JAXBElement<FindUserById> createFindUserById(FindUserById value) {
        return new JAXBElement<FindUserById>(_FindUserById_QNAME, FindUserById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUserByIdResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://user.winterchen.com/webservice", name = "findUserByIdResponse")
    public JAXBElement<FindUserByIdResponse> createFindUserByIdResponse(FindUserByIdResponse value) {
        return new JAXBElement<FindUserByIdResponse>(_FindUserByIdResponse_QNAME, FindUserByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PageUserInfo }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://user.winterchen.com/webservice", name = "pageUserInfo")
    public JAXBElement<PageUserInfo> createPageUserInfo(PageUserInfo value) {
        return new JAXBElement<PageUserInfo>(_PageUserInfo_QNAME, PageUserInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PageUserInfoResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://user.winterchen.com/webservice", name = "pageUserInfoResponse")
    public JAXBElement<PageUserInfoResponse> createPageUserInfoResponse(PageUserInfoResponse value) {
        return new JAXBElement<PageUserInfoResponse>(_PageUserInfoResponse_QNAME, PageUserInfoResponse.class, null, value);
    }

}
