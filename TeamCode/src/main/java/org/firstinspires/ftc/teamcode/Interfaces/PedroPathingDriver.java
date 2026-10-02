package org.firstinspires.ftc.teamcode.Interfaces;

import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;

public interface PedroPathingDriver {
    void start();
    void runToPosition(Pose pose);
    void followPath(PathChain path);
    Pose getPosition();
    boolean safeToFire();
    PathChain makeChainToPose(Pose targetPose);
}
