package org.firstinspires.ftc.teamcode.Interfaces;

import org.firstinspires.ftc.teamcode.Core.Robot;

public interface Storage {
    int getBallCount(Robot.BallColor color);
    void update();
}
