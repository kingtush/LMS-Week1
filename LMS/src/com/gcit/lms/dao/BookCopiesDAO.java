package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Genre;

public class BookCopiesDAO extends BaseDAO<BookCopies> {

	public BookCopiesDAO(Connection conn) {
		super(conn);
	}

	/*
	 * public void addBookCopies(BookCopies bookCopies) throws
	 * ClassNotFoundException, SQLException {
	 * save("insert into tbl_book_copies (branchName) values (?)", new
	 * Object[]{bookCopies.getGenreName()}); }
	 */

	public void updateBookCopies(BookCopies bookCopies) throws ClassNotFoundException, SQLException {
		save("update tbl_book_copies set noOfCopies = ? where bookId = ?",
				new Object[] { bookCopies.getNoOfCopies(), bookCopies.getBookId() });
	}

	public void deleteBookCopies(BookCopies bookCopies) throws ClassNotFoundException, SQLException {
		save("delete from tbl_book_copies where branchId = ?", new Object[] { bookCopies.getBookId() });
	}

	public List<BookCopies> readAllBookCopies() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book_copies", null);
	}

	public List<BookCopies> readAvailableBooksForBranch(BookCopies bookCopies)
			throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book_copies where branchId=? and noOfCopies>0",
				new Object[] { bookCopies.getBranchId() });
	}

	public List<BookCopies> readAllBooksForBranch(BookCopies bookCopies) throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book_copies where branchId=?", new Object[] { bookCopies.getBranchId() });
	}

	public void readBook(Integer bookId) throws ClassNotFoundException, SQLException {
		String sql = "select title from tbl_book where bookId = ?";
		Object[] vals = { bookId };
		ResultSet rs;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		populatePrepStatement(vals, pstmt);
		rs = pstmt.executeQuery();
		System.out.println(rs.getString("title"));
	}

	public Integer getCopies(BookCopies bookcopies) throws SQLException, ClassNotFoundException {
		String sql = "select noOfCopies from tbl_book_copies where bookId = ? and branchId = ?";
		Object[] vals = { bookcopies.getBookId(), bookcopies.getBranchId() };
		ResultSet rs;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		populatePrepStatement(vals, pstmt);
		rs = pstmt.executeQuery();
		rs.next();
		return rs.getInt("noOfCopies");
	}

//	public Book getBookName(BookCopies bookcopies) throws SQLException, ClassNotFoundException{
//		return read("select title from tbl_book where bookId =?", new Object[] {bookcopies.getBookId()});
//	}

	public List<BookCopies> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookCopies> bookcopiesList = new ArrayList<>();
		// GenreDAO bdao = new GenreDAO(conn);
		while (rs.next()) {
			BookCopies bookcopies = new BookCopies();
			bookcopies.setBookId(rs.getInt("bookId"));
			bookcopies.setNoOfCopies(rs.getInt("noOfCopies"));
			// populate my books
			// LibraryBranch.setBooks(bdao.readFirstLevel("select * from tbl_book where
			// bookId IN ( select bookId from tbl_book_authors where authorId = ?)", new
			// Object[]{LibraryBranch.getLibraryBranchId()}));
			// LibraryBranchs.add(LibraryBranch);
			bookcopiesList.add(bookcopies);
		}
		return bookcopiesList;
	}

	public List<BookCopies> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookCopies> bookcopiesList = new ArrayList<>();
		while (rs.next()) {
			BookCopies bookcopies = new BookCopies();
			bookcopies.setBookId(rs.getInt("bookId"));
			bookcopies.setNoOfCopies(rs.getInt("noOfCopies"));
			bookcopiesList.add(bookcopies);
		}
		return bookcopiesList;
	}

}
