//Class Project
//CS371 - Software Development
//
//Authors: McLane, Kurt
//		   Near, Kathleen
//		   Olivas, Tanya
//		   Peterson, Jare

import java.awt.*;
import java.awt.event.ActionEvent;
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
		centerView();
		buildMenu();
	}
	
	/**
	* Constructor
	* @param size is the desired dimension of the puzzle
	* Adds a Sudoku grid to the JFrame
	**/
	private void buildMenu() {
        JMenuBar bar = new JMenuBar();
        JMenu optMenu = new JMenu("Options");
        //JMenu 

        JMenuItem size4  = new JMenuItem("4x4");
        JMenuItem grid9x9  = new JMenuItem("9x9");

        optMenu.add(size4);
        optMenu.addSeparator();
        optMenu.add(grid9x9);

        bar.add(optMenu);

        size4.addActionListener((ActionEvent e) -> {
        	//SudokuJFrame grid4x4 = new SudokuJFrame(4);
            frame.getContentPane().removeAll();
            frame.getContentPane().add(grid = new SudokuGrid(4));
            frame.pack();
            centerView();
        });

        grid9x9.addActionListener((ActionEvent e) -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(grid = new SudokuGrid(9));
            frame.pack();
            centerView();
        });
        frame.setJMenuBar(bar);
	}
    

    private void centerView() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();

        frame.setLocation((screen.width - frameSize.width) >> 1,
                          (screen.height - frameSize.height) >> 1);
    }

}
