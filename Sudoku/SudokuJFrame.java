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

        frame.getContentPane().removeAll();
		buildMenu();
		Container contentPane = frame.getContentPane();
		contentPane.add(grid = new SudokuGrid(size));
		frame.pack();					//Set to preferred size
		frame.setVisible(true);
		frame.setResizable(false);			//Non-resizable
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centerView();
		
	}
	
	/**
	* Builds the menu along the top of the screen, and buttons along the bottom
	**/
	private void buildMenu() {
      JMenuBar menuBar = new JMenuBar();
      
      //Sets the font size for the menu options
      Font textFont = new Font("Arial", Font.CENTER_BASELINE, 15);
      UIManager.put("Menu.font", textFont);
      UIManager.put("MenuItem.font", textFont);
      
      //Menu bar:
      // OPTIONS
      JMenu optionMenu = new JMenu("Options");
      
      JMenuItem size4x4  = new JMenuItem("4x4");
      JMenuItem size9x9  = new JMenuItem("9x9");
      
      optionMenu.add(size4x4);
      optionMenu.addSeparator();
      optionMenu.add(size9x9);
      
      // DIFFICULTY
      JMenu difficultyMenu = new JMenu("Difficulty");
      
      JMenuItem easy = new JMenuItem("Easy");
      JMenuItem medium = new JMenuItem("Medium");
      JMenuItem hard = new JMenuItem("Difficult");
      
      difficultyMenu.add(easy);
      difficultyMenu.addSeparator();
      difficultyMenu.add(medium);
      difficultyMenu.addSeparator();
      difficultyMenu.add(hard);
      
      //Builds the menu
      menuBar.add(optionMenu);
      menuBar.add(difficultyMenu);
      
      frame.setJMenuBar(menuBar);
      
      /**
      * Action listener that changes the grid to a 4x4
      * @param e is the event that 4x4 is selected from the menu
      **/
      size4x4.addActionListener((ActionEvent e) -> {
          frame.getContentPane().removeAll();
          frame.getContentPane().add(grid = new SudokuGrid(4));
          frame.pack();
          centerView();
      });
      
      /**
      * Action listener that changes the grid to a 9x9
      * @param e is the event that 9x9 is selected from the menu
      **/
      size9x9.addActionListener((ActionEvent e) -> {
          frame.getContentPane().removeAll();
          frame.getContentPane().add(grid = new SudokuGrid(9));
          frame.pack();
          centerView();
      });
	}
  

	/**
    * Centers the grid on the screen
    **/
	private void centerView() {
      Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension frameSize = frame.getSize();

      frame.setLocation((screen.width - frameSize.width) >> 1,
                        (screen.height - frameSize.height) >> 1);
	}

}
