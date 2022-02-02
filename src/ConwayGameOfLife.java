import java.util.ArrayList;

public class ConwayGameOfLife {

    private final static int DEAD = 0; // Representation of a dead cell
    private final static int ALIVE = 1; // Representation of a alive cell

    /**
     * This function prints the grid to the console.
     * @param grid
     */
    public void displayGrid(int [][] grid){
       if (grid != null){
           for (int i = 0, e = grid.length ; i < e ; i++) {

               for (int j = 0, f = grid[i].length ; j < f ; j++) {
                   System.out.print(Integer.toString(grid[i][j]) + ",");
               }
               System.out.println();
           }
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




    public static void main(String[] args) {
        /*
         * Board is a 200x200 matrix.
         * Given are the locations of the alive cells.
         */
        ConwayGameOfLife conwayGameOfLife = new ConwayGameOfLife();


        int[][] locationOfAliveCells = {
                {5,5}, {6,5}, {7,5}, {5,6}, {6,6}, {7,6}
        };

        System.out.println("Print ");
        int[][] grid = conwayGameOfLife.initialiseGrid(200,200, locationOfAliveCells);
        conwayGameOfLife.displayGrid(grid);



    }



}
