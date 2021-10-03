package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Sudoku implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7274050187283218257L;
	private Board fullBoard;
	private Board diggedBoard;
	private ArrayList<Position> editablePositions;
	
	public Sudoku(Board fullBoard, Board diggedBoard) {
		super();
		this.fullBoard = fullBoard;
		this.diggedBoard = diggedBoard;
		initEditablePositions();
	}
	
	private void initEditablePositions() {
		editablePositions = new ArrayList<>();
		for (Iterator<Position> iterator = fullBoard.getPositions().iterator(); iterator.hasNext();) {
			Position p = (Position) iterator.next();
			if(diggedBoard.getCell(p) == 0){
				editablePositions.add(p);
			}
		}
	}
	public ArrayList<Position> getEditablePositions() {
		return editablePositions;
	}
	public void setCell(Position p, int value) {
		diggedBoard.setCell(p, value);
	}
	public Board getFullBoard() {
		return fullBoard;
	}
	public void setFullBoard(Board fullBoard) {
		this.fullBoard = fullBoard;
	}
	public Board getDiggedBoard() {
		return diggedBoard;
	}
	public void setDiggedBoard(Board diggedBoard) {
		this.diggedBoard = diggedBoard;
	}

	public int getCell(Position p) {
		return diggedBoard.getCell(p);
	}
	/**
	 * 
	 * @return number of errors
	 */
	public int getErrors() {
		int errors = 0;
		int diggedCell;
		int fullCell;
		for (int i = 0; i < Board.SIZE; i++) {
			for (int j = 0; j < Board.SIZE; j++) {
				diggedCell = diggedBoard.getCell(new Position(i, j));
				fullCell = fullBoard.getCell(new Position(i, j));
				if(diggedCell != 0 && diggedCell != fullCell) {
					errors++;
				}
			}
		}
		return errors;
	}
	public int getBlanks() {
		int blanks = 0;
		int cell;
		for (int i = 0; i < Board.SIZE; i++) {
			for (int j = 0; j < Board.SIZE; j++) {
				cell = diggedBoard.getCell(new Position(i, j));
				if(cell == 0) {
					blanks++;
				}
			}
		}
		return blanks;
	}
	
}
