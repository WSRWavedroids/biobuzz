package org.firstinspires.ftc.teamcode.Core.PuppetClasses;

import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.Core.Robot;
import org.firstinspires.ftc.teamcode.Interfaces.BallPathChain;

public class PuppetBallPathChain implements BallPathChain {
    private final Robot robot;
    public PuppetBallPathChain(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void goGetBalls() {

    }

    @Override
    public void update() {

    }

    @Override
    public void isDone() {

    }

    @Override
    public void addTelemetry() {

    }
}
