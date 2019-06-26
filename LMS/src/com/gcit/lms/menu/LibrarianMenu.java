package com.gcit.lms.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.service.LibrarianService;

public class LibrarianMenu {

	public static LibrarianService librarianService = new LibrarianService();
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	Scanner sc = new Scanner(System.in);

	public void level1() throws SQLException, IOException, ClassNotFoundException {
		// Integer quit=-1;
		System.out.println("1) Enter the ID for the Branch you manage: ");
		System.out.println("2) Return to previous page.");

		Integer selection = sc.nextInt();
		while (selection < 1 || selection > 2) {
			System.out.println("Invalid selection. Try again.");
			selection = sc.nextInt();
		}

		if (selection == 1) {
			level2();
		}
		if (selection == 2) {
			return;
		}

	}

	public void level2() throws SQLException, IOException, ClassNotFoundException {

		Integer finalBranch = 0;
		System.out.println("Pick the Branch you want to update: ");

		for (Branch a : librarianService.readAllBranches()) {
			System.out.println(a.getBranchId() + ") " + a.getBranchName() + " " + a.getBranchAddress());
			finalBranch = a.getBranchId() + 1;
		}
		System.out.println("Enter " + finalBranch + ") to return to previous.");

		Integer branchSelection = sc.nextInt();

		if (!(branchSelection == finalBranch)) {
			while (!librarianService.checkBranchId(branchSelection)) {
				System.out.println("Branch doesn't exist, try again: ");
				branchSelection = sc.nextInt();

			}
			level4(branchSelection);
		} else if (branchSelection == finalBranch) {
			level1();
		}

	}

//	public void level3() throws SQLException, IOException, ClassNotFoundException {
//		
//		System.out.println("Pick the Branch you want to update: ");
//		
//		for(Branch a: librarianService.readAllBranches()) {
//			System.out.println(a.getBranchId() +") " + a.getBranchName() + " " + a.getBranchAddress());
//			
//		}
//		Integer branchSelection = sc.nextInt();
//		while(!librarianService.checkBranchId(branchSelection)) {
//			System.out.println("Branch doesn't exist, try again: ");
//			branchSelection = sc.nextInt();
//		}
//		
//		level4(branchSelection);
//	}

	public void level4(Integer branchId) throws SQLException, IOException, ClassNotFoundException {
		System.out.println("1) Update the details of the Library");
		System.out.println("2) Add copies of book to the Library");
		System.out.println("3) Quit to previous");

		Integer selection = sc.nextInt();
		if (selection == 1) {
			Branch br = new Branch();
			br.setBranchId(branchId);
			System.out.println("You have chosen to update branch ");
			System.out.println("Please enter new branch name or enter N/A for no change: ");
			String input = "";
			input = reader.readLine();

			if (!(input.equals("N/A"))) {
				br.setBranchName(input);
			}

			System.out.println("Please enter new branch address or enter N/A for no change:");
			input = reader.readLine();
			if (!input.equals("N/A"))
				br.setBranchAddress(input);

			librarianService.updateBranch(br);
			level4(branchId);

		}
		if (selection == 2) {
			Integer quit = 0;
			System.out.println("Pick the book you want to add copies of to your branch: ");
			for (BookCopies a : librarianService.branchBooks(branchId)) {

				System.out.println(a.getBookId() + " " + librarianService.bookTitle(a.getBookId()) + " "
						+ a.getNoOfCopies() + " Copies left");
				quit = a.getBookId() + 1;
			}
			Integer bookId = Integer.parseInt(reader.readLine());
			if (bookId == quit) {
				return;
			}

			librarianService.addCopies(bookId, branchId);
			level4(branchId);

		}
		if (selection == 3) {
			level2();
		}
	}
}
