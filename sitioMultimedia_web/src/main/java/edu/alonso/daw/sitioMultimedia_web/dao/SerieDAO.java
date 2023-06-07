package edu.alonso.daw.sitioMultimedia_web.dao;

import java.util.List;

import edu.alonso.daw.sitioMultimedia_web.model.Serie;

/**
 *
 * @author Diego
 */
public interface SerieDAO {

	/**
	 * Retrieves all series from the data source.
	 *
	 * @return a list of all series
	 */
	List<Serie> getAll();

	/**
	 * Retrieves a serie by its ID from the data source.
	 *
	 * @param id the ID of the serie to retrieve
	 * @return the serie with the specified ID, or null if not found
	 */
	Serie getById(int serie_id);

	/**
	 * Creates a new serie in the data source.
	 *
	 * @param serie the serie to create
	 */
	boolean create(Serie serie);

	/**
	 * Updates an existing serie in the data source.
	 *
	 * @param serie the serie to update
	 */
	boolean update(Serie serie);

	/**
	 * Deletes a serie from the data source.
	 *
	 * @param id the ID of the serie to delete
	 */
	boolean delete(int serie_id);

}
