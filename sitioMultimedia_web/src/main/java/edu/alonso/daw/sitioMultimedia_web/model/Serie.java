package edu.alonso.daw.sitioMultimedia_web.model;

import java.util.Objects;

/**
 *
 * @author Diego
 */
public class Serie {

	// Atributes
	public int serie_id;
	public String titulo;
	public String genero;
	public String sinopsis;
	public int anoEstreno;
	public int duracion;

	// Constructor
	public Serie() {

	}

	public Serie(String titulo, String genero, String sinopsis, int anoEstreno, int duracion) {
		this.titulo = titulo;
		this.genero = genero;
		this.sinopsis = sinopsis;
		this.anoEstreno = anoEstreno;
		this.duracion = duracion;
	}

	public Serie(int serie_id, String titulo, String genero, String sinopsis, int anoEstreno, int duracion) {
		this.serie_id = serie_id;
		this.titulo = titulo;
		this.genero = genero;
		this.sinopsis = sinopsis;
		this.anoEstreno = anoEstreno;
		this.duracion = duracion;
	}

	public long getSerie_id() {
		return serie_id;
	}

	public void setSerie_id(int serie_id) {
		this.serie_id = serie_id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public long getAnoEstreno() {
		return anoEstreno;
	}

	public void setAnoEstreno(int anoEstreno) {
		this.anoEstreno = anoEstreno;
	}

	public long getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anoEstreno, duracion, genero, serie_id, sinopsis, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Serie other = (Serie) obj;
		return anoEstreno == other.anoEstreno && duracion == other.duracion && Objects.equals(genero, other.genero)
				&& serie_id == other.serie_id && Objects.equals(sinopsis, other.sinopsis)
				&& Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Serie [serie_id=" + serie_id + ", titulo=" + titulo + ", genero=" + genero + ", sinopsis=" + sinopsis
				+ ", anoEstreno=" + anoEstreno + ", duracion=" + duracion + "]";
	}

}
