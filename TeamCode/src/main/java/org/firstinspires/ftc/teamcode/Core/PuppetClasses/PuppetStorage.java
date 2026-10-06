package org.firstinspires.ftc.teamcode.Core.PuppetClasses;

import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.Core.Robot;
import org.firstinspires.ftc.teamcode.Interfaces.Storage;
@Configurable
public class PuppetStorage implements Storage {
    static int ballCount;
    @Override
    public int getBallCount(Robot.BallColor color) {
        return ballCount;
    }

    @Override
    public void update() {

    }
}
