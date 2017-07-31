package main;

public class Stone {
	
	private boolean alive;
	private char color;
	
	public Stone(char color) {
		this.alive = true;
		this.color = color;
	}
	
	public Stone() {
		this.alive = false;
		this.color = '_';
	}
	
	public boolean isAlive() {
		return alive;
	}

	public char getColor() {
		return color;
	}

}
