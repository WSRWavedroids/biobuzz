package org.firstinspires.ftc.teamcode.Core.PuppetClasses;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.Interfaces.PedroPathingDriver;
@Configurable
public class PuppetPedroPathingDriver implements PedroPathingDriver {
    public static boolean safeToFire = false;

    public static double x, y, heading = 0;

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
    public boolean safeToFire() {
        return safeToFire;
    }

    @Override
    public Pose getPosition() {
        return new Pose(x, y, heading);
    }

    @Override
    public PathChain makeChainToPose(Pose targetPose) {
        // TODO decide on the intended behavior here
        return new PathChain();
    }
}
