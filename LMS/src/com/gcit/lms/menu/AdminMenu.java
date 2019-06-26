package com.gcit.lms.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.service.BorrowerService;
import com.gcit.lms.service.ConnectionUtil;

public class AdminMenu {

	public static AdminService adminService = new AdminService();
	public static BorrowerService borrowService = new BorrowerService();
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public void level1() throws ClassNotFoundException, SQLException, NumberFormatException, IOException {
		System.out.println("What table would you like to manipulate?");
		System.out.println("1) Genre");
		System.out.println("2) Publisher");
		System.out.println("3) Borrower");
		System.out.println("4) Branch");
		System.out.println("5) Override ");
		System.out.println("6) Author ");

		Integer selection = Integer.parseInt(reader.readLine());

		if (selection == 1) {
			genreManipulation();
			return;
		}
		if (selection == 2) {
			publisherManipulation();
			return;
		}
		if (selection == 3) {
			borrowerManipulation();
			return;
		}
		if (selection == 4) {
			branchManipulation();
			return;
		}
		if (selection == 5) {
			overrideBookLoan();
		}
		if (selection == 6) {
			authorManipulation();
		}
		if (selection == 7) {
			return;
		}
	}

	public void overrideBookLoan() throws SQLException, NumberFormatException, IOException, ClassNotFoundException {
		System.out.println("You've chosen to override book loans. First, select a user: ");
		for (Borrower a : adminService.readAllBorrowers()) {
			System.out.println(a.getBorrowerId() + ") " + a.getBorrowerName());
		}
		System.out.println("\n");

		Integer borrowerId = Integer.parseInt(reader.readLine());

		BookLoans borrowerLoans = new BookLoans();
		borrowerLoans.setBorrowerId(borrowerId);
		while (!adminService.checkBorrowerId(borrowerId)) {
			System.out.println("That Borrower doesn't exist. Try again.");
			borrowerId = Integer.parseInt(reader.readLine());
		}

		for (BookLoans a : adminService.borrwerBooks(borrowerLoans)) {
			System.out.println(a.getBookId() + "-- " + a.getBranchId() + "- " + borrowService.bookTitle(a.getBookId()));
			// quitNo = a.getBookId()+1;
		}
		System.out.println("Enter the bookId: ");
		Integer bookId = Integer.parseInt(reader.readLine());

		borrowerLoans.setBookId(bookId);

		System.out.println("Enter the branchId: ");
		Integer branchId = Integer.parseInt(reader.readLine());

		borrowerLoans.setBranchId(branchId);

		level1();

	}

	// Genre Stuff
	public void genreManipulation() throws SQLException, ClassNotFoundException, NumberFormatException, IOException {
		System.out.println("What do you want to do to the Genre table?");
		System.out.println("1)Add\n" + "2)Update\n" + "3)Delete\n" + "4)Read.");

		Integer selection = Integer.parseInt(reader.readLine());

		while (selection < 1 || selection > 4) {
			System.out.println("Invalid selection. Try again: ");
			selection = Integer.parseInt(reader.readLine());
		}

		if (selection == 1) {
			System.out.println("Enter the new genre name: ");
			String genreName = reader.readLine();
			Genre genre = new Genre();
			genre.setGenreName(genreName);
			adminService.addGenre(genre);
			System.out.println("\n");
			level1();
		}
		if (selection == 3) {
			System.out.println("Enter the ID of the Genre you would like to delete: ");

			for (Genre a : adminService.readAllGenres()) {
				System.out.println(a.getGenreId() + ") " + a.getGenreName());
			}
			Integer genreId = Integer.parseInt(reader.readLine());
			while (!adminService.checkGenreId(genreId)) {
				System.out.println("That Genre doesn't exist. Try again.");
				genreId = Integer.parseInt(reader.readLine());
			}
			Genre genre = new Genre();
			genre.setGenreId(genreId);
			adminService.deleteGenre(genre);
			System.out.println("\n");
			level1();
		}

		if (selection == 2) {
			System.out.println("Enter the Id of the Genre you would like to update: ");
			for (Genre a : adminService.readAllGenres()) {
				System.out.println(a.getGenreId() + ") " + a.getGenreName());
			}
			Integer genreId = Integer.parseInt(reader.readLine());
			while (!adminService.checkGenreId(genreId)) {
				System.out.println("That Genre doesn't exist. Try again.");
				genreId = Integer.parseInt(reader.readLine());
			}
			Genre genre = new Genre();
			genre.setGenreId(genreId);

			System.out.println("Enter the new name of the Genre: ");
			String newGenre = reader.readLine();
			genre.setGenreName(newGenre);
			adminService.updateGenre(genre);
			System.out.println("\n");
			level1();
		}

		if (selection == 4) {
			System.out.println("Here is a list of the Genres");
			for (Genre a : adminService.readAllGenres()) {
				System.out.println(a.getGenreId() + ") " + a.getGenreName());

			}
			System.out.println("\n");
			level1();
		}
	}

