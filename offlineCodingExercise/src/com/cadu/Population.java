package com.cadu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Population {
	
	private Integer size;
	
	private Integer neighborsDegree;
	
	private int liveCells;
	
	private int deadCells;
	
	private long generation = 1;
	
	ArrayList<String> initialPopulation;
	
	Cell [][] populationArray;
	
	List<Cell> populationList = new ArrayList<Cell>();
	
	
	public Population(Integer size, Integer neighborsDegree, ArrayList<String> initialPopulation) {
		super();
		this.size = size;
		this.neighborsDegree = neighborsDegree;
		this.initialPopulation = initialPopulation;
		init();
	}

	public void nextGeneration(){
		liveCells = 0;
		deadCells = 0;
		for(Cell cell : populationList){
			cell.nextGeneration();
			if(cell.isAlive()){
				liveCells++;
			}else{
				deadCells++;
			}
		}
		
		generation++;
		
	}
	
	private void init(){
		populationArray = new Cell[size][size];
		
		for(int x=0; x < size; x++){
			for(int y=0; y < size; y++){
				Cell cell = new Cell(x,y);
				if(initialPopulation.contains(x + "_" + y)){
					cell.setNewValue(true);
					cell.update();
				}
				
				populationArray[x][y] = cell;
				populationList.add(cell);
			}
		}
		
		for(Cell cell : populationList){
			cell.setNeighbours(defineNeighbours(cell.getPositionX(),cell.getPositionY()));
			liveCells++;
		}
	}
	
	
	private List<Cell> defineNeighbours(int i, int x){
		
		List<Cell> neighbours = new ArrayList<Cell>();
		for(int a = i-1; a <= i+1; a++){
			for(int b = x-1; b <= x+1; b++){
				if(a != i || b != x){
					neighbours.add(populationArray[Math.floorMod(a, size)][Math.floorMod(b,size)]);
				}
			}
		}
		return neighbours;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getNeighborsDegree() {
		return neighborsDegree;
	}

	public void setNeighborsDegree(Integer neighborsDegree) {
		this.neighborsDegree = neighborsDegree;
	}
		
	public void print(){
		StringBuilder output = new StringBuilder("Generation " + generation + " (Live: " + liveCells + " - Dead:" + deadCells + "\n");
		for(int i=0; i < size; i++){
			for(int x=0; x < size; x++){
				Cell cell = populationArray[i][x];
				cell.update();
				output.append((cell.isAlive() ? 1 : 0) + cell.getCause() +  " - ");
			}
			output.append("\n");
		}
		System.out.println(output.toString());
	}
	
	public void printLiveCellsLocation(){
		StringBuilder output = new StringBuilder("[");
		for(int i=0; i < size; i++){
			for(int x=0; x < size; x++){
				Cell cell = populationArray[i][x];
				cell.update();
				if(cell.isAlive()){
					output.append("[" + cell.getPositionX() + "," + cell.getPositionY() + "]");
				}
			}
		}
		output.append("]");
		System.out.println(output.toString());
	}
	
}
