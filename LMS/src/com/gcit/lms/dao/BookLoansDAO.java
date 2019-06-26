package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoans;

public class BookLoansDAO extends BaseDAO<BookLoans> {

	public BookLoansDAO(Connection conn) {
		super(conn);
	}

	public void addBookLoan(BookLoans bookLoan) throws ClassNotFoundException, SQLException {
		save("insert into tbl_book_loans (bookId,branchId,cardNo,dateOut,dueDate) values (?,?,?,now(),now()+ interval 7 day)",
				new Object[] { bookLoan.getBookId(), bookLoan.getBranchId(), bookLoan.getBorrowerId() });
		updateSubtractCopies(bookLoan);
		// INSERT INTO `library`.`tbl_book_loans` (`bookId`, `branchId`, `cardNo`,
		// `dateOut`,`dueDate`)
		// VALUES ('8', '8', '9', curdate(), curdate()+ interval 7 day );
	}

	public Integer addBookGetPK(Book book) throws ClassNotFoundException, SQLException {
		return saveWithID("insert into tbl_book_loans (title) values (?)", new Object[] { book.getTitle() });
	}

	// TO BE MOVED TO BOOK COPIES
	public void updateAddCopies(BookLoans bookloan) throws ClassNotFoundException, SQLException {
		save("update tbl_book_copies set noOfCopies = noOfCopies+1 where branchId =? and bookId = ?",
				new Object[] { bookloan.getBranchId(), bookloan.getBookId() });
	}

	// TO BE MOVED TO BOOK COPIES
	public void updateSubtractCopies(BookLoans bookloan) throws ClassNotFoundException, SQLException {
		save("update tbl_book_copies set noOfCopies = noOfCopies-1 where branchId =? and bookId = ?",
				new Object[] { bookloan.getBranchId(), bookloan.getBookId() });
	}

	public void updateBookLoan(BookLoans bookLoan) throws ClassNotFoundException, SQLException {
		save("update tbl_book_loans set dateIn = now() where bookId = ? and cardNo = ? and branchId = ?",
				new Object[] { bookLoan.getBookId(), bookLoan.getBorrowerId(), bookLoan.getBranchId() });
	}

	public void deleteBookLoan(BookLoans bookloan) throws ClassNotFoundException, SQLException {
		save("delete from tbl_book_loans where bookId = ? and branchId =? and cardNo = ?",
				new Object[] { bookloan.getBookId(), bookloan.getBranchId(), bookloan.getBorrowerId() });
		updateAddCopies(bookloan);
	}

	public List<BookLoans> userLoanList(BookLoans bookLoan) throws ClassNotFoundException, SQLException {
		return read("select * from tbl_book_loans where cardNo = ? and dateIn is null",
				new Object[] { bookLoan.getBorrowerId() });
	}

	public List<BookLoans> readBorrowedBooks(BookLoans bookLoan) throws ClassNotFoundException, SQLException {
		return read("select * from tbl_book_loans where cardNo =? and branchId = ? and dateIn is null",
				new Object[] { bookLoan.getBorrowerId(), bookLoan.getBranchId() });
	}

	public List<BookLoans> readAllBookLoans() throws ClassNotFoundException, SQLException {
		return read("select * from tbl_book_loans", null);
	}

	public List<BookLoans> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookLoans> bookLoans = new ArrayList<>();
		// AuthorDAO adao = new AuthorDAO(conn);
		while (rs.next()) {
			BookLoans bookLoan = new BookLoans();
			bookLoan.setBookId(rs.getInt("bookId"));
			bookLoan.setBorrowerId(rs.getInt("cardNo"));
			bookLoan.setBranchId(rs.getInt("branchId"));
			// populate all child entities (genres, authors, branches etc)
			// book.setAuthors(adao.read("select * from tbl_author where authorId IN (
			// select authorId from tbl_book_authors where bookId = ?)", new Object[]
			// {book.getBookId()}));
			bookLoans.add(bookLoan);
		}
		return bookLoans;
	}

	public List<BookLoans> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookLoans> bookLoans = new ArrayList<>();
		while (rs.next()) {
			BookLoans bookLoan = new BookLoans();
			bookLoan.setBookId(rs.getInt("bookId"));
			bookLoan.setBorrowerId(rs.getInt("cardNo"));
			bookLoans.add(bookLoan);
		}
		return bookLoans;
	}

	public void overrideLoan(BookLoans bookLoan) throws ClassNotFoundException, SQLException {

		save("update tbl_book_loans set dueDate = dueDate + interval 7 day where cardNo =? and bookId = ? and branchId=?",
				new Object[] { bookLoan.getBorrowerId(), bookLoan.getBookId(), bookLoan.getBranchId() });
	}

}
