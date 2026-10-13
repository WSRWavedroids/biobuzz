package org.firstinspires.ftc.teamcode.Interfaces;

import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.Core.HiveSide;

public interface PedroPathingDriver {
    void start();
    void runToPosition(Pose pose);
    void followPath(PathChain path);
    Pose getPosition();
    boolean safeToFire();
    PathChain makeChainToPose(Pose targetPose);
    HiveSide findHiveZone();
    double findDistanceToHive();
    void update();

}
