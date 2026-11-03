package org.firstinspires.ftc.teamcode.Core.PuppetClasses;

import static org.firstinspires.ftc.teamcode.Core.HiveSide.*;
import static org.firstinspires.ftc.teamcode.Core.Robot.Alliance.*;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.Core.HiveSide;
import org.firstinspires.ftc.teamcode.Core.Robot;
import org.firstinspires.ftc.teamcode.Interfaces.PedroPathingDriver;
@Configurable
public class PuppetPedroPathingDriver implements PedroPathingDriver {
    private Robot robot;
    public static boolean safeToFire = false;

    public static double x, y, heading = 0;
    public static HiveSide hiveZone = NONE;

    public PuppetPedroPathingDriver(Robot robot) {
        this.robot = robot;
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
    public boolean safeToFire() {
        return safeToFire;
    }

    @Override
    public Pose getPosition() {
        return new Pose(x, y, heading);
    }

    @Override
    public Path makePathToPose(Pose targetPose) {
        // TODO decide on the intended behavior here
        return null;
    }

    @Override
    public HiveSide findHiveZone() {
        if (robot.alliance == BLUE) {
            if (x >= 84) {
                if (y < x - 24) {
                    return AUDIENCE;
                } else if (y > -x + 168) {
                    return SCORING;
                } else {
                    return NONE;
                }
            } else {
                if (y < -x + 144) {
                    return AUDIENCE;
                } else if (y > x) {
                    return SCORING;
                } else {
                    return NONE;
                }
            }
        } else { // Alliance is red
            if (x >= 60) {
                if (y < x) {
                    return AUDIENCE;
                } else if (y > -x + 120) {
                    return SCORING;
                } else {
                    return NONE;
                }
            } else {
                if (y < -x + 144) {
                    return AUDIENCE;
                } else if (y > x + 24) {
                    return SCORING;
                } else {
                    return NONE;
                }
            }
        }
    }

    @Override
    public double findDistanceToHive() {
        double hiveX;
        double hiveY;
        HiveSide hiveZone = findHiveZone();

        if (robot.alliance == BLUE) {
            hiveX = hiveZone.BLUE_X;
            hiveY = hiveZone.BLUE_Y;
        } else {
            hiveX = hiveZone.RED_X;
            hiveY = hiveZone.RED_Y;
        }

        return Math.sqrt(Math.pow(x - hiveX, 2) + Math.pow(y - hiveY, 2));
    }

    @Override
    public void update() {

    }

    @Override
    public void addTelemetry() {

    }
}
