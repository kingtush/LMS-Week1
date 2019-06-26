package com.gcit.lms.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Branch;

public class LibrarianService {

	ConnectionUtil connUtil = new ConnectionUtil();
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public List<Branch> readAllBranches() throws SQLException {
		System.out.println("TRYING TO READ BRANCHES");
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO adao = new BranchDAO(conn);

			// System.out.println("made it here");
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

	public void addBranch(Branch branch) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO branchDAO = new BranchDAO(conn);
			branchDAO.addLibraryBranch(branch);

			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to add publisher");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	public void updateBranch(Branch branch) throws SQLException {

		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO branchDAO = new BranchDAO(conn);
			branchDAO.updateLibraryBranch(branch);

			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to add genre");
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

	public List<BookCopies> branchBooks(Integer branchId) throws SQLException {
		Connection conn = null;
		BookCopies books = new BookCopies();
		books.setBranchId(branchId);

		try {
			conn = connUtil.getConnection();
			BookCopiesDAO copiesDAO = new BookCopiesDAO(conn);
			return copiesDAO.readAllBooksForBranch(books);
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

	public void addCopies(Integer bookId, Integer branchId)
			throws ClassNotFoundException, SQLException, NumberFormatException, IOException {

		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			BookCopies bcopies = new BookCopies();
			bcopies.setBookId(bookId);
			bcopies.setBranchId(branchId);
			Integer currentCopies = bcdao.getCopies(bcopies);
			System.out.println(bookTitle(bookId) + " " + currentCopies);
			System.out.println("Enter the new number of Copies: ");
			Integer newCopies = Integer.parseInt(reader.readLine());
			bcopies.setNoOfCopies(newCopies);
			bcdao.updateBookCopies(bcopies);

			conn.commit();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			// System.out.println("Done Reading fam");
			if (conn != null) {
				conn.close();
			}

		}

		/*
		 * public boolean checkBranchId(Integer branchId) throws SQLException { Branch
		 * branch = new Branch(); branch.setBranchId(branchId); Connection conn = null;
		 * boolean answer = false; try { conn = connUtil.getConnection(); BranchDAO
		 * branchDAO= new BranchDAO(conn); answer = branchDAO.validateBranch(branch); }
		 * catch (ClassNotFoundException e) { e.printStackTrace();
		 * System.out.println("Something went wrong. Failed to get all branches"); }
		 * finally {
		 * 
		 * //System.out.println("Done Reading fam"); if (conn != null) { conn.close(); }
		 * } return answer; }
		 */

	}
}
