package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;

public class BorrowerService {

	ConnectionUtil connUtil = new ConnectionUtil();

	// Function to list all branches.
	public List<Branch> readAllBranches() throws SQLException {
		System.out.println("TRYING TO READ BRANCHES");
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO adao = new BranchDAO(conn);

			System.out.println("made it here");
			return adao.readAllBranches();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to get all branches");
		} finally {

			// System.out.println("Done Reading fam");
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}

	// Function to validate the borrower ID
	public boolean checkCardNo(Integer borrowerId) throws SQLException {
		Borrower b = new Borrower();
		b.setBorrowerId(borrowerId);
		Connection conn = null;
		boolean answer = false;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO borrowDAO = new BorrowerDAO(conn);
			answer = borrowDAO.validateBorrower(b);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to get all branches");
		} finally {

			// System.out.println("Done Reading fam");
			if (conn != null) {
				conn.close();
			}
		}
		return answer;
	}

	public void returnBook(BookLoans bookLoan) throws SQLException, ClassNotFoundException {
		System.out.println("Returning Book");
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);

			// System.out.println("made it here");

			bldao.updateBookLoan(bookLoan);
			bldao.updateAddCopies(bookLoan);

			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to get all branches");
		} finally {

			// System.out.println("Done Reading fam");
			if (conn != null) {
				conn.close();
			}
		}
	}

	// Function to list books currently loaned by borrower
	public List<BookLoans> borrwerBranchBooks(Borrower borrower, Integer branchId)
			throws SQLException, ClassNotFoundException {

		Connection conn = null;
		BookLoans books = new BookLoans();
		books.setBranchId(branchId);
		books.setBorrowerId(borrower.getBorrowerId());

		try {
			conn = connUtil.getConnection();
			BookLoansDAO copiesDAO = new BookLoansDAO(conn);
			return copiesDAO.readBorrowedBooks(books);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to get all branches");
		} finally {

			// System.out.println("Done Reading fam");
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}

	// Function to list all the books at a particular branch
	public List<BookCopies> branchBooks(Integer branchId) throws SQLException {
		Connection conn = null;
		BookCopies books = new BookCopies();
		books.setBranchId(branchId);

		try {
			conn = connUtil.getConnection();
			BookCopiesDAO copiesDAO = new BookCopiesDAO(conn);
			return copiesDAO.readAvailableBooksForBranch(books);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to get all branches");
		} finally {

			// System.out.println("Done Reading fam");
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}

	public String bookTitle(Integer bookId) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BookDAO bdao = new BookDAO(conn);
		return bdao.readBook(bookId);
	}

	public void addLoan(Borrower borrower, Integer branchId, Integer bookId)
			throws SQLException, ClassNotFoundException {
		BookLoans bookloan = new BookLoans();
		bookloan.setBorrowerId(borrower.getBorrowerId());
		bookloan.setBranchId(branchId);
		bookloan.setBookId(bookId);
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoansDAO bookLoansDao = new BookLoansDAO(conn);
			bookLoansDao.addBookLoan(bookloan);

			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to add loan");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		System.out.println("Successfully borrowed book");
	}

	// MORE WORK NEEDED
	public void deleteLoan(BookLoans bookloan) throws SQLException {
		bookloan.setBookId(8);
		bookloan.setBorrowerId(9);
		bookloan.setBranchId(8);

		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoansDAO bookLoansDao = new BookLoansDAO(conn);
			bookLoansDao.deleteBookLoan(bookloan);

			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to delete loan");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public boolean checkBranchId(Integer branchSelection) throws SQLException {
		Branch branch = new Branch();
		branch.setBranchId(branchSelection);
		Connection conn = null;
		boolean answer = false;
		try {
			conn = connUtil.getConnection();
			BranchDAO branchDAO = new BranchDAO(conn);
			answer = branchDAO.validateBranch(branch);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to get all branches");
		} finally {

			// System.out.println("Done Reading fam");
			if (conn != null) {
				conn.close();
			}
		}
		return answer;
	}
}
