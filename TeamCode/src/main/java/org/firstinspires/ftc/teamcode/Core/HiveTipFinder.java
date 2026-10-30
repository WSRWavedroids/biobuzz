package org.firstinspires.ftc.teamcode.Core;

import static org.firstinspires.ftc.teamcode.Core.HivePosition.*;

import org.firstinspires.ftc.teamcode.Interfaces.HiveTipDetector;

public class HiveTipFinder implements HiveTipDetector {
    public HiveTipFinder(Robot robot) { this.robot = robot; }
    private final Robot robot;
    //TODO actually set this stuff
    private static final double HIVE_UP_DISTANCE = 0, HIVE_DOWN_DISTANCE = 0, LEAN_TOLERANCE = 0;
    double hiveHeight = 0;
    double distanceToApriltag = 0;

    @Override
    public void start() {

    }
    private HivePosition hivePosition = DRIVER_SIDE;
    @Override
    public HivePosition getCurrentHivePosition() {
        if (robot.limelight.getLatestResult() != null && robot.limelight.getLatestResult().isValid()) {
            return hivePosition;
        }
        getRobotPositioning();
        if (true) { //TODO check if limelight sees start side apriltag
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
        //if it gets here hive is outside static tolerances (currently tipping)
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

    private String getRobotPositioning() {
        distanceToApriltag = robot.getApriltagDistance();
        double cosVerticalHiveAngle = robot.pedroPathingDriver.findDistanceToHive() / distanceToApriltag;
        hiveHeight = distanceToApriltag * cosVerticalHiveAngle;
        return "";
    }

    public void addTelemetry() {
        robot.telemetry.addLine("Hive-Tip Finder");
        robot.telemetry.addData("Hive Position", hivePosition);
        robot.telemetry.addData("Apriltag Distance", distanceToApriltag);
        robot.telemetry.addData("Hive Height", hiveHeight);
        robot.telemetry.addLine();
    }
}
