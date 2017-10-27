//Class Project
//CS371 - Software Development
//
//Authors: McLane, Kurt
//		   Near, Kathleen
//		   Olivas, Tanya
//		   Peterson, Jared

import java.util.*;

public class CrossChecker{
	/**
     * Translates a finished Sudoku grid's contents to a 2-D integer array to be cross-checked
     * @param gridContents is the contents of a 2-D grid of text fields
     * @param puzzleSize is the size of the grid (length and width in text field boxes)
     * Returns true if the solution is correct, otherwise returns false
     * 
     * Author: Jared Peterson
     **/
	public static boolean checkSolution(String gridContents[][], int puzzleSize) {
		
		int[][] intGridContents = new int [puzzleSize][puzzleSize];
		
		//Converts the text fields to integers
		for (int row = 0; row < puzzleSize; ++row) {
			for (int col = 0; col < puzzleSize; ++col) {
				try {
					intGridContents[row][col] = Integer.parseInt(gridContents[row][col]);
					
				//Returns false if the solution contains any non-integer input or empty field(s)
				}catch(Exception e) {
					return false;
				}
			}
		}
		
		//Cross-check each entry of the solution and return overall validity
		if(solutionIsCorrect(intGridContents, puzzleSize)) {
			return true;
		}
		return false;
	}
	
	/**
     * Cross-checks the assortment of the numbers in a completed Sudoku grid
     * @param gridContents is the contents of the grid in integer form
     * @param puzzleSize is the size of the grid (in text field boxes)
     * Returns true if the solution is correct, and false if incorrect
     * 
     * Author: Jared Peterson
     **/
	public static boolean solutionIsCorrect(int gridContents[][], int puzzleSize) {
		//boolean variable to be returned
	    boolean solved = false;
	    
	    //Traverses the entire 2-D solution grid and cross-checks each number's placement in respect to the rest of its row, column, and section
		for (int row = 0; row < puzzleSize; ++row) {
			for (int col = 0; col < puzzleSize; ++col) {
				
				if( isValidInRow( gridContents, row, puzzleSize )
					&& checkCol( gridContents, col, puzzleSize )
					&& checkBox( gridContents, row, col, puzzleSize )) {
					solved = true;
				}else {
					solved = false;
					break;
				}
			}
			if(solved == false) {
				break;
			}
		}
		return solved;
	}

	/**
     * Verifies that all numbers are present in the given row (no duplicates or empty fields)
     * @param gridContents is the contents of the grid in integer form
     * @param row is the row being checked
     * @param puzzleSize is the size of the grid (in text field boxes)
     * returns true or false
     **/
	protected static boolean isValidInRow(int gridContents[][], int row, int puzzleSize) {
		Set<Integer> numSet = new HashSet<>();
		for(int i = 1; i <= puzzleSize; i++) {
			numSet.add(i);
		}
		int temp;
		for( int col = 0; col < puzzleSize; col++ ) {
			 if(numSet.isEmpty()) {
				 return true;
			 }
	         temp = gridContents[row][col];
	         if(numSet.contains(temp)) {
	        	 numSet.remove(temp);
	         }else {
	        	 return false;
	         }
		}
		return true;
	}
	
	protected static boolean checkCol(int inputArray[][], int col, int puzzleSize) {
		Set<Integer> numSet = new HashSet<>();
		for(int i = 1; i <= puzzleSize; i++) {
			numSet.add(i);
		}
		int temp;
		for( int row = 0; row < puzzleSize; row++ ) {
			if(numSet.isEmpty()) {
				return true;
			}
	        temp = inputArray[row][col];
	        if(numSet.contains(temp)) {
	        	numSet.remove(temp);
	        }else {
	        	return false;
	        }
	            
		}

	    return true;
		
	}
	
	/**
     * Cross-checks  in its section (no duplicates or empty fields)
     * @param inputArray is the contents of the grid in integer form
     * @param row is the row being checked
     * @param size is the size of the grid (in text field boxes)
     * returns true or false
     **/
	protected static boolean checkBox(int inputArray[][], int row, int col, int size) {
	      row = (int)(row / Math.sqrt(size)) * (int)Math.sqrt(size);
	      col = (int)(col / Math.sqrt(size)) * (int)Math.sqrt(size);
	      Set<Integer> numSet = new HashSet<>();
		  for(int i = 1; i <= size; i++) {
			 numSet.add(i);
		  }
	      int temp;
	      for( int r = 0; r < Math.sqrt(size); r++ ) {
	         for( int c = 0; c < Math.sqrt(size); c++ ) {
	        	 if(numSet.isEmpty()) {
	        		 return true;
	        	 }
	        	 temp = inputArray[row+r][col+c];
	             if(numSet.contains(temp)) {
	            	 numSet.remove(temp);
	             }
	             else {
	            	 return false;
	             }
	         }
	      }
	      return true ;
	}
}