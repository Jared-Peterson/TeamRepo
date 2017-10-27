//Class Project
//CS371 - Software Development
//
//Authors: McLane, Kurt
//		   Near, Kathleen
//		   Olivas, Tanya
//		   Peterson, Jared

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.lang.*;

final class SudokuGrid extends JPanel {
	
	//Grid variables
	private static final Font ARIAL = new Font("Arial", Font.CENTER_BASELINE, 22);
	private static final Font ARIAL_SM = new Font("Arial", Font.CENTER_BASELINE, 15);
	private final JTextField[][] grid;
	private final JPanel gridPanel;
	private final JPanel[][] sectionSquares;
	
	//Button variables
	private final JPanel buttonPanel;
	private final JButton solveButton;
	private final JButton newPuzzleButton;
	
	//Input storage
	private final String[][] numbersArr;
	
	SudokuGrid(int size) {
		
		//Variable instantiations
		this.grid = new JTextField[size][size];
		this.gridPanel = new JPanel();
		this.buttonPanel = new JPanel();
		this.numbersArr = new String[size][size];
		
		//Creates a thin border to be used for each square and a thick border to be used around each section
		Border gridBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
		Border sectionBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		
		//Sets the individual box size to 60x60 pixels & the section size to a dynamic number of boxes based upon the dimensions
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
				
				field.setBorder(gridBorder);
				field.setFont(ARIAL);
				field.setHorizontalAlignment(JTextField.CENTER);
				field.setPreferredSize(fieldSize);
				
				//Assigns a listener to each text field that ignores characters that aren’t digits or backspaces
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
		
		//Creates a "Solve" button of static dimensions and font size
		this.solveButton = new JButton("Solve");
		solveButton.setPreferredSize(new Dimension(125, 40));
		solveButton.setFont(ARIAL_SM);
		
		//Reads in all numbers from the grid
		solveButton.addActionListener((ActionEvent e) -> {
			for (int row = 0; row < size; ++row) {
				for (int col = 0; col < size; ++col) {
					numbersArr[row][col] =  grid[row][col].getText();
				}
			}
			if(CrossChecker.checkSolution(numbersArr, size)) {
				//do when solve is correct
				System.out.println("The solution is correct!");
			}
			else {
				//do when solve is incorrect
				System.out.println("The solution is wrong.");
			}
	      });
		
		//Creates a "Next Puzzle" button of static dimensions and font size
		this.newPuzzleButton = new JButton("Next Puzzle");
		newPuzzleButton.setPreferredSize(new Dimension(125, 40));
		newPuzzleButton.setFont(ARIAL_SM);
		
		newPuzzleButton.addActionListener((ActionEvent e) -> {
			JOptionPane.showMessageDialog(null, "Are you sure you wish to start a new puzzle?\nChanges will not be saved.");
		});
		
		//Adds the buttons to the bottom of the screen, centered and separated by 40px
		this.buttonPanel.add(solveButton, BorderLayout.CENTER);
		buttonPanel.add(Box.createRigidArea(new Dimension(15,0)));
		this.buttonPanel.add(newPuzzleButton, BorderLayout.CENTER);
		
		//Adds all items to the layout
		this.setLayout(new BorderLayout());
		this.add(gridPanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
}