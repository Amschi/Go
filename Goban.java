package main;

import java.util.LinkedList;
import java.util.List;

public class Goban {
	
	private Stone[][] grid;
	private int row;
	private int col;
	
	private List<String> chainContainer;
	private boolean liberties;
	
	public Goban(int row, int col) {
		this.row = row;
		this.col = col;
		this.grid = new Stone[this.row][this.col];
		
		for (int r = 0 ; r < this.row ; r++) {
			for (int c = 0 ; c < this.col ; c++) {
				this.grid[r][c] = new Stone();
			}
		}
	}
	
	public void print() {
		for (int r = 0 ; r < this.row ; r++) {
			for (int c = 0 ; c < this.col ; c++) {
				System.out.print(this.grid[r][c].getColor());
			}
			
			System.out.println();
		}
	}
	
	private boolean isInGoban(int row, int col) {
		if (row < 0 || col < 0 || row >= this.row || col >= this.col) {
			return false;
		} else {
			return true;
		}
	}
	
	public int put(int row, int col, Stone stone) {
		if (isInGoban(row, col)) {
			if (!this.grid[row][col].isAlive()) {
				this.chainContainer = new LinkedList<String>();
				this.liberties = false;
				floodFill(row, col);
				
				if (!this.liberties) {
					System.out.println("C'est un suicide !");
					return -1;
				} else {
					this.grid[row][col] = stone;
					
					int killTotal = 0;
					
					if (isInGoban(row + 1, col) && this.grid[row + 1][col].isAlive() && this.grid[row + 1][col].getColor() != this.grid[row][col].getColor()) {
						this.chainContainer = new LinkedList<String>();
						this.liberties = false;
						floodFill(row + 1, col);
						
						if (!this.liberties) {
							killTotal += this.kill();
						}
					}
					
					if (isInGoban(row - 1, col) && this.grid[row - 1][col].isAlive() && this.grid[row - 1][col].getColor() != this.grid[row][col].getColor()) {
						this.chainContainer = new LinkedList<String>();
						this.liberties = false;
						floodFill(row - 1, col);
						
						if (!this.liberties) {
							killTotal += this.kill();
						}
					}
					
					if (isInGoban(row, col + 1) && this.grid[row][col + 1].isAlive() && this.grid[row][col + 1].getColor() != this.grid[row][col].getColor()) {
						this.chainContainer = new LinkedList<String>();
						this.liberties = false;
						floodFill(row, col + 1);
						
						if (!this.liberties) {
							killTotal += this.kill();
						}
					}
					
					if (isInGoban(row, col - 1) && this.grid[row][col - 1].isAlive() && this.grid[row][col - 1].getColor() != this.grid[row][col].getColor()) {
						this.chainContainer = new LinkedList<String>();
						this.liberties = false;
						floodFill(row, col - 1);
						
						if (!this.liberties) {
							killTotal += this.kill();
						}
					}
					
					return killTotal;
				}
			} else {
				System.out.println("Il y a deja un pion !");
				return -1;
			}
		} else {
			System.out.println("Vous etes en dehors du goban...");
			return -1;
		}
	}
	
	private void floodFill(int row, int col) {
		this.chainContainer.add(row + ";" + col);
		
		if (isInGoban(row + 1, col)) {
			if (!this.chainContainer.contains((row + 1) + ";" + col) && this.grid[row + 1][col].isAlive() && this.grid[row + 1][col].getColor() == this.grid[row][col].getColor()) {
				this.floodFill(row + 1, col);
			} else if (!this.grid[row + 1][col].isAlive()) {
				this.liberties = true;
			}
		}
		
		if (isInGoban(row - 1, col)) {
			if (!this.chainContainer.contains((row - 1) + ";" + col) && this.grid[row - 1][col].isAlive() && this.grid[row - 1][col].getColor() == this.grid[row][col].getColor()) {
				this.floodFill(row - 1, col);
			} else if (!this.grid[row - 1][col].isAlive()) {
				this.liberties = true;
			}
		}
		
		if (isInGoban(row, col + 1)) {
			if (!this.chainContainer.contains(row + ";" + (col + 1)) && this.grid[row][col + 1].isAlive() && this.grid[row][col + 1].getColor() == this.grid[row][col].getColor()) {
				this.floodFill(row, col + 1);
			} else if (!this.grid[row][col + 1].isAlive()) {
				this.liberties = true;
			}
		}
		
		if (isInGoban(row, col - 1)) {
			if (!this.chainContainer.contains(row + ";" + (col - 1)) && this.grid[row][col - 1].isAlive() && this.grid[row][col - 1].getColor() == this.grid[row][col].getColor()) {
				this.floodFill(row, col - 1);
			} else if (!this.grid[row][col - 1].isAlive()) {
				this.liberties = true;
			}
		}
	}
	
	private int kill() {
		int n = this.chainContainer.size();
				
		for(int i = 0 ; i < n ; i++) {
			String[] coordinates = this.chainContainer.get(i).split(";", 2);
			int row = Integer.parseInt(coordinates[0]);
			int col = Integer.parseInt(coordinates[1]);
			
			this.grid[row][col] = new Stone();
		}
		
		return n;
	}

}
