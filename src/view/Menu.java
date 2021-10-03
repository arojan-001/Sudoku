package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.IOManager;
import model.Sudoku;

public class Menu extends JMenuBar {

	private static final long serialVersionUID = 7901387255695465700L;
	private JMenu sudokuMenu;
	private JMenuItem newSudoku, exitSudoku, saveSudoku, loadSudoku;
	private Window window;
	
	private void createSudoku(int difficulty) {
		window.reCreateSudoku(difficulty);
	}
	
	private void save() {
		IOManager io = new IOManager();
		io.saveToFile(window.getSudoku());
	}
	
	private void load() {
		IOManager io = new IOManager();
		Sudoku sudoku = io.readFromFile();
		window.setSudoku(sudoku);
		window.reset();		
	}
	private void setLabel(String string) {
		window.setLabel(string);
		
	}
	public Menu(Window window) {
		this.window = window;
		sudokuMenu = new JMenu("Sudoku");
		newSudoku = new JMenuItem("New Game...");
		newSudoku.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] selectionValues = { "Easy", "Medium", "Difficult",
						"More difficult", "Master" };
				String initialSelection = "Easy";
				String selected = "";
				Object selection = JOptionPane.showInputDialog(null,
						"Choose the difficulty:", "New Game",
						JOptionPane.QUESTION_MESSAGE, null, selectionValues,
						initialSelection);
				if(selection != null) {
					selected = selection.toString();
				}
				else {
					return;
				}

				switch (selected) {
					case "Easy" -> createSudoku(1);
					case "Medium" -> createSudoku(2);
					case "Difficult" -> createSudoku(3);
					case "More difficult" -> createSudoku(4);
					case "Master" -> createSudoku(5);
				}
				setLabel("Good luck!");
			}


		});
		
		saveSudoku = new JMenuItem("Keep");
		saveSudoku.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
				
			}
		});
		loadSudoku = new JMenuItem("Carry");
		loadSudoku.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				load();
				
			}
		});
		
		exitSudoku = new JMenuItem("Exit");
		exitSudoku.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);;

			}
		});

		sudokuMenu.add(newSudoku);
		sudokuMenu.add(saveSudoku);
		sudokuMenu.add(loadSudoku);
		sudokuMenu.add(exitSudoku);
		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutSudoku = new JMenuItem("About SudokuMaster");
		aboutSudoku.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "SAGA LLC");
				
			}
		});
		helpMenu.add(aboutSudoku);
		add(sudokuMenu);
		add(helpMenu);
	}
}
