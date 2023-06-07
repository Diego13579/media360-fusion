package edu.alonso.daw.sitioMultimedia_web.repository;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.alonso.daw.sitioMultimedia_web.dao.SerieDAO;
import edu.alonso.daw.sitioMultimedia_web.dao.SerieDAOMySql;
import edu.alonso.daw.sitioMultimedia_web.model.Serie;

/**
 * 
 * @author Diego
 */
public class SerieRepository {

    private static SerieDAO dao;

    private static SerieRepository instance;

    private static Logger logger = LogManager.getLogger(SerieRepository.class);

    // CREATE AN INSTANCE
    public static synchronized SerieRepository getInstance() {
        
        if (instance == null) {
            instance = new SerieRepository();
        }
        return instance;
    }

    private SerieRepository() {
        logger.info("Creando el dao para Serie...");
        dao = new SerieDAOMySql();
        logger.info("Dao para Serie creado con éxito");
    }

    public List<Serie> getAll() {
        return dao.getAll();
    }

    public Serie getById(int serie_id) {
        return dao.getById(serie_id);
    }

    public boolean create(Serie serie) {
        return dao.create(serie);
    }

    public boolean update(Serie serie) {
        return dao.update(serie);
    }

    public boolean delete(int serie_id) {
        return dao.delete(serie_id);
    }

}