	public void publisherManipulation()
			throws SQLException, ClassNotFoundException, NumberFormatException, IOException {
		System.out.println("What do you want to do to the Publisher table?");
		System.out.println("1)Add\n" + "2)Update\n" + "3)Delete\n" + "4)Read.");

		Integer selection = Integer.parseInt(reader.readLine());

		while (selection < 1 || selection > 4) {
			System.out.println("Invalid selection. Try again: ");
			selection = Integer.parseInt(reader.readLine());
		}

		if (selection == 1) {
			System.out.println("Enter the Publisher name: ");
			String publisherName = reader.readLine();
			Publisher publisher = new Publisher();
			publisher.setPublisherName(publisherName);
			adminService.addPublisher(publisher);
			System.out.println("\n");
			level1();
		}
		if (selection == 3) {
			System.out.println("Enter the ID of the Publisher you would like to delete: ");

			for (Publisher a : adminService.readAllPublishers()) {
				System.out.println(a.getPublisherId() + ") " + a.getPublisherName());
			}
			Integer publisherId = Integer.parseInt(reader.readLine());
			while (!adminService.checkGenreId(publisherId)) {
				System.out.println("That Publisher doesn't exist. Try again.");
				publisherId = Integer.parseInt(reader.readLine());
			}
			Publisher publisher = new Publisher();
			publisher.setPublisherId(publisherId);
			adminService.deletePublisher(publisher);
			System.out.println("\n");
			level1();
		}

		if (selection == 2) {
			System.out.println("Enter the Id of the Publisher you would like to update: ");
			for (Publisher a : adminService.readAllPublishers()) {
				System.out.println(a.getPublisherId() + ") " + a.getPublisherName());
			}
			Integer publisherId = Integer.parseInt(reader.readLine());
			while (!adminService.checkpublisherId(publisherId)) {
				System.out.println("That Publisher doesn't exist. Try again.");
				publisherId = Integer.parseInt(reader.readLine());
			}
			Publisher publisher = new Publisher();
			publisher.setPublisherId(publisherId);

			System.out.println("Enter the new name of the Publisher: ");
			String newPublisher = reader.readLine();
			publisher.setPublisherName(newPublisher);
			adminService.updatePublisher(publisher);
			System.out.println("\n");
			level1();
		}

		if (selection == 4) {
			System.out.println("Here is a list of the Publishers");
			for (Publisher a : adminService.readAllPublishers()) {
				System.out.println(a.getPublisherId() + ") " + a.getPublisherName());

			}
			System.out.println("\n");
			level1();
		}
	}

