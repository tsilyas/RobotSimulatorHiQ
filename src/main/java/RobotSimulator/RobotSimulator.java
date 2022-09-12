package RobotSimulator;

public class RobotSimulator {
    private Board board;

    public RobotSimulator() {
        this.board = new Board();
    }

    /**
     * Decoding the input.txt file.
    */
    public boolean decode(String input) {
        Cell robot = board.findRobot();
        if(robot == null){
            if(input.contains("PLACE")){
                Cell firstRobot = decodePlaceCommand(input);
                placeCommand(firstRobot);
                return true;
            }
            return false;
        }

        String command = input;

        if(input.contains("PLACE")){
            robot = decodePlaceCommand(input);
            command = command.substring(0, 5);
        }

        switch (command) {
            case "PLACE":
                placeCommand(robot);
                break;
            case "MOVE":
                moveCommand();
                break;
            case "LEFT":
            case "RIGHT":
                directionCommand(command);
                break;
            case "REPORT":
                reportCommand();
                break;
            default:
        }
        return true;
    }

    /**
     * logic for commands "LEFT" and "RIGHT"
    */

    private void directionCommand(String dir) {
        Cell robot = board.findRobot();
        if (robot == null) {
            return;
        }
        switch (robot.getRobot().getDirections()) {
            case NORTH:
                if(dir.equals("RIGHT")){
                    robot.getRobot().setDirections(Directions.EAST);
                } else robot.getRobot().setDirections(Directions.WEST);
                break;
            case WEST:
                if(dir.equals("RIGHT")){
                    robot.getRobot().setDirections(Directions.NORTH);
                } else robot.getRobot().setDirections(Directions.SOUTH);
                break;
            case SOUTH:
                if(dir.equals("RIGHT")){
                    robot.getRobot().setDirections(Directions.WEST);
                } else robot.getRobot().setDirections(Directions.EAST);
                break;
            case EAST:
                if(dir.equals("RIGHT")){
                    robot.getRobot().setDirections(Directions.SOUTH);
                } else robot.getRobot().setDirections(Directions.NORTH);
                break;
            default:
        }
    }

    public boolean placeCommand(Cell placeRobot) {
        if(placeRobot.getxPos() > 5 || placeRobot.getxPos() < 0 || placeRobot.getyPos() < 0 || placeRobot.getyPos() > 5){
            return false;
        }
        board.setRobotToBoard(placeRobot);
        return true;
    }

    public void moveCommand() {
        Cell robot = board.findRobot();
        if (robot != null) {
            if (robot.getRobot().getDirections() == Directions.NORTH) {
                moveDirection(robot, 0, 1);
            } else if (robot.getRobot().getDirections() == Directions.WEST) {
                moveDirection(robot, -1, 0);
            } else if (robot.getRobot().getDirections() == Directions.EAST) {
                moveDirection(robot, 1, 0);
            } else if (robot.getRobot().getDirections() == Directions.SOUTH) {
                moveDirection(robot, 0, -1);
            }
        }
    }

    public String reportCommand() {
        Cell robotPosition = board.findRobot();
        if (robotPosition == null) {
            return null;
        }
        System.out.println("Output: " + robotPosition.getxPos() + "," + robotPosition.getyPos() + "," + robotPosition.getRobot().getDirections());
        return "Output: " + robotPosition.getxPos() + "," + robotPosition.getyPos() + "," + robotPosition.getRobot().getDirections();
    }

    private void moveDirection(Cell robot, int moveX, int moveY) {
        if (robot.getxPos() + moveX < 0 || robot.getxPos() + moveX >= board.board.length || robot.getyPos() + moveY < 0 || robot.getyPos() + moveY >= board.board.length) {
            return;
        }
        Cell robotCell = new Cell(robot.getxPos() + moveX, robot.getyPos() + moveY, new Robot(robot.getRobot().getDirections()));
        board.setRobotToBoard(robotCell);

        return;
    }

    /**
     * Private help methods
     */

    private Directions convertDirectionEnum(String direction) {
        for(Directions dir : Directions.values()){
            if(direction.equals(String.valueOf(dir))){
                return dir;
            }
        }
        return null;
    }

    private Cell decodePlaceCommand(String input){
        int xPos = Character.getNumericValue(input.charAt(6));
        int yPos = Character.getNumericValue(input.charAt(8));
        String direction = input.substring(10);

        return new Cell(xPos,yPos,new Robot(convertDirectionEnum(direction)));
    }

}
