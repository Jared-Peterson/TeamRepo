//Class Project
//CS371 - Software Development
//
//Authors: McLane, Kurt
//		   Near, Kathleen
//		   Olivas, Tanya
//		   Peterson, Jared

import javax.swing.SwingUtilities;

public class Sudoku {

	private final int[][] matrix;
	int rowNum, colNum;
	private static int fourByFour = 4;
	private static int nineByNine = 9;

	/**
	* Constructor
	* @param size is the desired dimension of the puzzle
	* Method creates a size x size matrix
	**/
	public Sudoku(int size) {
		this.matrix = new int[size][size];
	}
	
	/**
	* Copy Constructor
	* @param sudoku is a Sudoku object
	* A clone is created of the object's matrix
	**/
	public Sudoku(Sudoku sudoku) {
		int length = sudoku.matrix.length;
		this.matrix = new int[length][];
		
		for (int i = 0; i < length; ++i) {
			this.matrix[i] = sudoku.matrix[i].clone();
		}
	}
	
	/**
	* MAIN
	* Updates the GUI via Event Dispatch Thread (EDT)
	**/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SudokuJFrame(nineByNine);
			}
		});
	}

	/**
	* Row number getter method
	* Returns the current row number
	**/
	public int getRowNum() {
		return this.rowNum;
	}

	/**
	* Column number getter method
	* Returns the current column number
	**/
	public int getColNum() {
		return this.colNum;
	}
	
	/**
	* Matrix element getter method
	* @param x is the x-coordinate (row) of the element
	* @param y is the y-coordinate (column) of the element
	* Returns the element value at the given coordinates
	**/
	public int get(int x, int y) {
		return matrix[x][y];
	}
	
	/**
	* Matrix element setter method
	* @param x is the x-coordinate (row) of the element
	* @param y is the y-coordinate (column) of the element
	* @param value is the desired value for the element at location x, y to be set
	**/
	public void set(int x, int y, int value) {
		matrix[x][y] = value;
	}
}
