package main;

public class Player {
	
	private char color;
	private int score;
	
	public Player(char color) {
		this.color = color;
	}
	
	public int put(int row, int col, Goban goban) {
		int scoreResult = goban.put(row, col, new Stone(this.color));
		
		if (scoreResult > 0) {
			this.score += scoreResult;
		}
		
		return scoreResult;
	}
	
	public int getScore() {
		return this.score;
	}

}
