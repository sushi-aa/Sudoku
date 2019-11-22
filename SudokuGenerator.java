public class SudokuGenerator
{
        private int [][] board;
        public static final int WIDTH = 9;
        public static final int HEIGHT = 9;

        public SudokuGenerator()
        {
                board = new int[HEIGHT][WIDTH];
        }

        public static void main (String[] args)
        {
                SudokuGenerator sg = new SudokuGenerator();
                sg.run();
        }

        public void run()
        {
                createBoard();
                SudokuUtilities.print(board);
        }

        public void createBoard()
        {
                createCell(0, 0); //upper left hand corner

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


                                if (col == 8)
                                {
                                        if (row == 8)
                                        {
                                                return true; //reached the end of the board
                                        }
                                        else
                                        {
                                                nextCol = 0;
                                                nextRow = row+1; //next row
                                        }
                                }
                                else
                                {
                                        nextCol = col+1; //next col
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
}
