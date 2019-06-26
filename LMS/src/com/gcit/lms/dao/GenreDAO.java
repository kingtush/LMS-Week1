/**
 * 
 */
package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Genre;

/**
 * @author ppradhan
 *
 */
public class GenreDAO extends BaseDAO<Genre> {

	public GenreDAO(Connection conn) {
		super(conn);
	}

	public void addGenre(Genre genre) throws ClassNotFoundException, SQLException {
		save("insert into tbl_genre (genre_name) values (?)", new Object[] { genre.getGenreName() });
	}

	public void updateGenre(Genre genre) throws ClassNotFoundException, SQLException {
		save("update tbl_genre set genre_name = ? where genre_id = ?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}

	public void deleteGenre(Genre genre) throws ClassNotFoundException, SQLException {
		save("delete from tbl_genre where genre_id = ?", new Object[] { genre.getGenreId() });
	}

	public List<Genre> readAllGenres() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_genre", null);
	}

	public boolean validateGenre(Genre genre) throws ClassNotFoundException, SQLException {
		ResultSet rs = validateRecord("select * from tbl_genre where genre_id = ?",
				new Object[] { genre.getGenreId() });
		// System.out.println("made it here for validation");
		boolean notempty = false;
		while (rs.next()) {
			// ResultSet processing here
			notempty = true;
		}

		return notempty;
	}

	public List<Genre> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Genre> genres = new ArrayList<>();
		// GenreDAO bdao = new GenreDAO(conn);
		while (rs.next()) {
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			// populate my books
			// LibraryBranch.setBooks(bdao.readFirstLevel("select * from tbl_book where
			// bookId IN ( select bookId from tbl_book_authors where authorId = ?)", new
			// Object[]{LibraryBranch.getLibraryBranchId()}));
			// LibraryBranchs.add(LibraryBranch);
			genres.add(genre);
		}
		return genres;
	}

	public List<Genre> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Genre> genres = new ArrayList<>();
		while (rs.next()) {
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			genres.add(genre);
		}
		return genres;
	}

}
