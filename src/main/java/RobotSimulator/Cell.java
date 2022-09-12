package RobotSimulator;

public class Cell {
    private int xPos;
    private int yPos;
    private Robot robot;

    public Cell(int xPos, int yPos, Robot robot) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.setRobot(robot);
    }

    public int getxPos() {
        return xPos;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public int getyPos() {
        return yPos;
    }

    @Override
    public String toString() {
        String result = "\nCell: [" + getxPos() + "],[" + getyPos() + "]";
        if(getRobot() != null){
            result += " Direction: " + getRobot().getDirections();
        }
        return result;
    }
}
