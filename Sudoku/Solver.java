//Class Project
//CS371 - Software Development
//
//Authors: McLane, Kurt
//		   Near, Kathleen
//		   Olivas, Tanya
//		   Peterson, Jared

import java.util.*;

public class Solver{
	/**
     * Translates contents to integers
     * @param inputArray is the contents of the grid converted to strings
     * @param size is the size of the grid (in text field boxes)
     * returns true or false
     **/
	public static boolean isSolved(String inputArray[][], int size) {
		
		int[][] intArray = new int [size][size];
		
		for (int row = 0; row < size; ++row) {
			for (int col = 0; col < size; ++col) {
				try {
					intArray[row][col] = Integer.parseInt(inputArray[row][col]);
				}catch(Exception e) {
					return false;
				}
			}
		}
		
		if(solve(intArray, size)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
     * Cross-checks the assortment of the numbers in the grid
     * @param inputArray is the contents of the grid in integer form
     * @param size is the size of the grid (in text field boxes)
     * returns true or false
     **/
	public static boolean solve(int inputArray[][], int size) {
	
	    boolean solved = false;
		for (int row = 0; row < size; ++row) {
			for (int col = 0; col < size; ++col) {
				
				if( checkRow( inputArray, row, size ) && checkCol( inputArray, col, size ) 
			    		  && checkBox( inputArray, row, col, size ) ){
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
     * @param inputArray is the contents of the grid in integer form
     * @param row is the row being checked
     * @param size is the size of the grid (in text field boxes)
     * returns true or false
     **/
	protected static boolean checkRow(int inputArray[][], int row, int size) {
		Set<Integer> numSet = new HashSet<>();
		for(int i = 1; i <= size; i++) {
			numSet.add(i);
		}
		int temp;
		for( int col = 0; col < size; col++ ) {
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
	
	protected static boolean checkCol(int inputArray[][], int col, int size) {
		Set<Integer> numSet = new HashSet<>();
		for(int i = 1; i <= size; i++) {
			numSet.add(i);
		}
		int temp;
		for( int row = 0; row < size; row++ ) {
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