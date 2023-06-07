package edu.alonso.daw.sitioMultimedia_web.utils;

import javax.servlet.ServletContext;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

public class ThymeleafConfig {
	private final TemplateEngine templateEngine;

	public ThymeleafConfig(final ServletContext ctx) {
		
		
		/*
		 * ServletContextTemplateResolver
		 * 
		 * These objects are in charge of determining how our templates will be accessed, and in this GTVG application, 
		 * the org.thymeleaf.templateresolver.ServletContextTemplateResolver implementation that we are using specifies that we are 
		 * going to retrieve our template files as resources from the Servlet Context: an application-wide javax.servlet.ServletContext 
		 * object that exists in every Java web application, and that resolves resources considering the web application 
		 * root as the root for resource paths.
		 * 
		 * 
		 * 
		 * Estos objetos son los encargados de determinar cómo se accederá a nuestras plantillas, y en esta aplicación GTVG, 
		 * la implementación org.thymeleaf.templateresolver.ServletContextTemplateResolver que estamos utilizando especifica que vamos a 
		 * recuperar nuestros archivos de plantillas como recursos del Contexto Servlet: un objeto javax.servlet.ServletContext de toda la 
		 * aplicación que existe en toda aplicación web Java, y que resuelve los recursos considerando 
		 * la raíz de la aplicación web como raíz para las rutas de recursos.

		 */

		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(ctx);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setCacheTTLMs(3600000L);
		templateResolver.setCacheable(true);

		templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
	}

	public TemplateEngine getTemplateEngine() {
		return templateEngine;
	}
}
