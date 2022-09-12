package RobotSimulator;

import java.util.Arrays;

public class Board {
    private final int boardSizeX = 5;
    private final int boardSizeY = 5;
    public Cell[][] board = new Cell[boardSizeX][boardSizeY];

    public Board() {
        this.fillBoard();
    }

    public void fillBoard(){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                this.board[i][j] = new Cell(i,j,null);
            }
        }
    }
    public Cell getCell(int xPos, int yPos) {
        return board[xPos][yPos];
    }

    public void setRobotToBoard(Cell robot){
        if(robot == null){
            return;
        }
        int xPos = robot.getxPos();
        int yPos = robot.getyPos();

        Cell[][] tmp = board;

        Cell currPosition = findRobot();
        if (currPosition != null) {
            int currxPos = currPosition.getxPos();
            int curryPos = currPosition.getyPos();
            tmp[currxPos][curryPos] = new Cell(currxPos,curryPos,null);
        }
        tmp[xPos][yPos] = robot;
        //Make changes in a temporary board to avoid multiple positions
        board = tmp;
    }

    public Cell findRobot(){
        for(int i = 0; i<board.length;i++){
            for(int j = 0; j< board.length;j++){
                if(board[i][j].getRobot() != null){
                    return board[i][j];
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String result = Arrays.deepToString(this.board).replace("], ", "]\n");
        return result;
    }
}
