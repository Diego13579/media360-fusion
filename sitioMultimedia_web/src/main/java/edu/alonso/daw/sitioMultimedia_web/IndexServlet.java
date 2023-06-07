package edu.alonso.daw.sitioMultimedia_web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

public class IndexServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = LogManager.getLogger(IndexServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Para ejecutar:
		// http://localhost:8080/sitioMultimedia_web/

		// revisar
		// https://www.codejava.net/ides/eclipse/how-to-create-java-web-project-with-maven-in-eclipse

		// Primero invoco al doGet del padre.
		super.doGet(request, response);

		// El siguiente contexto nos va a permitir procesar las plantillas,
		// es decir, nos permite mandar información a la plantilla HTML
		WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

		// En este caso mandaríamos a la referencia en la plantilla "today", una fecha
		ctx.setVariable("today", new Date());

		// Al final procesamos la plantilla con la clase TemplateEngine.
		// El nombre de la plantilla es el indicado en el método process.
		// response.getWriter obtiene el objeto PrintWriter, que nos permite escribir la
		// salida.
		TemplateEngine engine = configThymeleaf.getTemplateEngine();
//        engine.process("home", ctx, response.getWriter());
		engine.process("index", ctx, response.getWriter());

		/*
		 * Para la resolución con multilenguaje hay que poner la traducción en la misma
		 * carpeta que las plantillas html es decir, los .properties. Además, los
		 * ficheros de propiedades tienen que llevar el mismo nombre que la plantilla.
		 * The standard message resolver expects to find messages for
		 * /WEB-INF/templates/home.html in properties files in the same folder and with
		 * the same name as the template, like:
		 */
	}
}
