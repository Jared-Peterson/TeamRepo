//Class Project
//CS371 - Software Development
//
//Authors: McLane, Kurt
//		   Near, Kathleen
//		   Olivas, Tanya
//		   Peterson, Jared

import java.util.*;

public class SolutionChecker{
	
	/**
     * Translates a Sudoku grid's content to a 2-D integer array, and calls
     * the solutionIsCorrect method to check the 2-D integer array.
     * @param gridContents is the contents of a 2-D grid of text fields
     * @param puzzleSize is the size of the grid (length and width in text field boxes)
     * Returns true if the solution is correct, otherwise returns false
     * 
     * Author: Jared Peterson
     **/
	public static boolean checkSolution(String gridContents[][], int puzzleSize) {
		
		//creates 2-D integer array
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
     * Checks the assortment of the numbers in a full Sudoku grid
     * @param intGridContents is the contents of the Sudoku grid in integer form
     * @param puzzleSize is the size of the grid (in text field boxes)
     * Returns true if the solution is correct, and false if incorrect
     * 
     * Author: Jared Peterson
     **/
	public static boolean solutionIsCorrect(int intGridContents[][], int puzzleSize) {
		
		//boolean variable to be returned
	    boolean solved = false;
	    
	    //Traverses the entire 2-D array and checks each number's placement 
	    //in respect to the rest of its row, column, and section.
		for (int row = 0; row < puzzleSize; ++row) {
			for (int col = 0; col < puzzleSize; ++col) {
				
				if( checkRow( intGridContents, row, puzzleSize )
					&& checkCol( intGridContents, col, puzzleSize )
					&& checkBox( intGridContents, row, col, puzzleSize )) {
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
     * @param intGridContents is the contents of the grid in integer form
     * @param row is the row being checked
     * @param puzzleSize is the size of the grid (in text field boxes)
     * returns true if all of the numbers 1-puzzleSize are present in the given row, otherwise returns false
     * 
     * Author: Jared Peterson
     **/
	protected static boolean checkRow(int intGridContents[][], int row, int puzzleSize) {
		
		//creates a set of integers 1-puzzleSize
		Set<Integer> numSet = new HashSet<>();
		for(int i = 1; i <= puzzleSize; i++) {
			numSet.add(i);
		}
		int temp;
		//checks given column
		for( int col = 0; col < puzzleSize; col++ ) {
			 if(numSet.isEmpty()) {
				 return true;
			 }
	         temp = intGridContents[row][col];
	         if(numSet.contains(temp)) {
	        	 numSet.remove(temp);
	         }else {
	        	 return false;
	         }
		}
		return true;
	}
	
	/**
     * Verifies that all numbers are present in the given column (no duplicates or empty fields)
     * @param intGridContents is the contents of the grid in integer form
     * @param col is the column being checked
     * @param puzzleSize is the size of the grid (in text field boxes)
     * returns true if all of the numbers 1-puzzleSize are present in the given column, otherwise returns false
     * 
     * Author: Jared Peterson
     **/
	protected static boolean checkCol(int intGridContents[][], int col, int puzzleSize) {
		
		//creates a set of numbers 1-puzzleSize
		Set<Integer> numSet = new HashSet<>();
		for(int i = 1; i <= puzzleSize; i++) {
			numSet.add(i);
		}
		int temp;
		//checks given row
		for( int row = 0; row < puzzleSize; row++ ) {
			if(numSet.isEmpty()) {
				return true;
			}
	        temp = intGridContents[row][col];
	        if(numSet.contains(temp)) {
	        	numSet.remove(temp);
	        }else {
	        	return false;
	        }
		}
	    return true;
	}
	
	/**
     * Verifies that all numbers are present in the box (no duplicates or empty fields)
     * @param intGridContents is the contents of the grid in integer form
     * @param row is the row of the box that is being checked
     * @param col is the column of the box that is being checked
     * @param puzzleSize is the size of the grid (in text field boxes)
     * returns true if all of the numbers 1-puzzleSize are present in the given box, otherwise returns false
     * 
     * Author: Jared Peterson
     **/
	protected static boolean checkBox(int intGridContents[][], int row, int col, int puzzleSize) {
		
		//uses integer division to place row and col to the beginning of the box that it will check
	    row = (int)(row / Math.sqrt(puzzleSize)) * (int)Math.sqrt(puzzleSize);
	    col = (int)(col / Math.sqrt(puzzleSize)) * (int)Math.sqrt(puzzleSize);
	    //creates a set of numbers 1-puzzleSize
	    Set<Integer> numSet = new HashSet<>();
		for(int i = 1; i <= puzzleSize; i++) {
		   numSet.add(i);
		}
	    int temp;
	    //checks given box
	    for( int r = 0; r < Math.sqrt(puzzleSize); r++ ) {
	       for( int c = 0; c < Math.sqrt(puzzleSize); c++ ) {
	          if(numSet.isEmpty()) {
	             return true;
	          }
	          temp = intGridContents[row+r][col+c];
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