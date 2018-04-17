package unico.restfull.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Servlet initializer class for the lamainator project. (This is the equivalent of the old web.xml).  
 * The Servlet 3.0+ container will pick up this class and run it automatically.
 * We use it here to fire up our Spring container (by referencing our main Spring
 * config object, AppConfig), and to indicate that all incoming web requests
 * will be processed by the default dispatcher servlet.
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
}
