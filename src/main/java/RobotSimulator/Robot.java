package RobotSimulator;

public class Robot {
    private Directions directions;

    public Robot(Directions direction) {
        this.directions = direction;
    }

    public Directions getDirections() {
        return directions;
    }

    public void setDirections(Directions directions) {
        this.directions = directions;
    }
}
