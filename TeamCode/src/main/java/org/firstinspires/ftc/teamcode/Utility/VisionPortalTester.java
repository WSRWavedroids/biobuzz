package org.firstinspires.ftc.teamcode.Utility;

import android.graphics.Color;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Utility;

import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
import org.firstinspires.ftc.vision.opencv.ColorRange;
import org.firstinspires.ftc.vision.opencv.ImageRegion;

import java.util.List;

@TeleOp(name = "Vision Portal Camera Test")
//@Utility(name = "Vision Portal Camera Test")
public class VisionPortalTester extends OpMode {

    private VisionPortal portal;
    ColorBlobLocatorProcessor colorLocator = new ColorBlobLocatorProcessor.Builder()
            .setTargetColorRange(ColorRange.ARTIFACT_PURPLE)   // Use a predefined color match
            .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
            .setRoi(ImageRegion.asUnityCenterCoordinates(-0.75, 0.75, 0.75, -0.75))
            .setDrawContours(true)   // Show contours on the Stream Preview
            .setBoxFitColor(0)       // Disable the drawing of rectangles
            .setCircleFitColor(Color.rgb(255, 255, 0)) // Draw a circle
            .setBlurSize(5)          // Smooth the transitions between different colors in image

            // the following options have been added to fill in perimeter holes.
            .setDilateSize(3)       // Expand blobs to fill any divots on the edges
            .setErodeSize(3)        // Shrink blobs back to original size
            .setMorphOperationType(ColorBlobLocatorProcessor.MorphOperationType.CLOSING)

            .build();

    @Override
    public void init() {
        portal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Camera"))
                .addProcessor(colorLocator)
                .setCameraResolution(new Size(320, 240))
                .build();
    }

    @Override
    public void loop() {
        List<ColorBlobLocatorProcessor.Blob> blobList = colorLocator.getBlobs();

        // Filter out small blobs
        ColorBlobLocatorProcessor.Util.filterByCriteria(
                ColorBlobLocatorProcessor.BlobCriteria.BY_CONTOUR_AREA,
                100, 20000, blobList);

        // Filter out ones that aren't circles
        ColorBlobLocatorProcessor.Util.filterByCriteria(
                ColorBlobLocatorProcessor.BlobCriteria.BY_CIRCULARITY,
                0.6, 1, blobList);

        if (!blobList.isEmpty()) {
            for (ColorBlobLocatorProcessor.Blob ball : blobList) {
                telemetry.addLine("X: " + ball.getCircle().getX() +
                        "Y: " + ball.getCircle().getY());
            }
        }

        telemetry.update();
    }
}
