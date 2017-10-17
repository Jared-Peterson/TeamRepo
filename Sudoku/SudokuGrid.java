//Class Project
//CS371 - Software Development
//
//Authors: McLane, Kurt
//		   Near, Kathleen
//		   Olivas, Tanya
//		   Peterson, Jare

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

final class SudokuGrid extends JPanel {
	
	private static final Font ARIAL = new Font("Arial", Font.CENTER_BASELINE, 22);
	
	private final JTextField[][] grid;
	private final JPanel gridPanel;
	private final JPanel[][] smallSquares;
	
	SudokuGrid(int size) {
		this.grid = new JTextField[size][size];
		for (int y = 0; y < size; ++y) {
			for (int x = 0; x < size; ++x) {
				JTextField field = new JTextField();
				grid[y][x] = field;
			}
		}
		
		this.gridPanel   = new JPanel();
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		Dimension fieldSize = new Dimension(60, 60);
		
		for (int y = 0; y < size; ++y) {
			for (int x = 0; x < size; ++x) {
				JTextField field = grid[y][x];
				field.setBorder(border);
				field.setFont(ARIAL);
				field.setPreferredSize(fieldSize);
			}
		}
		
		int squareSize = (int) Math.sqrt(size);
		this.gridPanel.setLayout(new GridLayout(squareSize, squareSize));
		
		this.smallSquares = new JPanel[squareSize][squareSize];
		Border minisquareBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		
		for (int y = 0; y < squareSize; ++y) {
			for (int x = 0; x < squareSize; ++x) {
				JPanel p = new JPanel();
				p.setLayout(new GridLayout(squareSize,squareSize));
				p.setBorder(minisquareBorder);
				smallSquares[y][x] = p;
				gridPanel.add(p);
			}
		}
		
		for (int y = 0; y < size; ++y) {
			for (int x = 0; x < size; ++x) {
				int sqX = x / squareSize;
				int sqY = y / squareSize;
				smallSquares[sqY][sqX].add(grid[y][x]);
			}
		}
		
		this.setLayout(new BorderLayout());
		this.add(gridPanel, BorderLayout.NORTH);
	}
}