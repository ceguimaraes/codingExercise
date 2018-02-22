package com.cadu;

import java.util.ArrayList;
import java.util.Scanner;

public class TheGameOfLife {

	public static void main(String [] args){
		
		System.out.println("Welcome to The Game of Life!");
		Scanner scanner = new Scanner(System.in);
		
		Integer populationSize = null;
		
		do{
			System.out.println("Enter the population size: eg. 100 (100 x 100) , 200 (200 x 200)");
			
			String popSize = scanner.nextLine();
			
			try{
				populationSize = Integer.parseInt(popSize);
			}catch(NumberFormatException e){
				System.out.println("Number invalid. Please provide a valid Integer.");
			}
		}while(populationSize == null);
			
		ArrayList<String> initalPopulation = null;
		
		do{
			System.out.println("Enter the initial live population in the following format: x1,y1 x2,y2 -> eg: 1,1 2,2");
			String input = scanner.nextLine();
			
			try{
				initalPopulation = parse(input);
			}catch(Exception e){
				System.out.println("Initial population invalid.");
			}
			
		}while(initalPopulation == null);
		
		Integer numberOfGenerations = null;
		
		do{
			System.out.println("How many generations do you want to play?");
			
			String input = scanner.nextLine();
			
			try{
				numberOfGenerations = Integer.parseInt(input);
			}catch(NumberFormatException e){
				System.out.println("Number invalid. Please provide a valid Integer.");
			}
		}while(numberOfGenerations == null);
		
		
		Population population = new Population(populationSize, 1, initalPopulation);
		
		scanner.close();
		
		for(int i=0; i < numberOfGenerations; i++){
			
			population.nextGeneration();
			population.printLiveCellsLocation();
			
		}
		
	}
	
	private static ArrayList<String> parse(String arg){
		
		ArrayList<String> initalPopulation = new ArrayList<String>();
		String [] args = arg.split("\\s");
		for(int i=0; i < args.length; i++){
			String [] param = args[i].split(",");
			initalPopulation.add(Integer.parseInt(param[0].trim()) + "_" + Integer.parseInt(param[1].trim()));
		}
	
		return initalPopulation;
	}
	
}
