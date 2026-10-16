package org.firstinspires.ftc.teamcode.Core;

import static org.firstinspires.ftc.teamcode.Core.HivePosition.*;
import static java.lang.Math.*;

import org.firstinspires.ftc.teamcode.Interfaces.HiveTipDetector;

public class HiveTipFinder implements HiveTipDetector {
    public HiveTipFinder(Robot robot) { this.robot = robot; }
    private final Robot robot;

    private static final double HIVE_UP_DISTANCE = 0, HIVE_DOWN_DISTANCE = 0, LEAN_TOLERANCE = 0;
    double distanceToApriltag, angleToHive, hiveHeight = 0;

    @Override
    public void start() {

    }
    private HivePosition hivePosition = DRIVER_SIDE;
    @Override
    public HivePosition getCurrentHivePosition() {
        if (false) { //TODO check if it is actually seeing an apriltag or not
            return hivePosition;
        }
        getRobotPositioning();
        if (true) { //TODO check if it is looking at start side, likely using apriltags
            if (hiveHeight > HIVE_UP_DISTANCE - LEAN_TOLERANCE) {
                hivePosition = DRIVER_SIDE;
                return DRIVER_SIDE;
            } else if (hiveHeight < HIVE_DOWN_DISTANCE + LEAN_TOLERANCE){
                hivePosition = OPPOSING_SIDE;
                return OPPOSING_SIDE;
            }
        } else {
            if (hiveHeight > HIVE_UP_DISTANCE - LEAN_TOLERANCE) {
                hivePosition = OPPOSING_SIDE;
                return OPPOSING_SIDE;
            } else if (hiveHeight < HIVE_DOWN_DISTANCE + LEAN_TOLERANCE){
                hivePosition = DRIVER_SIDE;
                return DRIVER_SIDE;
            }
        }
        //TODO find and return the way it is tipping
        //if it gets here hive is tipping and out of static tolerances
        //it should be tipping towards the opposite pos of whatever it last was
        if (hivePosition == DRIVER_SIDE) {
            hivePosition = TIPPING_OPPOSING_SIDE;
            return TIPPING_OPPOSING_SIDE;
        } else if (hivePosition == OPPOSING_SIDE) {
            hivePosition = TIPPING_DRIVER_SIDE;
            return TIPPING_DRIVER_SIDE;
        }
        //if it gets here it is still tipping
        return hivePosition;
    }

    @Override
    public void update() {

    }

    private void getRobotPositioning() {
        //TODO get dist to tag (using limelight)
        distanceToApriltag = 0;
        //TODO get robot angle vertically to hive (using limelight again)
        //it should know what the target angle is based on the side of the field the robot is on
        angleToHive = 0;
        //TODO finish doing maths if needed
        hiveHeight = distanceToApriltag * cos(angleToHive);
    }
}
