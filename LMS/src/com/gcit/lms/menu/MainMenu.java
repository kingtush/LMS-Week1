package com.gcit.lms.menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {

	public void menu() throws SQLException, ClassNotFoundException, IOException {
		System.out.println("Welcome to the GCIT Library Management System. Which category of a user are you?");
		System.out.println("1)Librarian\n" + "2)Administrator\n" + "3)Borrower:\n");

		Scanner sc = new Scanner(System.in);
		Integer selection = sc.nextInt();
		while (selection > 4 || selection < 1) {
			System.out.println("invalid selection. Enter a digit based on the given options");
			selection = sc.nextInt();
		}

		if (selection == 1) {
			openLibrarian();
		}
		if (selection == 2) {
			openAdmin();
		}
		if (selection == 3) {
			openBorrower();
		}
		if (selection == 4) {
			System.exit(0);
		}
	}

	public void openBorrower() throws SQLException, ClassNotFoundException, IOException {
		BorrowerMenu borrowMenu = new BorrowerMenu();
		borrowMenu.level1();

	}

	public void openLibrarian() throws SQLException, IOException, ClassNotFoundException {
		LibrarianMenu librarianMenu = new LibrarianMenu();
		librarianMenu.level1();

	}

	public void openAdmin() throws SQLException, ClassNotFoundException, NumberFormatException, IOException {
		AdminMenu adminMenu = new AdminMenu();
		adminMenu.level1();

	}

}
