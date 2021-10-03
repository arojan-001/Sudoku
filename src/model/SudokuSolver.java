package model;

import java.util.ArrayList;

/**
 * Solves a Sudoku board
 * 
 * @author SAGA LLC
 * 
 */
public class SudokuSolver{


	/**
	 * Solves the sudoku and returns it solved. Implements a recursive algorithm
	 * that does the following: First, it searches for the empty cell with the
	 * minimum number of filling possibilities. Then it fills it with a
	 * possibility, calls itself recursively until it it solved. If not, it
	 * tries the next possibility and so forth.
	 * 
	 * @param board
	 * @return Solved Sudoku board
	 */
	
	
	public Board solve(Board board) {
		board = board.clone();
		/* Find the empty cell with the minimum number of filling possibilities. */
		int minSize = Board.SIZE, minIndex = board.getPositions().size();
		ArrayList<Integer> possibilities;
		for (int i = 0; i < board.getPositions().size(); i++) {
			Position p = board.getPositions().get(i);
			/* If cell not empty, continue */
			if (board.getCell(p) != 0) {
				continue;
			}
			possibilities = board.getPossibilities(p);
			if (possibilities == null) {
				return null;
			}
			if (possibilities.size() < minSize) {
				minIndex = i;
				minSize = possibilities.size();
			}
		}
		/* No empty cells found, ergo a solution has been found */
		if (minSize == Board.SIZE) {
			return board;
		}
		/* Fill cell with each possibility until solution is found */
		Position p = board.getPositions().get(minIndex);
		possibilities = board.getPossibilities(p);
		if (possibilities.size() == 0) {
			return null;
		} else {
			for (Integer value : possibilities) {
				board.setCell(p, value);
				/* Recursive call */
				Board partialResult = solve(board);
				if (partialResult != null) {
					return partialResult;
				}
			}
		}
		return null;
	}

}
