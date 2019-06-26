package com.gcit.lms.menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.service.BorrowerService;

public class BorrowerMenu {

	BorrowerService borrowService = new BorrowerService();
	MainMenu mainMenu = new MainMenu();

	public void level1() throws SQLException, ClassNotFoundException, IOException {
		System.out.println("Enter your card No: ");
		Scanner sc = new Scanner(System.in);
		Integer borrowerId = sc.nextInt();
		while (!borrowService.checkCardNo(borrowerId)) {
			System.out.println("User not found: Try again: ");
			borrowerId = sc.nextInt();

			if (borrowService.checkCardNo(borrowerId)) {
				break;
			}
		}
		Borrower borrower = new Borrower();
		borrower.setBorrowerId(borrowerId);
		level2(borrower);
		// System.out.println("Logged In");

	}

	public void level2(Borrower borrower) throws SQLException, ClassNotFoundException, IOException {
		Integer quitNo = 0;

		System.out.println("1)Check Out a book\n" + "2)Return a book\n" + "3)Quit to previous");

		Scanner sc = new Scanner(System.in);
		Integer selection = sc.nextInt();

		while (selection < 1 || selection > 3) {
			System.out.println("Invalid selection. Try again: ");
			selection = sc.nextInt();
		}
		if (selection == 1) {
			borrowBook(borrower);
		}
		if (selection == 2) {

			returnBook(borrower);
		}
		if (selection == 3) {
			return;
		}
		mainMenu.menu();
	}

	public void borrowBook(Borrower borrower) throws SQLException, ClassNotFoundException, IOException {
		Integer quitNo = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Pick the Branch you want to check out from(Enter the ID number): ");

		for (Branch a : borrowService.readAllBranches()) {
			System.out.println(a.getBranchId() + ") " + a.getBranchName());
			quitNo = a.getBranchId();
		}

		System.out.println((quitNo + 1) + ") Quit");
		Integer branchSelection = sc.nextInt();
		if (branchSelection == (quitNo + 1)) {
			level2(borrower);
		}
		while (!borrowService.checkBranchId(branchSelection)) {
			System.out.println("Branch doesn't exist, try again: ");
			branchSelection = sc.nextInt();
		}
		System.out.println("Pick the book you want to checkout");
		for (BookCopies a : borrowService.branchBooks(branchSelection)) {

			System.out.println(a.getBookId() + " " + borrowService.bookTitle(a.getBookId()) + "-> " + a.getNoOfCopies()
					+ " Copies left");
			quitNo = a.getBookId() + 1;
		}

		Integer bookSelect = sc.nextInt();

		if (bookSelect == quitNo) {
			level2(borrower);
			return;
		}
		borrowService.addLoan(borrower, branchSelection, bookSelect);

	}

	public void returnBook(Borrower borrower) throws SQLException, ClassNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		Integer quitNo = 0;
		System.out.println("Pick the Branch you want to Return to from(Enter the ID number): ");

		for (Branch a : borrowService.readAllBranches()) {
			System.out.println(a.getBranchId() + ") " + a.getBranchName());
			quitNo = a.getBranchId() + 1;
		}
		System.out.println("Enter " + quitNo + "to return to previous Menu.");
		Integer branch = sc.nextInt();

		if (branch == quitNo) {
			return;
		}

		System.out.println("Which book would you like to return?(Enter the ID)");

		for (BookLoans a : borrowService.borrwerBranchBooks(borrower, branch)) {
			System.out.println(a.getBookId() + " " + borrowService.bookTitle(a.getBookId()));
			quitNo = a.getBookId() + 1;
		}
		System.out.println("Enter " + quitNo + "to return to previous Menu.");
		Integer returnBookId = sc.nextInt();
		if (returnBookId == quitNo) {
			return;
		}

		BookLoans loan = new BookLoans();
		loan.setBookId(returnBookId);
		loan.setBorrowerId(borrower.getBorrowerId());
		loan.setBranchId(branch);
		borrowService.returnBook(loan);

		level2(borrower);

	}
}
