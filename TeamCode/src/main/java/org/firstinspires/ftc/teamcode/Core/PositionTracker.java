package org.firstinspires.ftc.teamcode.Core;

import static org.firstinspires.ftc.teamcode.Core.HiveSide.AUDIENCE;
import static org.firstinspires.ftc.teamcode.Core.HiveSide.NONE;
import static org.firstinspires.ftc.teamcode.Core.HiveSide.SCORING;
import static org.firstinspires.ftc.teamcode.Core.Robot.Alliance.BLUE;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.Interfaces.PedroPathingDriver;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class PositionTracker implements PedroPathingDriver {

    private Robot robot;
    private Follower follower;
    private Pose currentPose;

    public PositionTracker(Robot robot) {
        this.robot = robot;
        follower = Constants.createFollower(robot.hardwareMap);
    }

    @Override
    public void start() {

    }

    @Override
    public void runToPosition(Pose pose) {

    }

    @Override
    public void followPath(PathChain path) {

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
    public PathChain makeChainToPose(Pose targetPose) {
        return null;
    }

    @Override
    public HiveSide findHiveZone() {
         if (robot.alliance == BLUE) {
            if (currentPose.getX() >= 84) {
                if (currentPose.getY() < currentPose.getX() - 24) {
                    return AUDIENCE;
                } else if (currentPose.getY() > -currentPose.getX() + 168) {
                    return SCORING;
                } else {
                    return NONE;
                }
            } else {
                if (currentPose.getY() < -currentPose.getX() + 144) {
                    return AUDIENCE;
                } else if (currentPose.getY() > currentPose.getX()) {
                    return SCORING;
                } else {
                    return NONE;
                }
            }
        } else { // Alliance is red
            if (currentPose.getX() >= 60) {
                if (currentPose.getY() < currentPose.getX()) {
                    return AUDIENCE;
                } else if (currentPose.getY() > -currentPose.getX() + 120) {
                    return SCORING;
                } else {
                    return NONE;
                }
            } else {
                if (currentPose.getY() < -currentPose.getX() + 144) {
                    return AUDIENCE;
                } else if (currentPose.getY() > currentPose.getX() + 24) {
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

        return currentPose.distanceFrom(hivePose);
    }

    @Override
    public void update() {
        follower.updatePose();
        currentPose = follower.getPose();
    }
}
