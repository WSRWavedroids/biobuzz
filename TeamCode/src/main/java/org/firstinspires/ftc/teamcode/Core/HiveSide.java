package org.firstinspires.ftc.teamcode.Core;

public enum HiveSide {
    SCORING(58,144 - 54,144 - 58,144 - 54),
    AUDIENCE(58,54,144 - 58,54),
    NONE(0,0,0,0);
    HiveSide(double redX, double redY, double blueX, double blueY) {
        RED_X = redX;
        RED_Y = redY;
        BLUE_X = blueX;
        BLUE_Y = blueY;
    }
    public final double RED_X, RED_Y, BLUE_X, BLUE_Y;
}
