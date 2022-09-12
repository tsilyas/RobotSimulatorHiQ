package RobotSimulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        RobotSimulator robotsim = new RobotSimulator();

        File file = new File("src/main/java/input.txt");
        Scanner sc = new Scanner(file);
        while(sc.hasNext()){
            robotsim.decode(sc.nextLine());
        }
    }
}
