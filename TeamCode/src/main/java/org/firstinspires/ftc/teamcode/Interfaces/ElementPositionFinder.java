package org.firstinspires.ftc.teamcode.Interfaces;

import org.firstinspires.ftc.teamcode.Core.Robot;
import org.firstinspires.ftc.teamcode.Core.XYSet;

import java.util.ArrayList;

public interface ElementPositionFinder {
    ArrayList<XYSet> getBallPositions(Robot.BallColor targetColor);

    void update();

}

