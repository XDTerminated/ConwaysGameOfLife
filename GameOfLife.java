
public class GameOfLife {
    static final int ROWS = 30;
    static final int COLS = 30;
    static int[][] gameBoard = new int[ROWS][COLS];



    public static void randomSeed() {
        gameBoard[5][4] = 1;
        gameBoard[5][5] = 1;
        gameBoard[6][5] = 1;
        gameBoard[7][5] = 1;
        gameBoard[7][8] = 1;
        gameBoard[7][9] = 1;

        gameBoard[4][8] = 1;





    }

    public static void drawBoard() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (gameBoard[i][j] == 1) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }
    }

    private static int countNeighbors(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < 30 && j >= 0 && j < 30) {
                    if (gameBoard[i][j] == 1) {
                        count++;
                    }
                }
            }
        }
        return count - gameBoard[row][col];
    }
    
    public static void nextGeneration() {
        int[][] newGrid = new int[30][30];

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                int neighbors = countNeighbors(i, j);

                if (gameBoard[i][j] == 1) {
                    if (neighbors < 2) {
                        newGrid[i][j] = 0;
                    } else if (neighbors == 2 || neighbors == 3) {
                        newGrid[i][j] = 1;
                    } else if (neighbors > 3) {
                        newGrid[i][j] = 0;
                    }
                } else {
                    if (neighbors == 3) {
                        newGrid[i][j] = 1;
                    }
                }
            }
        }

        gameBoard = newGrid;
    }

    public static void main(String[] args) {
        randomSeed();
        while (true) {
            drawBoard();
            nextGeneration();
            try {
                Thread.sleep(500);
                System.out.println("\033[H\033[2J");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        
    }

}