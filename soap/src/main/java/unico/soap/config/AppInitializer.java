package unico.soap.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

/**
 * Servlet initializer class for the lamainator project. (This is the equivalent
 * of the old web.xml). The Servlet 3.0+ container will pick up this class and
 * run it automatically. We use it here to fire up our Spring container (by
 * referencing our main Spring config object, AppConfig), and to indicate that
 * all incoming web requests will be processed by the default dispatcher
 * servlet.
 * 
 * @author Arnon Ruangthanawes
 */

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/*" };
	}

	/**
	 * Registers and loads on startup MessageDispatcherServlet for the SOAP
	 * messages
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

		// @EnableWs, @Configuration, @ComponentScan
		context.setConfigLocation(AppConfig.class.getName());
		context.getEnvironment().setActiveProfiles("production");

		// use MessageDispatcherServlet instead of standard DispatcherServlet
		// for SOAP messages
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setContextClass(AnnotationConfigWebApplicationContext.class);
		servlet.setApplicationContext(context);
		servlet.setTransformWsdlLocations(true);

		// register MessageDispatcherServlet as Web Service entry point
		final ServletRegistration.Dynamic dispatcher = servletContext.addServlet("MessageDispatcherServlet", servlet);

		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/*");
	}
}
