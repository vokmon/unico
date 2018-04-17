package unico.soap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import unico.soap.constant.Constant;
import unico.soap.repository.GcdTransactionRepository;
import unico.soap.service.GcdService;

/**
 * Restfull App Configuration. Defines the classes that will be scanned for the
 * web interface.
 * 
 * @author Arnon Ruangthanawes
 */

@Configuration
@EnableWs
@ComponentScan({ "unico.soap.endpoint" })
public class AppConfig extends WsConfigurerAdapter {

	/**
	 * Create the bean for reading the schema
	 * @return the bean for reading the schema
	 */
	@Bean
	public XsdSchema countriesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("soap.xsd"));
	}
	
	/**
	 * Exposes a standard WSDL using XsdSchema
	 * 
	 * @param countriesSchema the {@link XsdSchema}
	 * @return WSDL using XsdSchema
	 */
	@Bean(name = "gdcDetailsWsdl")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema)
    {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName(Constant.PORT_TYPE_NAME);
        wsdl11Definition.setLocationUri(Constant.LOCAL_URI);
        wsdl11Definition.setTargetNamespace(Constant.TARGET_NAME_SPACE);
        wsdl11Definition.setSchema(countriesSchema);
        return wsdl11Definition;
    }
	
	/**
	 * Create the bean for gcd service
	 * @return {@link GcdService}
	 */
	@Bean
	public GcdService gcdService() {
		return new GcdService();
	}
	
	/**
	 * Create the bean for gcd repository
	 * @return bean for gcd repository
	 */
	@Bean
	GcdTransactionRepository gcdTransactionRepository() {
		return new GcdTransactionRepository();
	}
}
