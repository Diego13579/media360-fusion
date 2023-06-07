package edu.alonso.daw.sitioMultimedia_web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import edu.alonso.daw.sitioMultimedia_web.model.Serie;
import edu.alonso.daw.sitioMultimedia_web.service.SerieService;
import edu.alonso.daw.sitioMultimedia_web.utils.DBConnection;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

public class SerieServlet extends GenericServlet {

    private static final long serialVersionUID = 1L;
    private SerieService serieService;

    private static Logger logger = LogManager.getLogger(SerieServlet.class);

    // Problema: navegadores web no soportan nativamente los métodos HTTP PUT y
    // DELETE en los formularios, solo los métodos GET y POST son soportados.
    // Solucuión, redirigir todas las peticinoes desde el doPost.
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        serieService = new SerieService();
    }

    public String removeUrlPrefix(String uri) {
        int index = uri.lastIndexOf("/") - 1;
        if (index != -1) {
            return uri.substring(index + 1);
        } else {
            return uri;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        configureResponseHeaders(response);

        logger.info("Obtención de series");

        String uri = request.getRequestURI();
        String cleanUri = removeUrlPrefix(uri);

        if ("/listado".equals(cleanUri)) {
            showListado(request, response);
        } else if ("/formulario".equals(cleanUri)) {
            String idParam = request.getParameter("serie_id");
            if (idParam != null && !idParam.isEmpty()) {
                int id = Integer.parseInt(idParam);
                Serie serie = serieService.getSerieById(id);
                request.setAttribute("serie", serie);
            }
            TemplateEngine engine = configThymeleaf.getTemplateEngine();
            WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
            engine.process("formularios", ctx, response.getWriter());
        } else {
            // Redirigir a la página de inicio o a otra opción predeterminada
            response.sendRedirect(request.getContextPath());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        configureResponseHeaders(response);

        logger.info("Recibiendo solicitud...");

        String uri = request.getRequestURI();
        String cleanUri = removeUrlPrefix(uri);
        if ("/formulario".equals(cleanUri)) {
            String idParam = request.getParameter("serie_id");

            if (idParam != null && !idParam.isEmpty()) {
                // Si el id no está vacío, se trata de una actualización, entonces reenviamos la
                // solicitud a doPut
                logger.info("Reenviando la solicitud a doPut para actualización...");
                doPut(request, response);
            } else {
                // Si el id está vacío, es una creación
                logger.info("Creación de una serie...");

                String titulo = request.getParameter("titulo");
                String genero = request.getParameter("genero");
                String sinopsis = request.getParameter("sinopsis");
                String anoEstrenoParam = request.getParameter("anoEstreno");
                int anoEstreno = anoEstrenoParam != null && !anoEstrenoParam.isEmpty()
                        ? Integer.parseInt(anoEstrenoParam)
                        : 0;
                String duracionParam = request.getParameter("duracion");
                int duracion = duracionParam != null && !duracionParam.isEmpty()
                        ? Integer.parseInt(duracionParam)
                        : 0;

                Serie serie = new Serie();
                serie.setTitulo(titulo);
                serie.setGenero(genero);
                serie.setSinopsis(sinopsis);
                serie.setAnoEstreno(anoEstreno);
                serie.setDuracion(duracion);
                serieService.createSerie(serie);

                response.sendRedirect(request.getContextPath() + "/serie/listado");
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        configureResponseHeaders(response);
        logger.info("Actualización de una serie...");

        String idParam = request.getParameter("serie_id");
        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);
            Serie serie = serieService.getSerieById(id);
            if (serie != null) {
                String titulo = request.getParameter("titulo");
                String genero = request.getParameter("genero");
                String sinopsis = request.getParameter("sinopsis");
                String anoEstrenoParam = request.getParameter("anoEstreno");
                int anoEstreno = anoEstrenoParam != null && !anoEstrenoParam.isEmpty()
                        ? Integer.parseInt(anoEstrenoParam)
                        : 0;
                String duracionParam = request.getParameter("duracion");
                int duracion = duracionParam != null && !duracionParam.isEmpty()
                        ? Integer.parseInt(duracionParam)
                        : 0;

                serie.setTitulo(titulo);
                serie.setGenero(genero);
                serie.setSinopsis(sinopsis);
                serie.setAnoEstreno(anoEstreno);
                serie.setDuracion(duracion);
                serieService.updateSerie(serie);

                response.setStatus(HttpServletResponse.SC_OK);
                response.sendRedirect(request.getContextPath() + "/serie/listado");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        configureResponseHeaders(response);

        logger.info("Borrado de una serie...");

        String idParam = request.getParameter("serie_id");
        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);
            Serie serie = serieService.getSerieById(id);
            if (serie != null) {
                serieService.deleteSerie(id);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void showListado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        List<Serie> allSerie = serieService.getSerie();
        ctx.setVariable("series", allSerie);

        // Configurar el motor de plantillas Thymeleaf
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(request.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        // Procesar la plantilla y enviar la respuesta al cliente
        templateEngine.process("listado", ctx, response.getWriter());
    }

    private void configureResponseHeaders(HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
    }

    @Override
    public void destroy() {
        super.destroy();
        DBConnection.getInstance().destroyConnection();
    }
}
