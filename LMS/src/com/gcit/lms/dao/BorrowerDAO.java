package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Borrower;

public class BorrowerDAO extends BaseDAO<Borrower> {

	public BorrowerDAO(Connection conn) {
		super(conn);
	}

	public void addBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("insert into tbl_borrower (name) values (?)", new Object[] { borrower.getBorrowerName() });
	}

	public void updateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("update tbl_borrower set name = ? where  cardNo= ?",
				new Object[] { borrower.getBorrowerName(), borrower.getBorrowerId() });
	}

	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("delete from tbl_borrower where cardNo = ?", new Object[] { borrower.getBorrowerId() });
	}

	public List<Borrower> readAllBorrowers() throws ClassNotFoundException, SQLException {
		return read("select * from tbl_borrower", null);
	}

	public boolean validateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		ResultSet rs = validateRecord("select * from tbl_borrower where cardNo = ?",
				new Object[] { borrower.getBorrowerId() });
		// System.out.println("made it here for validation");
		boolean notempty = false;
		while (rs.next()) {
			// ResultSet processing here
			notempty = true;
		}

		return notempty;
	}

	public List<Borrower> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Borrower> borrowers = new ArrayList<>();
		BookDAO bdao = new BookDAO(conn);
		while (rs.next()) {
			Borrower borrower = new Borrower();
			borrower.setBorrowerId(rs.getInt("cardNo"));
			borrower.setBorrowerName(rs.getString("name"));
			// borrower.setBorrowerAddress(rs.getString("address"));
			// populate my books
			// borrowers.setBooks(bdao.readFirstLevel("select * from tbl_book where bookId
			// IN ( select bookId from tbl_book_authors where authorId = ?)", new
			// Object[]{author.getBorrowerId()}));
			borrowers.add(borrower);
		}
		return borrowers;
	}

	public List<Borrower> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Borrower> borrowers = new ArrayList<>();
		while (rs.next()) {
			Borrower borrower = new Borrower();
			borrower.setBorrowerId(rs.getInt("cardNo"));
			borrower.setBorrowerName(rs.getString("name"));
			// borrower.setBorrowerAddress(rs.getString("address"));
			borrowers.add(borrower);
		}
		return borrowers;
	}
}
