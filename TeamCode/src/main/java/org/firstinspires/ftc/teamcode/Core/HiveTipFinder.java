package org.firstinspires.ftc.teamcode.Core;

import static java.lang.Math.*;

import org.firstinspires.ftc.teamcode.Interfaces.HiveTipDetector;

public class HiveTipFinder implements HiveTipDetector {
    public HiveTipFinder(Robot robot) { this.robot = robot; }
    private final Robot robot;

    private static final double HIVE_UP_DISTANCE = 0, HIVE_DOWN_DISTANCE = 0, LEAN_TOLERANCE = 0;

    @Override
    public void start() {

    }

    @Override
    public HivePosition getCurrentHivePosition() {

        //TODO get robot angle to hive (pedropathing) and dist to tag (limelight)
        double distance = 0;
        double angle = 0;
        double estimatedHeight = distance * cos(angle);
        /* if () { //TODO check if it is looking at start side, likely using apriltags
            if (estimatedHeight > HIVE_UP_DISTANCE - LEAN_TOLERANCE) {
            //TODO return up position
            } else if (estimatedHeight < HIVE_DOWN_DISTANCE + LEAN_TOLERANCE){
            //TODO return down position
            }
        } else {
            if (estimatedHeight > HIVE_UP_DISTANCE - LEAN_TOLERANCE) {
            //TODO return down position
            } else if (estimatedHeight < HIVE_DOWN_DISTANCE + LEAN_TOLERANCE){
            //TODO return up position
            }
        }*/

        return null; //TODO return that it is currently tipping
    }

    @Override
    public void update() {

    }
}
