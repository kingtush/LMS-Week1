package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;

public class AdminService {

	ConnectionUtil connUtil = new ConnectionUtil();

	// READ ALL AUTHORS
	public List<Author> readAllAuthors() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAllAuthors();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to get all authors");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	// ADD A BOOK
	public String addBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			Integer bookId = bdao.addBookGetPK(book);
			// add authors
			for (Author a : book.getAuthors()) {
				bdao.addBookAuthors(bookId, a.getAuthorId());
			}
			// add genres
			for (Genre a : book.getGenres()) {
				bdao.addBookGenre(bookId, a.getGenreId());
			}

			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to get all authors");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public List<BookLoans> borrwerBooks(BookLoans bookLoan) throws SQLException {

		Connection conn = null;
		BookLoans books = new BookLoans();
		books.setBorrowerId(bookLoan.getBorrowerId());

		try {
			conn = connUtil.getConnection();
			BookLoansDAO copiesDAO = new BookLoansDAO(conn);
			return copiesDAO.userLoanList(books);
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

	// READ BRANCHES
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

			// System.out.println("finally");
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	// READ BORROWERS
	public List<Borrower> readAllBorrowers() throws SQLException {
		System.out.println("TRYING TO READ BORROWERS");
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO adao = new BorrowerDAO(conn);

			// System.out.println("made it here");
			return adao.readAllBorrowers();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to get all borrowers");
		} finally {

			// System.out.println("finally");
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	// READ PUBLISHERS
	public List<Publisher> readAllPublishers() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO adao = new PublisherDAO(conn);

			// System.out.println("made it here");
			return adao.readAllPublishers();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to get all borrowers");
		} finally {

			// System.out.println("finally");
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	// READ ALL GENRES
	public List<Genre> readAllGenres() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO adao = new GenreDAO(conn);

			// System.out.println("made it here");
			return adao.readAllGenres();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to get all borrowers");
		} finally {

			// System.out.println("finally");
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public List<BookCopies> readAllBookCopies() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookCopiesDAO adao = new BookCopiesDAO(conn);

			// System.out.println("made it here");
			return adao.readAllBookCopies();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to get all borrowers");
		} finally {

			// System.out.println("finally");
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	// GENRE FUNCTIONS
	// BEGIN HERE

	public void addGenre(Genre genre) throws SQLException {

		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO genreDAO = new GenreDAO(conn);
			genreDAO.addGenre(genre);

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

	public void updateGenre(Genre genre) throws SQLException {

		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO genreDAO = new GenreDAO(conn);
			genreDAO.updateGenre(genre);

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

	public void deleteGenre(Genre genre) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO genreDAO = new GenreDAO(conn);
			genreDAO.deleteGenre(genre);

			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to delete genre");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public boolean checkGenreId(Integer genreId) throws SQLException {
		Genre genre = new Genre();
		genre.setGenreId(genreId);
		Connection conn = null;
		boolean answer = false;
		try {
			conn = connUtil.getConnection();
			GenreDAO genreDAO = new GenreDAO(conn);
			answer = genreDAO.validateGenre(genre);
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

	public void addPublisher(Publisher publisher) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO publisherDAO = new PublisherDAO(conn);
			publisherDAO.addPublisher(publisher);

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

	public void deletePublisher(Publisher publisher) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO publisherDAO = new PublisherDAO(conn);
			publisherDAO.deletePublisher(publisher);

			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to delete publisher");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	public void updatePublisher(Publisher publisher) throws SQLException {

		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO publisherDAO = new PublisherDAO(conn);
			publisherDAO.updatePublisher(publisher);

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

	public boolean checkpublisherId(Integer publisherId) throws SQLException {
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		Connection conn = null;
		boolean answer = false;
		try {
			conn = connUtil.getConnection();
			PublisherDAO publisherDAO = new PublisherDAO(conn);
			answer = publisherDAO.validatePublisher(publisher);
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

	public void deleteBranch(Branch branch) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO branchDAO = new BranchDAO(conn);
			branchDAO.deleteLibraryBranch(branch);

			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to delete branch");
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

	public boolean checkBranchId(Integer branchId) throws SQLException {
		Branch branch = new Branch();
		branch.setBranchId(branchId);
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

	public boolean checkBorrowerId(Integer borrowerId) throws SQLException {
		Borrower borrower = new Borrower();
		borrower.setBorrowerId(borrowerId);
		Connection conn = null;
		boolean answer = false;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO borrowerDAO = new BorrowerDAO(conn);
			answer = borrowerDAO.validateBorrower(borrower);
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

	public void deleteBorrower(Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO borrowerDAO = new BorrowerDAO(conn);
			borrowerDAO.deleteBorrower(borrower);

			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to delete branch");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	public void addBorrower(Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO borrowerDAO = new BorrowerDAO(conn);
			borrowerDAO.addBorrower(borrower);

			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to add borrower");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	public void updateBorrower(Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO borrowerDAO = new BorrowerDAO(conn);
			borrowerDAO.updateBorrower(borrower);

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

	public void overrideLoan(BookLoans bookLoan) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			bldao.overrideLoan(bookLoan);

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

	public void addAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO authorDAO = new AuthorDAO(conn);
			authorDAO.addAuthor(author);

			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to add author");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	public boolean checkAuthorId(Integer authorId) throws SQLException {
		Author author = new Author();
		author.setAuthorId(authorId);
		Connection conn = null;
		boolean answer = false;
		try {
			conn = connUtil.getConnection();
			AuthorDAO authorDAO = new AuthorDAO(conn);
			answer = authorDAO.validateAuthor(author);
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

	public void deleteAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO authorDAO = new AuthorDAO(conn);
			authorDAO.deleteAuthor(author);

			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to delete branch");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	public void updateAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO authorDAO = new AuthorDAO(conn);
			authorDAO.updateAuthor(author);

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

}
