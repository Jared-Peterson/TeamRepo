package blah;
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
		buildMenu();
		Container contentPane = frame.getContentPane();
		contentPane.add(grid = new SudokuGrid(size));
		frame.pack();					//Set to preferred size
		frame.setVisible(true);
		frame.setResizable(true);			//Non-resizable
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centerView();
		
	}
	
	/**
	* Constructor
	* @param size is the desired dimension of the puzzle
	* Adds a Sudoku grid to the JFrame
	**/
	private void buildMenu() {
      JMenuBar bar = new JMenuBar();
      JMenu optMenu = new JMenu("Options");
      JMenu difMenu = new JMenu("Difficulty");

      JMenuItem size4  = new JMenuItem("4x4");
      JMenuItem size9  = new JMenuItem("9x9");
      JMenuItem easy = new JMenuItem("Easy");
      JMenuItem medium = new JMenuItem("Medium");
      JMenuItem hard = new JMenuItem("Hard");

      optMenu.add(size4);
      optMenu.addSeparator();
      optMenu.add(size9);
      
      difMenu.add(easy);
      difMenu.addSeparator();
      difMenu.add(medium);
      difMenu.addSeparator();
      difMenu.add(hard);
      

      bar.add(optMenu);
      bar.add(difMenu);

      size4.addActionListener((ActionEvent e) -> {
      	//SudokuJFrame grid4x4 = new SudokuJFrame(4);
          frame.getContentPane().removeAll();
          frame.getContentPane().add(grid = new SudokuGrid(4));
          frame.pack();
          centerView();
      });

      size9.addActionListener((ActionEvent e) -> {
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
