package edu.alonso.daw.sitioMultimedia_web.service;

import java.util.List;

import edu.alonso.daw.sitioMultimedia_web.model.Serie;
import edu.alonso.daw.sitioMultimedia_web.repository.SerieRepository;

/**
 *
 * @author Diego
 */
public class SerieService {

	public List<Serie> getSerie() {
		return SerieRepository.getInstance().getAll();
	}

	public Serie getSerieById(int serie_id) {
		return SerieRepository.getInstance().getById(serie_id);
	}

	public boolean createSerie(Serie serie) {
		return SerieRepository.getInstance().create(serie);
	}

	public boolean updateSerie(Serie serie) {
		return SerieRepository.getInstance().update(serie);
	}

	public boolean deleteSerie(int serie_id) {
		return SerieRepository.getInstance().delete(serie_id);
	}
}
