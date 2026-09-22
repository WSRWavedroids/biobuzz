package org.firstinspires.ftc.teamcode.Core;

import com.pedropathing.geometry.Pose;

import java.util.ArrayList;

public interface ElementPositionFinder {
    ArrayList<XYSet> getBallPositions(Robot.BallColor targetColor);
}

