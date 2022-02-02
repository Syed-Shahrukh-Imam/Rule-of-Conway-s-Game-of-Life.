import java.util.ArrayList;

public class ConwayGameOfLife {

    private final static int DEAD = 0; // Representation of a dead cell
    private final static int ALIVE = 1; // Representation of a alive cell

    /**
     * This function prints the grid to the console.
     * @param grid    The 2D integer gird to be outputted. It cannot be null
     */
    public void displayGridAliceCellLocation(int [][] grid){
       if (grid != null){
           System.out.print("[");
           for (int i = 0, e = grid.length ; i < e ; i++) {

               for (int j = 0, f = grid[i].length ; j < f ; j++) {
                   if(grid[i][j] == 1){
                       System.out.print("[" + i + ", " + j + "]");
                   }
               }

           }
           System.out.print("]");
           System.out.println();
       }

    }

    public int[][] initialiseGrid(int m, int n, int [][] aliveCells){

        // Initialise a m x n with the alive cells.
        int[][] grid = new int[m][n];

        for (int[] positionOfAliveCell : aliveCells) {
            grid[positionOfAliveCell[0]][positionOfAliveCell[1]] = ALIVE;
        }

        return grid;
    }

    private int getNewCellState(int curState, int liveNeighbours) {

        int newState = curState;

        switch (curState) {
            case ALIVE:

                // Any live cell with fewer than two
                // live neighbours dies
                if (liveNeighbours < 2) {
                    newState = DEAD;
                }

                // Any live cell with two or three live
                // neighbours lives on to the next generation.
                if (liveNeighbours == 2 || liveNeighbours == 3) {
                    newState = ALIVE;
                }

                // Any live cell with more than three live neighbours
                // dies, as if by overcrowding.
                if (liveNeighbours > 3) {
                    newState = DEAD;
                }
                break;

            case DEAD:
                // Any dead cell with exactly three live neighbours becomes a
                // live cell, as if by reproduction.
                if (liveNeighbours == 3) {
                    newState = ALIVE;
                }
                break;

            default:
                throw new IllegalArgumentException("State of cell must be either LIVE or DEAD");
        }
        return newState;
    }

    private int getLiveNeighbours(int cellRow, int cellCol, int[][] board) {

        int liveNeighbours = 0;
        int rowEnd = Math.min(board.length , cellRow + 2);
        int colEnd = Math.min(board[0].length, cellCol + 2);

        for (int row = Math.max(0, cellRow - 1) ; row < rowEnd ; row++) {

            for (int col = Math.max(0, cellCol - 1) ; col < colEnd ; col++) {

                // make sure to exclude the cell itself from calculation
                if ((row != cellRow || col != cellCol) && board[row][col] == ALIVE) {
                    liveNeighbours++;
                }
            }
        }
        return liveNeighbours;
    }

    public int[][] getNextGrid(int [][] grid){
        if (grid.length == 0 || grid[0].length == 0) {
            throw new IllegalArgumentException("Board must have a positive amount of rows and/or columns");
        }

        int nrRows = grid.length;
        int nrCols = grid[0].length;

        // temporary board to store new values
        int[][] buf = new int[nrRows][nrCols];

        for (int row = 0 ; row < nrRows ; row++) {

            for (int col = 0 ; col < nrCols ; col++) {
                buf[row][col] = getNewCellState(grid[row][col], getLiveNeighbours(row, col, grid));
            }
        }
        return buf;
    }



    public static void main(String[] args) {
        /*
         * Board is a 200x200 matrix.
         * Given are the locations of the alive cells.
         */
        ConwayGameOfLife conwayGameOfLife = new ConwayGameOfLife();
        int[][] locationOfAliveCells = {
                {5,5}, {6,5}, {7,5}, {5,6}, {6,6}, {7,6}
        };


        int[][] grid = conwayGameOfLife.initialiseGrid(200,200, locationOfAliveCells);
        System.out.println("The input locations of the alive cells/ Initial state:");
        conwayGameOfLife.displayGridAliceCellLocation(grid);
        // Simulations
        int iterations = 100;
        for(int i=0; i<iterations; i++){
            grid = conwayGameOfLife.getNextGrid(grid);
        }
        System.out.println();
        System.out.println("100th state: ");
        conwayGameOfLife.displayGridAliceCellLocation(grid);

    }



}
