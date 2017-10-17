//Class Project
//CS371 - Software Development
//
//Authors: McLane, Kurt
//		   Near, Kathleen
//		   Olivas, Tanya
//		   Peterson, Jare

import java.awt.Container;
import javax.swing.*;

public class SudokuJFrame {
	
	private final JFrame frame = new JFrame("Sudoku Solver");
	private SudokuGrid grid;
	
	/**
	* Constructor
	* @param size is the desired dimension of the puzzle
	* Adds a Sudoku grid to the JFrame
	**/
	public SudokuJFrame(int size) {
		Container contentPane = frame.getContentPane();
		contentPane.add(grid = new SudokuGrid(size));
		
		frame.pack();					//Set to preferred size
		frame.setVisible(true);
		frame.setResizable(false);			//Non-resizable
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
