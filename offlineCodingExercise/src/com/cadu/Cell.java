package com.cadu;

import java.util.ArrayList;
import java.util.List;

public class Cell {

	private boolean alive = false;
	
	private boolean newValue = false;
	
	private int positionX;
	
	private int positionY;
	
	private String cause = "i";
	
	public Cell(int positionX, int positionY) {
		super();
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public boolean isNewValue() {
		return newValue;
	}

	public void setNewValue(boolean newValue) {
		this.newValue = newValue;
	}

	private List<Cell> neighbours =  new ArrayList<Cell>();
	
	public void nextGeneration(){
		
		int neighbourAlive = 0;
		
		for(Cell neighbour : neighbours){
			if(neighbour.isAlive()){
				neighbourAlive++;
			}
		}
		
		this.newValue = this.alive;
		
		if(this.alive){
			if(neighbourAlive < 2){
				this.newValue = false;
				cause="u";
			}else if(neighbourAlive > 3){
				this.newValue = false;
				cause="o";
			}else{
				cause="c";
			}
			
		}else{
			if(neighbourAlive == 3){
				this.newValue = true;
				cause="r";
			}
		}
	}
	
	public void update(){
		this.alive = newValue;
	}

	public List<Cell> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(List<Cell> neighbours) {
		this.neighbours = neighbours;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public String getCause() {
		return cause;
	}
}
