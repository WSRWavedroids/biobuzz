package org.firstinspires.ftc.teamcode.Core;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public class ElementFinderCamera {
    public final WebcamName camera;
    public final double x, y, z, verticalAngleRadians, horizontalAngleRadians;
    public ElementFinderCamera(WebcamName camera, double x, double y, double z, double verticalAngleRadians, double horizontalAngleRadians) {
        this.camera = camera;
        this.x = x;
        this.y = y;
        this.z = z;
        this.verticalAngleRadians = verticalAngleRadians;
        this.horizontalAngleRadians = horizontalAngleRadians;
    }
}
