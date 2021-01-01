package io.eymard;

import java.util.Scanner;

public class Decryptor2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		StartApp();
			
	}
	
	public static void StartApp() {
		App app = new App();
					
		Scanner scanner = new Scanner(System.in);
		app.ui(scanner);	
		
	}
}