	public void branchManipulation() throws SQLException, ClassNotFoundException, IOException {
		System.out.println("What do you want to do to the Branch table?");
		System.out.println("1)Add\n" + "2)Update\n" + "3)Delete\n" + "4)Read.");

		Integer selection = Integer.parseInt(reader.readLine());

		while (selection < 1 || selection > 4) {
			System.out.println("Invalid selection. Try again: ");
			selection = Integer.parseInt(reader.readLine());
		}

		if (selection == 1) {
			System.out.println("Enter the Branch name: ");
			String branchName = reader.readLine();
			Branch branch = new Branch();
			branch.setBranchName(branchName);
			adminService.addBranch(branch);
			System.out.println("\n");
			level1();
		}

		if (selection == 3) {
			System.out.println("Enter the ID of the Branch you would like to delete: ");

			for (Branch a : adminService.readAllBranches()) {
				System.out.println(a.getBranchId() + ") " + a.getBranchName());
			}
			Integer branchId = Integer.parseInt(reader.readLine());
			while (!adminService.checkGenreId(branchId)) {
				System.out.println("That Genre doesn't exist. Try again.");
				branchId = Integer.parseInt(reader.readLine());
			}
			Branch branch = new Branch();
			branch.setBranchId(branchId);
			adminService.deleteBranch(branch);
			System.out.println("\n");
			level1();
		}

		if (selection == 2) {
			System.out.println("Enter the Id of the Branch you would like to update: ");
			for (Branch a : adminService.readAllBranches()) {
				System.out.println(a.getBranchId() + ") " + a.getBranchName());
			}
			Integer branchId = Integer.parseInt(reader.readLine());
			while (!adminService.checkBranchId(branchId)) {
				System.out.println("That Branch doesn't exist. Try again.");
				branchId = Integer.parseInt(reader.readLine());
			}
			Branch branch = new Branch();
			branch.setBranchId(branchId);

			System.out.println("Enter the new name of the Branch: ");
			String newBranch = reader.readLine();
			branch.setBranchName(newBranch);
			adminService.updateBranch(branch);
			System.out.println("\n");
			level1();
		}

		if (selection == 4) {
			System.out.println("Here is a list of the Branches");
			for (Branch a : adminService.readAllBranches()) {
				System.out.println(a.getBranchId() + ") " + a.getBranchName());
			}
			System.out.println("\n");
			level1();
		}
	}

	public void borrowerManipulation() throws SQLException, ClassNotFoundException, IOException {
		System.out.println("What do you want to do to the Borrowers table?");
		System.out.println("1)Add\n" + "2)Update\n" + "3)Delete\n" + "4)Read.");

		Integer selection = Integer.parseInt(reader.readLine());

		while (selection < 1 || selection > 4) {
			System.out.println("Invalid selection. Try again: ");
			selection = Integer.parseInt(reader.readLine());
		}

		if (selection == 1) {
			System.out.println("Enter the Borrower name: ");
			String borrowerName = reader.readLine();
			Borrower borrower = new Borrower();
			borrower.setBorrowerName(borrowerName);
			adminService.addBorrower(borrower);
			System.out.println("\n");
			level1();
		}
		if (selection == 3) {
			System.out.println("Enter the ID of the Borrower you would like to delete: ");

			for (Borrower a : adminService.readAllBorrowers()) {
				System.out.println(a.getBorrowerId() + ") " + a.getBorrowerName());
			}
			Integer borrowerId = Integer.parseInt(reader.readLine());
			while (!adminService.checkBorrowerId(borrowerId)) {
				System.out.println("That Borrower doesn't exist. Try again.");
				borrowerId = Integer.parseInt(reader.readLine());
			}
			Borrower borrower = new Borrower();
			borrower.setBorrowerId(borrowerId);
			adminService.deleteBorrower(borrower);
			System.out.println("\n");
			level1();
		}

		if (selection == 2) {
			System.out.println("Enter the Id of the Borrower you would like to update: ");
			for (Borrower a : adminService.readAllBorrowers()) {
				System.out.println(a.getBorrowerId() + ") " + a.getBorrowerName());
			}
			Integer borrowerId = Integer.parseInt(reader.readLine());
			while (!adminService.checkBorrowerId(borrowerId)) {
				System.out.println("That Borrower doesn't exist. Try again.");
				borrowerId = Integer.parseInt(reader.readLine());
			}
			Borrower borrower = new Borrower();
			borrower.setBorrowerId(borrowerId);

			System.out.println("Enter the new name of the Borrower: ");
			String newBorrower = reader.readLine();
			borrower.setBorrowerName(newBorrower);
			adminService.updateBorrower(borrower);
			System.out.println("\n");
			level1();
		}

		if (selection == 4) {
			System.out.println("Here is a list of the Borrowers");
			for (Borrower a : adminService.readAllBorrowers()) {
				System.out.println(a.getBorrowerId() + ") " + a.getBorrowerName());
			}
			System.out.println("\n");
			level1();
		}
	}

