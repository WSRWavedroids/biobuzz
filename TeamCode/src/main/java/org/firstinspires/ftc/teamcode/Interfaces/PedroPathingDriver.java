package org.firstinspires.ftc.teamcode.Interfaces;

import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;


import org.firstinspires.ftc.teamcode.Core.HiveSide;
import org.firstinspires.ftc.teamcode.Core.Robot;

public interface PedroPathingDriver {
    void start();
    void runToPosition(Pose pose);
    void followPath(Path path);
    Pose getPosition();
    boolean safeToFire();
    Path makePathToPose(Pose targetPose);
    HiveSide findHiveZone();
    double findDistanceToHive();

    Robot.FrontBack findHiveFacingDirection();

    void update();

    void addTelemetry();

}
