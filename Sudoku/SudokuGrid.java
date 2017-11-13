//Class Project
//CS371 - Software Development
//
//Authors: McLane, Kurt
//		   Near, Kathleen
//		   Olivas, Tanya
//		   Peterson, Jared

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.PlainDocument;


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
	
	//Allow num pad entries
	
	//Puzzle options
	private final String[][] fourByFourPrefill = new String[][] {
		{ "",  "2", "1", ""  },
		{ "3", "",  "",  ""  },
		{ "",  "",  "",  "1" },
		{ "",  "3", "4", ""  }
	};
	
	
	private final String[][] fourByFourPrefill2 = new String[][] {
		{ "",  "1", "", "3"  },
		{ "", "",  "2",  ""  },
		{ "",  "4",  "",  "" },
		{ "1",  "", "3", ""  }
	};
	
	private final String[][] fourByFourPrefill3 = new String[][] {
		{ "2", "", "1", ""  },
		{ "", "",  "",  "4"  },
		{ "3",  "",  "",  "" },
		{ "",  "2", "", "1"  }
	};
	
	private final String[][] fourByFourPrefill4 = new String[][] {
		{ "",  "4", "2", ""  },
		{ "", "",  "",  "1"  },
		{ "4",  "",  "",  "" },
		{ "",  "3", "1", ""  }
	};
	
	private final String[][] fourByFourPrefill5 = new String[][] {
		{ "",  "3", "", ""  },
		{ "2", "",  "",  "3"  },
		{ "1",  "",  "",  "4" },
		{ "",  "", "1", ""  }
	};
	
	private final String[][] fourByFourPrefill6 = new String[][] {
		{ "",  "4", "3", ""  },
		{ "", "",  "",  "4"  },
		{ "2",  "",  "",  "" },
		{ "",  "1", "2", ""  }
	};
	
	private final String[][] fourByFourPrefill7 = new String[][] {
		{ "",  "1", "", ""  },
		{ "4", "",  "",  "1"  },
		{ "3",  "",  "",  "2" },
		{ "",  "", "3", ""  }
	};
	
	private final String[][] fourByFourPrefill8 = new String[][] {
		{ "",  "3", "", "4"  },
		{ "", "",  "3",  ""  },
		{ "",  "2",  "",  "" },
		{ "1", "", "2", ""  }
	};
	
	private final String[][] fourByFourPrefill9 = new String[][] {
		{ "2",  "", "", "3"  },
		{ "", "",  "2",  ""  },
		{ "",  "1",  "",  "" },
		{ "4",  "", "", "1"  }
	};
	
	private final String[][] fourByFourPrefill10 = new String[][] {
		{ "",  "", "2", ""  },
		{ "2", "",  "",  "1"  },
		{ "4",  "",  "",  "3" },
		{ "",  "3", "", ""  }
	};
	
	private final String[][] nineByNinePrefill = new String[][] {
		{ "7", "",  "",  "",  "9", "8", "5", "6", ""  },
		{ "",  "",  "5", "",  "",  "",  "",  "",  ""  },
		{ "",  "6", "2", "",  "1", "4", "3", "",  ""  },
		{ "",  "",  "1", "3", "8", "7", "6", "",  "9" },
		{ "",  "",  "9", "",  "",  "",  "7", "",  ""  },
		{ "4", "",  "8", "9", "6", "5", "1", "",  ""  },
		{ "",  "",  "6", "4", "7", "",  "2", "3", ""  },
		{ "",  "",  "",  "",  "",  "",  "8", "",  ""  },
		{ "",  "5", "7", "8", "2", "",  "",  "6", ""  }
	};
	
	private final String[][] nineByNinePrefill2 = new String[][] {
		{ "6", "",  "7",  "8",  "4", "", "", "", ""  },
		{ "",  "3",  "", "",  "5",  "",  "",  "7",  ""  },
		{ "",  "4", "5", "",  "", "2", "6", "3",  "8"  },
		{ "",  "",  "9", "5", "3", "", "", "",  "6" },
		{ "7",  "",  "", "",  "",  "",  "", "",  "5"  },
		{ "5", "",  "", "", "8", "1", "7", "",  ""  },
		{ "3",  "5",  "6", "9", "", "",  "1", "4", ""  },
		{ "",  "8",  "",  "",  "1",  "",  "", "6",  ""  },
		{ "",  "", "", "", "6", "4",  "5",  "", "3"  }
	};
	
	private final String[][] nineByNinePrefill3 = new String[][] {
		{ "", "",  "",  "2",  "7", "8", "", "", "4"  },
		{ "7",  "",  "", "",  "",  "",  "",  "3",  ""  },
		{ "",  "4", "", "3",  "", "5", "", "9",  "7"  },
		{ "3",  "",  "7", "5", "", "", "4", "",  "1" },
		{ "2",  "",  "", "1",  "",  "9",  "", "",  "6"  },
		{ "1", "",  "4", "", "", "7", "9", "",  "2"  },
		{ "6",  "5",  "", "7", "", "4",  "", "2", ""  },
		{ "",  "7",  "",  "",  "",  "",  "", "",  "5"  },
		{ "9",  "", "", "6", "5", "1",  "",  "", ""  }
	};
	
	private final String[][] nineByNinePrefill4 = new String[][] {
		{ "", "",  "",  "",  "5", "2", "9", "", ""  },
		{ "2",  "",  "8", "",  "7",  "3",  "5",  "",  ""  },
		{ "",  "", "6", "9",  "4", "", "", "",  "1"  },
		{ "5",  "",  "", "", "", "", "", "7",  "" },
		{ "",  "7",  "3", "5",  "6",  "1",  "8", "4",  ""  },
		{ "", "1",  "", "", "", "", "", "",  "5"  },
		{ "8",  "",  "", "", "1", "6",  "2", "", ""  },
		{ "",  "",  "9",  "8",  "2",  "",  "4", "",  "7"  },
		{ "",  "", "5", "7", "3", "",  "",  "", ""  }
	};
	
	
	private final String[][] nineByNinePrefill5 = new String[][] {
		{ "", "",  "2",  "1",  "", "", "", "8", ""  },
		{ "",  "4",  "3", "",  "7",  "",  "1",  "",  "6"  },
		{ "",  "", "", "",  "3", "2", "", "",  ""  },
		{ "",  "9",  "", "", "8", "", "5", "",  "4" },
		{ "5",  "8",  "4", "",  "9",  "",  "2", "3",  "1"  },
		{ "2", "",  "7", "", "1", "", "", "6",  ""  },
		{ "",  "",  "", "8", "2", "",  "", "", ""  },
		{ "1",  "",  "6",  "",  "5",  "",  "7", "9",  ""  },
		{ "",  "7", "", "", "", "1",  "4",  "", ""  }
	};
	SudokuGrid(int size) {
		
		//Variable instantiations
		this.grid = new JTextField[size][size];
		this.gridPanel = new JPanel();
		this.buttonPanel = new JPanel();
		
		//Creates a thin border to be used for each square and a thick border to be used around each section
		Border gridBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
		Border sectionBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		
		//Sets the individual box size to 60x60 pixels & the section size to a dynamic number of boxes based upon the dimensions
		Dimension fieldSize = new Dimension(60, 60);
		int sectionSize = (int) Math.sqrt(size);
		
		//random number generator to select puzzle
		Random ran = new Random();
		int random = ran.nextInt(10);
		
		//Sets the pre-made puzzle based upon grid size
		String[][] numbersArr;
		
		switch (random) {
			
		case 0: 
		
			if(size == 9)
				numbersArr = this.nineByNinePrefill;
			else
				numbersArr = this.fourByFourPrefill;
			break;
		
		
		case 1:
		
			if(size == 9)
				numbersArr = this.nineByNinePrefill2;
			else
				numbersArr = this.fourByFourPrefill2;
			break;
			
		case 2:
			
			if(size == 9)
				numbersArr = this.nineByNinePrefill3;
			else
				numbersArr = this.fourByFourPrefill3;
			break;
			
		case 3:
			
			if(size == 9)
				numbersArr = this.nineByNinePrefill4;
			else
				numbersArr = this.fourByFourPrefill4;
			break;
			
		case 4:
			
			if(size == 9)
				numbersArr = this.nineByNinePrefill5;
			else
				numbersArr = this.fourByFourPrefill5;
			break;
			
		case 5:
			
			if(size == 9)
				numbersArr = this.nineByNinePrefill;
			else
				numbersArr = this.fourByFourPrefill6;
			break;
			
		case 6:
			
			if(size == 9)
				numbersArr = this.nineByNinePrefill;
			else
				numbersArr = this.fourByFourPrefill7;
			break;
			
		case 7:
			
			if(size == 9)
				numbersArr = this.nineByNinePrefill;
			else
				numbersArr = this.fourByFourPrefill8;
			break;
			
		case 8:
			
			if(size == 9)
				numbersArr = this.nineByNinePrefill;
			else
				numbersArr = this.fourByFourPrefill9;
			break;
			
		case 9:
			
			if(size == 9)
				numbersArr = this.nineByNinePrefill;
			else
				numbersArr = this.fourByFourPrefill10;
			break;
		
	    default:  
			if(size == 9)
				numbersArr = this.nineByNinePrefill;
			else
				numbersArr = this.fourByFourPrefill;
			break;
		} //end switch
		
		/**
		* Loop initiates the array of text fields that makes up the grid.
		* Each field is bordered and the text is centered.
		**/
		for (int row = 0; row < size; ++row) {
			for (int col = 0; col < size; ++col) {
				
				//Creates a text field for each "box" with filtered input
				JTextField field = new JTextField();
				PlainDocument document = (PlainDocument) field.getDocument();
			    document.setDocumentFilter(new FieldFilter(size));
				
			    //If there is a pre-set value for the given "box", display value and disallow editing
				if(!numbersArr[row][col].isEmpty()) {
					field.setEditable(false);
					field.setText(numbersArr[row][col]);
				}
				grid[row][col] = field;
				
				//Formatting
				field.setBorder(gridBorder);
				field.setFont(ARIAL);
				field.setHorizontalAlignment(JTextField.CENTER);
				field.setPreferredSize(fieldSize);
				
				//Replaces content upon keypressed
				field.addKeyListener(new java.awt.event.KeyAdapter() {
					public void keyPressed(java.awt.event.KeyEvent evt) {
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
		
		//Creates a "Solve" button of static dimensions and defined font size
		this.solveButton = new JButton("Solve");
		solveButton.setPreferredSize(new Dimension(125, 40));
		solveButton.setFont(ARIAL_SM);
		
		//Reads in grid contents
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
				System.out.println("The solution is incorrect.");
			}
	      });
		
		//Creates a "Next Puzzle" button of static dimensions and defined font size
		this.newPuzzleButton = new JButton("Next Puzzle");
		newPuzzleButton.setPreferredSize(new Dimension(125, 40));
		newPuzzleButton.setFont(ARIAL_SM);
		
		//Verifies action with user
		newPuzzleButton.addActionListener((ActionEvent e) -> {
			JOptionPane.showMessageDialog(null, "Are you sure you wish to start a new puzzle?\nChanges will not be saved.");
		});
		
		/*//Generate new puzzle after clicking "Next Puzzle" button       ----->
		newPuzzleButton.addActionListener((ActionEvent e) -> {
	      SudokuJFrame.getFrame().getContentPane().removeAll();
          frame.getContentPane().add(grid = new SudokuGrid(4));
          SudokuJFrame.getFrame().pack();
          centerView();
     
		});*/
		
		
		//Adds all items to the layout
		this.setLayout(new BorderLayout());
		this.add(gridPanel, BorderLayout.NORTH);
		
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.buttonPanel.add(solveButton, BorderLayout.CENTER);
		buttonPanel.add(Box.createRigidArea(new Dimension(15,0)));
		this.buttonPanel.add(newPuzzleButton, BorderLayout.CENTER);
	}
}