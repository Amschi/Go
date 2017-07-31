package main;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Go {
	
	private Goban goban;
	private Player[] players = {new Player('N'), new Player('B')};
	
	private int turns = 0;
	private int skipTurn = 0;
	
	private Scanner scanner;
	
	public Go() {
		System.out.println("==== Jeu de Go ==== (Amaury Schillio)\n");
		
		System.out.print("Choisissez la taille du goban : ");
		
		this.scanner = new Scanner(System.in);
		int size = this.scanner.nextInt();
		
		this.goban = new Goban(size, size);
	}
	
	public void play() {
		this.scanner = new Scanner(System.in);
		
		do {
			System.out.println();
			this.goban.print();
			
			if (this.turns % 2 == 0) {
				System.out.print("\nTour " + this.turns + " > Noir : ");
			} else {
				System.out.print("\nTour " + this.turns + " > Blanc : ");
			}
			
			String response = this.scanner.nextLine();
			
			if (Pattern.matches("^[0-9]+;[0-9]+$", response)) {
				String[] coordinates = response.split(";", 2);
				int row = Integer.parseInt(coordinates[0]);
				int col = Integer.parseInt(coordinates[1]);
				
				int scoreResult;
				
				if (this.turns % 2 == 0) {
					scoreResult = this.players[0].put(row, col, this.goban);
				} else {
					scoreResult = this.players[1].put(row, col, this.goban);
				}
				
				if(scoreResult >= 0) {
					this.skipTurn = 0;
					this.turns++;
				}
			} else if (response.equals("passer")) {
				this.skipTurn++;
				this.turns++;
			} else if (response.equals("quitter")) {
				break;
			} else {
				System.out.println("Commande inconnue...");
			}
			
		} while (skipTurn < 2);
		
		System.out.println("\nResultat de la partie :");
		System.out.println("- Score de noir : " + this.players[0].getScore());
		System.out.println("- Score de blanc : " + this.players[1].getScore());
		
	}
	
}
