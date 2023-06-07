package edu.alonso.daw.sitioMultimedia_web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.alonso.daw.sitioMultimedia_web.model.Serie;
import edu.alonso.daw.sitioMultimedia_web.utils.DBConnection;
import java.sql.Statement;

/**
 *
 * @author Diego
 */
public class SerieDAOMySql implements SerieDAO {

	private final String SELECT_SERIES = "SELECT * FROM SERIES";
	private final String SELECT_SERIE_BY_ID = "SELECT * FROM SERIES WHERE serie_id = ?";
	private final String DELETE_SERIES = "DELETE FROM SERIES WHERE serie_id = ?";
	private final String UPDATE_SERIE = "UPDATE SERIES SET titulo = ?, genero = ?, sinopsis = ?, anoEstreno = ?, duracion = ? WHERE serie_id = ?;";
	private final String INSERT_SERIE = "INSERT INTO SERIES (titulo, genero, sinopsis, anoEstreno, duracion) VALUES (?, ?, ?, ?, ?);";

	private static Logger logger = LogManager.getLogger(SerieDAOMySql.class);

	@Override
	public List<Serie> getAll() {
		List<Serie> series = new ArrayList<>();

		logger.info("Obtengo las series...");

		Connection conn = DBConnection.getInstance().getConnection();
		if (conn != null) {
			try (Statement stmt = conn.createStatement();) {

				ResultSet rs = stmt.executeQuery(SELECT_SERIES);

				
				
				while (rs.next()) {
					Serie s = new Serie(rs.getInt("serie_id"), rs.getString("titulo"), rs.getString("genero"),
							rs.getString("sinopsis"), rs.getInt("anoEstreno"), rs.getInt("duracion"));
					series.add(s);
				}

			} catch (SQLException e) {
				System.out.println(" " + e.getMessage());
				e.printStackTrace();
			}
		}

		return series;
	}

	@Override
	public Serie getById(int serie_id) {

		logger.info("Obtengo la serie con id: " + serie_id);

		Connection conn = DBConnection.getInstance().getConnection();
		if (conn != null) {
			try (PreparedStatement stmt = conn.prepareStatement(SELECT_SERIE_BY_ID);) {
				stmt.setLong(1, serie_id);

				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					return new Serie(rs.getInt("serie_id"), rs.getString("titulo"), rs.getString("genero"),
							rs.getString("sinopsis"), rs.getInt("anoEstreno"), rs.getInt("duracion"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public boolean create(Serie serie) {
		Connection conn = DBConnection.getInstance().getConnection();
		if (conn != null) {
			try (PreparedStatement stmt = conn.prepareStatement(INSERT_SERIE);) {
				stmt.setString(1, serie.getTitulo());
				stmt.setString(2, serie.getGenero());
				stmt.setString(3, serie.getSinopsis());
				stmt.setLong(4, serie.getAnoEstreno());
				stmt.setLong(5, serie.getDuracion());

				int rowsAffected = stmt.executeUpdate();

				return rowsAffected > 0;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public boolean update(Serie serie) {

		logger.info("Actualizo la serie: " + serie);
		Connection conn = DBConnection.getInstance().getConnection();
		if (conn != null) {
			try (PreparedStatement stmt = conn.prepareStatement(UPDATE_SERIE);) {
				stmt.setString(1, serie.getTitulo());
				stmt.setString(2, serie.getGenero());
				stmt.setString(3, serie.getSinopsis());
				stmt.setLong(4, serie.getAnoEstreno());
				stmt.setLong(5, serie.getDuracion());
				stmt.setLong(6, serie.getSerie_id());

				int rowsAffected = stmt.executeUpdate();

				return rowsAffected > 0;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public boolean delete(int serie_id) {

		logger.info("Borro la serie con id: " + serie_id);
		Connection conn = DBConnection.getInstance().getConnection();
		if (conn != null) {
			try (PreparedStatement stmt = conn.prepareStatement(DELETE_SERIES);) {
				stmt.setLong(1, serie_id);

				int rowsAffected = stmt.executeUpdate();

				return rowsAffected > 0;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

}
