package com.gcit.lms.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.gcit.lms.menu.MainMenu;

public class Driver {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Scanner sc = new Scanner(System.in);
		MainMenu mainmenu = new MainMenu();
		boolean keepRun = true;
		String ans = "";
		while (keepRun) {

			mainmenu.menu();
			System.out.println("Return to main menu?: ");
			ans = sc.nextLine();

			if (ans.equals("no")) {
				keepRun = false;
			}
		}
	}
}
