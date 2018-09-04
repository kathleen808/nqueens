/**
 * 
 */
package edu.ics211.l20;

import java.util.ArrayList;

/**
 * Class that creates a new NQueens variable.
 * 
 * @author Kathleen Dy
 *
 * 1 = queen, 0 = empty
 */
public class NQueens {

  /**
   * solve() checks the base case: if numRows == numColumns. If the board is valid, it calls solveCell, otherwise it
   * returns false.
   * 
   * @param board, type int[][], the board to check
   * @return type boolean, true if the board has a solution where numQueens == numRows == numColumns and no Queens can
   * attack each other.
   */
  public boolean solve(int[][] board) {
    System.out.println(toString(board));

    // check that numRows == numColumns
    for (int i = 0; i < board.length; i++) {
      if (board[i].length != board.length) {
        return false;
      }
    }

    if (check(board)) {
      return solveCell(board, 0, 0);
    }
    return false;
  }


  /**
   * checkSolved() checks if the board is solved (if numQueens == numRows).
   * 
   * @param board, type int[][], the board to check
   * @return type boolean, true if the board is solved, false otherwise
   */
  public boolean checkSolved(int[][] board) {
    // System.out.println(toString(board));
    // number of queens != number of rows && columns
    int qCount = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == 1) {
          qCount++;
        }
      }
    }
    return qCount == board.length;
  }


  /**
   * check() adds the x and y coordinates of each queen into an ArrayList and checks that no queens are in the same row,
   * column, or diagonal.
   * 
   * @param board, type int[][], the board to check
   * @return type boolean, true if the board is valid, false otherwise
   */
  public boolean check(int[][] board) {
    // System.out.println(toString(board));

    ArrayList<Integer> xList = new ArrayList<>(); // x coordinate of a queen
    ArrayList<Integer> yList = new ArrayList<>(); // y coordinate of a queen
    // number of queens != number of rows && columns
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == 1) {
          xList.add(i);
          yList.add(j);
        }
      }
    }

    // System.out.println(toString(xList));
    // System.out.println(toString(yList));

    boolean val;
    for (int i = 0; i < xList.size(); i++) {
      val = checkMates(xList, yList, i);
      if (val == false) {
        return false;
      }
    }
    return true;
  }


  /**
   * checkMates() returns true if no queens are attacking the queen at the index parameter, false otherwise.
   * 
   * @param xList, type ArrayList<Integer>, the x coordinate of a queen
   * @param yList, type ArrayList<Integer>, the x coordinate of a queen
   * @param index, type int the index of the queen to check
   * @return type boolean, true if no queens are attacking the queen at the index parameter, false otherwise
   */
  public boolean checkMates(ArrayList<Integer> xList, ArrayList<Integer> yList, int index) {
    int x = xList.get(index);
    int y = yList.get(index);
    for (int i = 0; i < xList.size(); i++) {
      // System.out.printf("(%d, %d), (%d, %d)%n", x, y, xList.get(i), yList.get(i));
      if (i != index) {
        if (x == xList.get(i) || y == yList.get(i)) {
          return false;
        }
        if ((x + y == xList.get(i) + yList.get(i)) || (x - y == xList.get(i) - yList.get(i))) {
          return false;
        }
      }
    }
    return true;
  }


  /**
   * solveCell() uses recursion to solve the cell at the row and column parameters.
   * 
   * @param board, type int[][], the board to solve
   * @param row, type int, the index of the row to solve
   * @param column, type int, the index of the column to solve
   * @return type boolean, true if this cell has a solution, false otherwise
   */
  public boolean solveCell(int[][] board, int row, int column) {
    // IF: numQueens == numRows, return true
    //
    // ELSE IF: end of board, return false
    // ELSE: false
    // IF: cell is empty, place a queen
    // move to next cell/row
    // If: val = true, return true
    // Else: set cell to 0, try next cell
    // If: val == true, return true.
    // Else: tried all possible values, no solutions, return false
    // ELSE: move to next cell
    if (check(board) == false) {
      return false;
    }
    if (checkSolved(board)) {
      return true;
    } else if (row == board.length || column == board.length) {
      return false;
    } else {
      if (board[row][column] == 0) {
        boolean val;
        board[row][column] = 1;
        val = solveCell(board, row + 1, 0);
        if (val) {
          return true;
        } else {
          board[row][column] = 0;
          return solveCell(board, row, column + 1);
        }
      } else {
        return solveCell(board, row + 1, 0);
      }
    }
  }


  /**
   * toString() returns a String representation of the ArrayList parameter.
   * 
   * @param list, type ArrayLlist<?>, the list to represent as a String
   * @return type String, the String representation of the ArrayList parameter
   */
  public static String toString(ArrayList<?> list) {
    StringBuilder string = new StringBuilder();
    string.append("[");

    for (int i = 0; i < list.size(); i++) {
      string.append(list.get(i));
      string.append(", ");
    }

    if (string.length() > 2) {
      string.replace(string.length() - 2, string.length(), "");
    }
    string.append("]");

    // string.append(String.format(" Size: %d", list.size()));
    return string.toString();
  }


  /**
   * toString() returns a String representation of the int[][] parameter.
   * 
   * @param board, type int[][], the board to represent as a String.
   * @return type String, the String representation of the int[][] parameter.
   */
  public String toString(int[][] board) {
    StringBuilder string = new StringBuilder();
    StringBuilder s = new StringBuilder();
    s.append("\n+");
    for (int i = 0; i < board.length * 3 + (board.length - 1); i++) {
      s.append("-");
    }
    s.append("+");

    string.append(s);
    for (int i = 0; i < board.length; i++) {
      string.append("\n| ");
      for (int j = 0; j < board.length; j++) {
        // if (board[i][j] == 1) {

        string.append(board[i][j]);
        // } else {
        // string.append(" ");
        // }
        string.append(" | ");
      }
      // string.append("\n" + s);

    }
    string.append(s);

    return string.toString();
  }

}
