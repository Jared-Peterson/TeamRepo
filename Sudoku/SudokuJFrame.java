//Class Project
//CS371 - Software Development
//
//Authors: McLane, Kurt
//		   Near, Kathleen
//		   Olivas, Tanya
//		   Peterson, Jared

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.lang.*;

final class SudokuGrid extends JPanel {
	
	//Input variables
	private final String[][] numbersArr;
	private static final Font ARIAL = new Font("Arial", Font.CENTER_BASELINE, 22);
	
	//Grid variables
	private final JTextField[][] grid;
	private final JPanel gridPanel;
	private final JPanel[][] sectionSquares;
	
	//Button variables
	private final JPanel buttonPanel;
	private final JButton solveButton;
	private final JButton newPuzzleButton;
	
	SudokuGrid(int size) {
		
		this.grid = new JTextField[size][size];
		this.gridPanel = new JPanel();
		this.buttonPanel = new JPanel();
		
		this.numbersArr = new String[size][size];
		
		//Creates borders around each square and a thick border around each section
		Border gridBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
		Border sectionBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		
		//Sets the individual box size to 60x60 pixels & the sections to sqrtxsqrt boxes
		Dimension fieldSize = new Dimension(60, 60);
		int sectionSize = (int) Math.sqrt(size);
		
		/**
		* Loop initiates the array of text fields that makes up the grid.
		* Each field is bordered and the text is centered.
		**/
		for (int row = 0; row < size; ++row) {
			for (int col = 0; col < size; ++col) {
				
				JTextField field = new JTextField();
				grid[row][col] = field;
				
				numbersArr[row][col] =  field.getText();
				System.out.println(numbersArr[row][col]);
				
				field.setBorder(gridBorder);
				field.setFont(ARIAL);
				field.setHorizontalAlignment(JTextField.CENTER);
				field.setPreferredSize(fieldSize);
				
				field.addKeyListener(new java.awt.event.KeyAdapter() {
		            public void keyPressed(java.awt.event.KeyEvent evt) {
		            	
		            	field.setText("");
		            	char c = evt.getKeyChar();
		            	
		            	if(!Character.isDigit(c) && (c != evt.VK_BACK_SPACE))
		            		field.setText("");
		            }
		            
		        });
			}
		}
		
		System.out.println("END");
		
		
		//
		this.sectionSquares = new JPanel[sectionSize][sectionSize];
		this.gridPanel.setLayout(new GridLayout(sectionSize, sectionSize));
		
		for (int row = 0; row < sectionSize; ++row) {
			for (int col = 0; col < sectionSize; ++col) {
				
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(sectionSize, sectionSize));
				
				panel.setBorder(sectionBorder);
				sectionSquares[row][col] = panel;
				gridPanel.add(panel);
			}
		}
		
		for (int y = 0; y < size; ++y) {
			for (int x = 0; x < size; ++x) {
				int sqX = x / sectionSize;
				int sqY = y / sectionSize;
				sectionSquares[sqY][sqX].add(grid[y][x]);
			}
		}
		
		this.solveButton = new JButton("Solve");
		this.newPuzzleButton = new JButton("Next Puzzle");
		
		this.buttonPanel.add(solveButton, BorderLayout.CENTER);
		
		this.setLayout(new BorderLayout());
		this.add(gridPanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	/*private void limitInput(java.awt.event.KeyEvent evt, JTextField textField) {  
		char c = evt.getKeyChar();
		String s = Character.toString(c);
		textField.setText("");
	}*/
	
}
