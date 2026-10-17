package org.firstinspires.ftc.teamcode.Core;


import com.pedropathing.math.Pose;

public enum HiveSide {
    SCORING(58,144 - 54,144 - 58,144 - 54),
    AUDIENCE(58,54,144 - 58,54),
    NONE(0,0,0,0);
    HiveSide(double redX, double redY, double blueX, double blueY) {
        RED_X = redX;
        RED_Y = redY;
        BLUE_X = blueX;
        BLUE_Y = blueY;

        RED_POSE = new Pose(redX, redY);
        BLUE_POSE = new Pose(blueX, blueY);
    }
    public final double RED_X, RED_Y, BLUE_X, BLUE_Y;
    public final Pose RED_POSE, BLUE_POSE;
}
