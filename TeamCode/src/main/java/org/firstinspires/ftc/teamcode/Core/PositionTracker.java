package org.firstinspires.ftc.teamcode.Core;

import static org.firstinspires.ftc.teamcode.Core.HiveSide.AUDIENCE;
import static org.firstinspires.ftc.teamcode.Core.HiveSide.NONE;
import static org.firstinspires.ftc.teamcode.Core.HiveSide.SCORING;
import static org.firstinspires.ftc.teamcode.Core.Robot.Alliance.BLUE;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.Interfaces.PedroPathingDriver;
import org.firstinspires.ftc.teamcode.pedro.Constants;

public class PositionTracker implements PedroPathingDriver {

    private Robot robot;
    private Follower follower;
    private Pose currentPose;

    public PositionTracker(Robot robot) {
        this.robot = robot;
        follower = Constants.create(robot.hardwareMap);
    }

    @Override
    public void start() {

    }

    @Override
    public void runToPosition(Pose pose) {

    }

    @Override
    public void followPath(Path path) {

    }

    @Override
    public Pose getPosition() {
        return currentPose;
    }

    @Override
    public boolean safeToFire() {
        return false;
    }

    @Override
    public Path makePathToPose(Pose targetPose) {
        return null;
    }

    @Override
    public HiveSide findHiveZone() {
         if (robot.alliance == BLUE) {
            if (currentPose.x() >= 84) {
                if (currentPose.y() < currentPose.x() - 24) {
                    return AUDIENCE;
                } else if (currentPose.y() > -currentPose.x() + 168) {
                    return SCORING;
                } else {
                    return NONE;
                }
            } else {
                if (currentPose.y() < -currentPose.x() + 144) {
                    return AUDIENCE;
                } else if (currentPose.y() > currentPose.x()) {
                    return SCORING;
                } else {
                    return NONE;
                }
            }
        } else { // Alliance is red
            if (currentPose.x() >= 60) {
                if (currentPose.y() < currentPose.x()) {
                    return AUDIENCE;
                } else if (currentPose.y() > -currentPose.x() + 120) {
                    return SCORING;
                } else {
                    return NONE;
                }
            } else {
                if (currentPose.y() < -currentPose.x() + 144) {
                    return AUDIENCE;
                } else if (currentPose.y() > currentPose.x() + 24) {
                    return SCORING;
                } else {
                    return NONE;
                }
            }
        }
    }

    @Override
    public double findDistanceToHive() {
        Pose hivePose;
        if (robot.alliance == BLUE) {
            hivePose = findHiveZone().BLUE_POSE;
        } else {
            hivePose = findHiveZone().RED_POSE;
        }

        return currentPose.distance(hivePose);
    }

    @Override
    public void update() {
        follower.localizer.update();
        currentPose = follower.pose();
    }

    public void addTelemetry() {
        robot.telemetry.addLine("Position Tracker");
        robot.telemetry.addData("Current Pose", currentPose);
        robot.telemetry.addLine();
    }
}
