package RobotSimulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotSimulatorTest {

    @Test
    void firstPlaceCommandTest(){
        RobotSimulator robotsim = new RobotSimulator();
        assertEquals(true,robotsim.decode("PLACE 0,0,NORTH"));
    }

    @Test
    void ignoreCommandsBeforeFirstPlaceTest(){
        var robotsimulator = new RobotSimulator();

        assertEquals(false, robotsimulator.decode("MOVE"));
        assertEquals(false, robotsimulator.decode("FAKEINPUT"));
        assertEquals(true, robotsimulator.decode("PLACE 0,0,NORTH"));
    }

    @Test
    void checkConstraint(){
        var robotsimulator = new RobotSimulator();

        robotsimulator.decode("PLACE 0,0,SOUTH");
        robotsimulator.decode("MOVE");
        assertEquals("Output: 0,0,SOUTH",robotsimulator.reportCommand());
        robotsimulator.decode("RIGHT");
        robotsimulator.decode("MOVE");
        assertEquals("Output: 0,0,WEST",robotsimulator.reportCommand());

        robotsimulator.decode("PLACE 4,4,NORTH");
        robotsimulator.decode("MOVE");
        assertEquals("Output: 4,4,NORTH",robotsimulator.reportCommand());
        robotsimulator.decode("RIGHT");
        robotsimulator.decode("MOVE");
        assertEquals("Output: 4,4,EAST",robotsimulator.reportCommand());
    }

    @Test
    void ignoreCommandsBeforePlace(){
        var robotsimulator = new RobotSimulator();

        assertEquals(false, robotsimulator.decode("MOVE"));
        assertEquals(false, robotsimulator.decode("FAKEINPUT"));
        assertEquals(false, robotsimulator.decode("LEFT"));
        assertEquals(false, robotsimulator.decode("RIGHT"));
        assertEquals(false, robotsimulator.decode("REPORT"));

    }
}