package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.Position;
import model.Sudoku;

public class Cell extends JPanel {

	/**
	 * @author SAGA LLC
	 */
	private static final long serialVersionUID = 7689997863618368453L;

	private int value;
	public static int size = 50;
	public JTextField input;
	public boolean hasTextField = false;
	private Sudoku sudoku;
	private Position position;

	public Cell(Sudoku sudoku, Position p) {
		this.sudoku = sudoku;
		this.position = p;
		this.value = sudoku.getCell(position);
		setBorder(new EmptyBorder(5, 5, 15, 0));
		if(sudoku.getEditablePositions().contains(p)) {
			hasTextField = true;
			input = new JTextField(1);
			input.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			input.setFont(new Font("Arial Bold", Font.ITALIC, 22));
			input.setBackground(null);
			input.setOpaque(false);
			if(value != 0) {
				input.setText(String.valueOf(value));
			}
			input.getDocument().addDocumentListener(new DocumentListener() {

				@Override
				public void removeUpdate(DocumentEvent e) {
					if (input.getText().length() < 2) {
						input.setFont(new Font("Arial Bold", Font.ITALIC, 22));
						input.setColumns(1);
					}
					try {
						setSudokuCell(position, Integer.valueOf(input.getText()));
					} catch (Exception e2) {
						setSudokuCell(position, 0);
					}
				}

				@Override
				public void insertUpdate(DocumentEvent e) {
					if (input.getText().length() > 1) {
						input.setFont(new Font("Arial Bold", Font.ITALIC, 16));
						input.setColumns(2);
					}
					try {
						setSudokuCell(position, Integer.valueOf(input.getText()));
					} catch (Exception e2) {
						setSudokuCell(position, 0);
					}
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
				}
			});
			add(input);
			repaint();
			revalidate();
		}
		setPreferredSize(new Dimension(size, size));
	}

	private void setSudokuCell(Position p, int value) {
		sudoku.setCell(p, value);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(2, 2, size - 1, size - 1);		

		if (!hasTextField) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial Bold", Font.PLAIN, 24));
			g.drawString(String.valueOf(value), size / 2 - 5, size / 2 + 5);
			revalidate();
		}
		

	}

	public int getValue() {
		if (value != 0) {
			return value;
		} else if (!(input.getText().equals(""))) {
			int inputValue;
			try {
				inputValue = Integer.parseInt(input.getText());
				if (inputValue > 0 && inputValue < 10) {
					return inputValue;
				} else {
					return -1;
				}
			} catch (Exception e) {
				System.out.println(input.getText());
				return -1;
			}
		}
		return 0;
	}

}