	public void authorManipulation() throws SQLException, ClassNotFoundException, IOException {
		System.out.println("What do you want to do to the Authors table?");
		System.out.println("1)Add\n" + "2)Update\n" + "3)Delete\n" + "4)Read.");

		Integer selection = Integer.parseInt(reader.readLine());

		while (selection < 1 || selection > 4) {
			System.out.println("Invalid selection. Try again: ");
			selection = Integer.parseInt(reader.readLine());
		}

		if (selection == 1) {
			System.out.println("Enter the Author name: ");
			String authorName = reader.readLine();
			Author author = new Author();
			author.setAuthorName(authorName);
			adminService.addAuthor(author);
			System.out.println("\n");
			level1();
		}
		if (selection == 3) {
			System.out.println("Enter the ID of the Author you would like to delete: ");

			for (Author a : adminService.readAllAuthors()) {
				System.out.println(a.getAuthorId() + ") " + a.getAuthorName());
			}
			Integer authorId = Integer.parseInt(reader.readLine());
			while (!adminService.checkAuthorId(authorId)) {
				System.out.println("That Author doesn't exist. Try again.");
				authorId = Integer.parseInt(reader.readLine());
			}
			Author author = new Author();
			author.setAuthorId(authorId);
			adminService.deleteAuthor(author);
			System.out.println("\n");
			level1();
		}

		if (selection == 2) {
			System.out.println("Enter the Id of the Author you would like to update: ");
			for (Author a : adminService.readAllAuthors()) {
				System.out.println(a.getAuthorId() + ") " + a.getAuthorName());
			}
			Integer authorId = Integer.parseInt(reader.readLine());
			while (!adminService.checkAuthorId(authorId)) {
				System.out.println("That Author doesn't exist. Try again.");
				authorId = Integer.parseInt(reader.readLine());
			}
			Author author = new Author();
			author.setAuthorId(authorId);

			System.out.println("Enter the new name of the Author: ");
			String newAuthor = reader.readLine();
			author.setAuthorName(newAuthor);
			adminService.updateAuthor(author);
			System.out.println("\n");
			level1();
		}

		if (selection == 4) {
			System.out.println("Here is a list of the Authors");
			for (Author a : adminService.readAllAuthors()) {
				System.out.println(a.getAuthorId() + ") " + a.getAuthorName());
			}
			System.out.println("\n");
			level1();
		}
	}

	public void bookManipulation() throws SQLException, ClassNotFoundException, IOException {
		System.out.println("What do you want to do to the Book table?");
		System.out.println("1)Add \n" + "2)Update\n" + "3)Delete\n" + "4)Read.");

		Integer selection = Integer.parseInt(reader.readLine());

		while (selection < 1 || selection > 4) {
			System.out.println("Invalid selection. Try again: ");
			selection = Integer.parseInt(reader.readLine());
		}

		if (selection == 1) {
			System.out.println("Enter the Author name: ");
			String authorName = reader.readLine();
			Author author = new Author();
			author.setAuthorName(authorName);
			adminService.addAuthor(author);
			System.out.println("\n");
			level1();
		}
		if (selection == 3) {
			System.out.println("Enter the ID of the Author you would like to delete: ");

			for (Author a : adminService.readAllAuthors()) {
				System.out.println(a.getAuthorId() + ") " + a.getAuthorName());
			}
			Integer authorId = Integer.parseInt(reader.readLine());
			while (!adminService.checkAuthorId(authorId)) {
				System.out.println("That Author doesn't exist. Try again.");
				authorId = Integer.parseInt(reader.readLine());
			}
			Author author = new Author();
			author.setAuthorId(authorId);
			adminService.deleteAuthor(author);
			System.out.println("\n");
			level1();
		}

		if (selection == 2) {
			System.out.println("Enter the Id of the Author you would like to update: ");
			for (Author a : adminService.readAllAuthors()) {
				System.out.println(a.getAuthorId() + ") " + a.getAuthorName());
			}
			Integer authorId = Integer.parseInt(reader.readLine());
			while (!adminService.checkAuthorId(authorId)) {
				System.out.println("That Author doesn't exist. Try again.");
				authorId = Integer.parseInt(reader.readLine());
			}
			Author author = new Author();
			author.setAuthorId(authorId);

			System.out.println("Enter the new name of the Author: ");
			String newAuthor = reader.readLine();
			author.setAuthorName(newAuthor);
			adminService.updateAuthor(author);
			System.out.println("\n");
			level1();
		}

		if (selection == 4) {
			System.out.println("Here is a list of the Authors");
			for (Author a : adminService.readAllAuthors()) {
				System.out.println(a.getAuthorId() + ") " + a.getAuthorName());
			}
			System.out.println("\n");
			level1();
		}
	}

}
