package org.firstinspires.ftc.teamcode.Core;

import com.pedropathing.geometry.Pose;

import java.util.ArrayList;

public abstract class ElementPositionFinder {
    public ElementFinderCamera[] cameras;
    public ElementPositionFinder(ElementFinderCamera... elementFinderCameras) {
        cameras = elementFinderCameras;
    }
    public abstract ArrayList<XYSet> getBallPositions(Robot.BallColor targetColor);

    public abstract void update();

}

