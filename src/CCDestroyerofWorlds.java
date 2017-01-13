// package cwclsrhs;
import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.*;
import java.util.*;
import java.io.*;

public class CCDestroyerofWorlds extends AdvancedRobot {
    /**
     * run: CCDestroyerofWorlds's default behavior
     */
    private byte scanDirection = 1;	int count = 0; // Keeps track of how long we've
    // been searching for our target
    double gunTurnAmt; // How much to turn our gun when searching
    double radarTurnAmt;
    private boolean movingForward;

    private ScannedRobotEvent target;
    private ScannedRobotEvent targetLast;

    private boolean oscillator;
    String trackName;

    public static int randomInt(int min, int max) { //generates a random integer between the two parameters
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    private void initialize() {
        setAdjustGunForRobotTurn(true);
        setAdjustRadarForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setColors(Color.red,Color.white,Color.blue);
    }

    public void run() {
        // Initialization of the robot should be put here

        // After trying out your robot, try uncommenting the import at the top,
        // and the next line:
        // body,gun,radar

        // Robot main loop
        initialize();
        while(true) {
            gunTurnAmt = 10; // Initialize gunTurn to 10
            radarTurnAmt = 10;
            // Replace the next 4 lines with any behavior you would like
            // setTurnLeft(randomInt(20, 360));
            int degrees = randomInt(20, 360);
            if (degrees < 180)
                setTurnRight(degrees);
            else {
                setTurnLeft(180 - degrees);
            }
//            int gunDegrees = randomInt(20, 360);
//            if (gunDegrees < 180)
//                setTurnRadarRight(gunDegrees);
//            else {
//                setTurnRadarLeft(180- gunDegrees);
//            }
            setTurnRadarRight(180);

            setAhead(randomInt (-50, 200));
            execute();
        }
    }

    public void reverseDirection() {
        if (movingForward) {
            setBack(40000);
            movingForward = false;
        } else {
            setAhead(40000);
            movingForward = true;
        }
    }


    /**
     * onScannedRobot: What to do when you see another robot
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // Replace the next line with any behavior you would like
        gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getGunHeading()));
        radarTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
        setTurnGunRight(gunTurnAmt);
        if (oscillator) {
            setTurnRadarRight(radarTurnAmt - 5);
        } else {
            setTurnRadarRight(radarTurnAmt + 5);
        }

        setFire(3);
        scan();
    }


    /**
     * onHitByBullet: What to do when you're hit by a bullet
     */
    public void onHitByBullet(HitByBulletEvent e) {
        // Replace the next line with any behavior you would like
        back(10);
    }

    public void onHitRobot(HitRobotEvent e) {
        if (e.isMyFault()) {
            reverseDirection();
        }
//        double gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
//        turnGunRight(gunTurnAmt);
//        setFire(3);
//        back(50);
    }

    /**
     * onHitWall: What to do when you hit a wall
     */
    public void onHitWall(HitWallEvent e) {
        // Replace the next line with any behavior you would like

        reverseDirection();
    }
}

