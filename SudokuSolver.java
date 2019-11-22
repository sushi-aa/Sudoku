//Arushi Arora
//SudokuSolver.java

import java.util.Scanner;

public class SudokuSolver
{
        private int [][] board;
        public static final int WIDTH = 9;
        public static final int HEIGHT = 9;
        private static Scanner infile;
        
        public SudokuSolver()
        {
                board = new int[HEIGHT][WIDTH];
                //board = new int[][]{ {8, 0, 2, 0, 0, 4, 0, 1, 0}, {6, 0, 0, 1, 0, 5, 3, 0, 4}, {3, 1, 4, 6, 8, 0, 5, 0, 0},
                    //{0, 0, 1, 5, 9, 7, 0, 0, 3}, {7, 0, 8, 0, 4, 1, 2, 5, 9}, {0, 0, 3, 8, 0, 2, 7, 0, 0},
                    //{9, 3, 0, 0, 7, 0, 1, 0, 8}, {1, 0, 0, 2, 0, 3, 0, 9, 0}, {0, 4, 0, 0, 1, 8, 0, 3, 5} }; 

        } 

        public static void main (String[] args)
        {
                SudokuSolver ss = new SudokuSolver();
                if (args.length != 0)
                {
                   infile = OpenFile.openToRead(args[0]);
                   
                }
                else
                {
                    infile = OpenFile.openToRead("a1.docx");
                }
                ss.run();
                
        }

        public void run()
        {
                createBoard();
                SudokuUtilities.print(board);
        }

        public void createBoard()
        {
                
                
                while (infile.hasNext())
                {
                    for (int i = 0; i<board.length; i++)
                    {
                        for (int j = 0; j<board[i].length; j++)
                        {
                            board[i][j] = infile.nextInt();
                        }
                    }
                }      
                infile.close();
                int r = getNextRow(0, -1);
                int c = getNextCol(0, -1);
                createCell(r, c); //upper left hand corner

        }

        public boolean createCell(int row, int col)
        {
                int nextRow = row;
                int nextCol = col;
                int[] toCheck = {1, 2, 3, 4, 5, 6, 7, 8, 9};
                SudokuUtilities.shuffleArray(toCheck); //shuffle 1-9

                for (int i = 0; i<toCheck.length; i++)
                {
                        if (SudokuUtilities.legalMove(board, row, col, toCheck[i]))
                        {
                                board[row][col] = toCheck[i];
                                SudokuUtilities.printInfo(board, row, col, toCheck, i);

                                nextRow = getNextRow(row, col);
                                nextCol = getNextCol(row, col); //next col
                               
                                if (nextRow == -1 && nextCol == -1)
                                {
                                    return true;
                                }
                                if (createCell(nextRow, nextCol))
                                {
                                        return true; //made the cell before, then move on
                                }
                        }
                }
                board[row][col] = 0; //if doesn't work, set to 0 and backtrack
                SudokuUtilities.printInfo(board, row, col, toCheck, -1);

                return false;
        }
        
        public int getNextRow(int r, int c)
        {
            for (int k = c+1; k<board[r].length; k++)
            {
                if (board[r][k] == 0)
                {
                    return r;
                }
            }
            for (int i = r+1; i<board.length; i++)
            {
                for (int j = 0; j<board[i].length; j++)
                {
                    if (board[i][j] == 0)
                    {
                        return i;
                    }
                }
            }
            return -1;
        }
        
        public int getNextCol(int r, int c)
        {
            for (int k = c+1; k<board[r].length; k++)
            {
                if (board[r][k] == 0)
                {
                    return k;
                }
            }
            for (int i = r+1; i<board.length; i++)
            {
                for (int j = 0; j<board[i].length; j++)
                {
                    if (board[i][j] == 0)
                    {
                        return j;
                    }
                }
            }
            return -1;
            
        }
}
