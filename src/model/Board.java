package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 * The Sudoku board
 * 
 * @author SAGA LLC
 *
 */
public class Board implements Serializable {
	
	private static final long serialVersionUID = -2329080360064804899L;
	
	public int[][] board;
	/*
	 * Maps positions as String (this must be changed in the future) to its
	 * possible values
	 */
	private HashMap<String, ArrayList<Integer>> possibilities;
	private ArrayList<Position> positions;
	public static final int SIZE = 9;

	public Board() {
		board = new int[SIZE][SIZE];
		possibilities = new HashMap<String, ArrayList<Integer>>();
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (int i = 1; i <= board.length; i++) {
			values.add(i);
		}
		positions = new ArrayList<Position>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				positions.add(new Position(i, j));
			}
		}
		for (Position position : positions) {
			possibilities.put(position.toString(), values);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Board clone(){
		Board newBoard = new Board();
		/* */
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				newBoard.board[i][j] = board[i][j];
			}
		}
		newBoard.positions = (ArrayList<Position>) this.positions.clone();
		newBoard.possibilities = (HashMap<String, ArrayList<Integer>>) this.possibilities.clone();
		return newBoard;
	}

	public void recomputePossibilities() {
		Integer n;
		ArrayList<Integer> values;
		for (Position p : positions) {
			values = new ArrayList<Integer>();
			for (int i = 1; i <= board.length; i++) {
				values.add(i);
			}
			/* Recompute possibilities for row and column */
			for (int i = 0; i < board.length; i++) {
				if (i != p.col) {
					Position temp = new Position(p.row, i);
					n = getCell(temp);
					if (n != 0) {
						values.remove(n);
					}
				}
				if (i != p.row) {
					Position temp = new Position(i, p.col);
					n = getCell(temp);
					if (n != 0) {
						values.remove(n);
					}
				}
			}
			/* Recompute possibilities for box */
			int startRow = 3 * (p.row / 3);
			int startCol = 3 * (p.col / 3);

			for (int j = startRow; j < startRow + 3; j++) {
				for (int k = startCol; k < startCol + 3; k++) {
					if (j != p.row || k != p.col) {
						Position temp = new Position(j, k);
						n = getCell(temp);
						if (n != 0) {
							values.remove(n);
						}
					}
				}
			}
			/* Update possibilities */
			possibilities.put(p.toString(), values);
		}
	}

	public int getCell(Position p) {
		return board[p.row][p.col];
	}

	public void setCell(Position p, int value) {
		board[p.row][p.col] = value;
		recomputePossibilities();
	}

	public void clear(Position p) {
		board[p.row][p.col] = 0;
		recomputePossibilities();
	}
	
	public ArrayList<Position> getPositions() {
		return positions;
	}

	public ArrayList<Integer> getPossibilities(Position p) {
		if (board[p.row][p.col] != 0) {
			System.err.println("Cell " + p + " is not empty!");
			return null;
		}
		ArrayList<Integer> pos = possibilities.get(p.toString());
		if (pos.size() == 0) {
			return null;
		} else {
			return pos;
		}
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int[] row : board) {
			str.append(Arrays.toString(row)).append("\n");
		}
		return str.toString();
	}
	

}
