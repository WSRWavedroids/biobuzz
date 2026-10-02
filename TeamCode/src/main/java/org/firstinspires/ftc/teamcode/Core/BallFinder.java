package org.firstinspires.ftc.teamcode.Core;

import java.util.ArrayList;

public class BallFinder implements ElementPositionFinder{
    Robot robot;
    public ElementFinderCamera[] cameras;
    public BallFinder (Robot robot, ElementFinderCamera... elementFinderCameras) {
        this.robot = robot;
        cameras = elementFinderCameras;
    }

    public static final double POLLENREFERENCE = 0, NECTARREFERANCE = 0; //size at 1-meter distance
    public static final double POLLENRADIUS = 1.45, NECTARRADIUS = 1.8; //inches
    public double distance = 0, theta = 0;
    public double r = 0;

    @Override
    public ArrayList<XYSet> getBallPositions(Robot.BallColor targetColor) {
        return null;
    }
    private void foodini() {
        for (ElementFinderCamera camera : cameras) {
            //r = Math.sqrt(Math.pow(distance, 2) - Math.pow(elementFinderCameras.y /*- ballRadius*/, 2));
            //must be stored as a seprate r in each.
        }
    }
    @Override
    public void update() {
        foodini();
    }
}
