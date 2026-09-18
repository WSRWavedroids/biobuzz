package org.firstinspires.ftc.teamcode.Utility;

import android.graphics.Color;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
import org.firstinspires.ftc.vision.opencv.ColorRange;
import org.firstinspires.ftc.vision.opencv.ColorSpace;
import org.firstinspires.ftc.vision.opencv.ImageRegion;
import org.opencv.core.Scalar;

import java.util.List;

@TeleOp(name = "Vision Portal Camera Test")
//@Utility(name = "Vision Portal Camera Test")
public class VisionPortalTester extends OpMode {

    private VisionPortal portal;
    private WebcamName camera;

    final static private ColorRange POLLEN = new ColorRange(
            ColorSpace.HSV,
            new Scalar(20, 100, 120),
            new Scalar(30, 255, 255)
    );

    ColorBlobLocatorProcessor purpleColorLocator = new ColorBlobLocatorProcessor.Builder()
            .setTargetColorRange(ColorRange.ARTIFACT_PURPLE)   // Use a predefined color match
            .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
            .setRoi(ImageRegion.asUnityCenterCoordinates(-1, 1, 1, -1))
            .setDrawContours(true)   // Show contours on the Stream Preview
            .setBoxFitColor(0)       // Disable the drawing of rectangles
            .setCircleFitColor(Color.rgb(255, 255, 0)) // Draw a circle
            .setBlurSize(5)          // Smooth the transitions between different colors in image

            // the following options have been added to fill in perimeter holes.
            .setDilateSize(3)       // Expand blobs to fill any divots on the edges
            .setErodeSize(3)        // Shrink blobs back to original size
            .setMorphOperationType(ColorBlobLocatorProcessor.MorphOperationType.CLOSING)

            .build();

    ColorBlobLocatorProcessor yellowColorLocator = new ColorBlobLocatorProcessor.Builder()
            .setTargetColorRange(POLLEN)   // Use a predefined color match
            .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
            .setRoi(ImageRegion.asUnityCenterCoordinates(-1, 1, 1, -1))
            .setDrawContours(true)   // Show contours on the Stream Preview
            .setBoxFitColor(0)       // Disable the drawing of rectangles
            .setCircleFitColor(Color.rgb(0, 255, 255)) // Draw a circle
            .setBlurSize(5)          // Smooth the transitions between different colors in image

            // the following options have been added to fill in perimeter holes.
            .setDilateSize(3)       // Expand blobs to fill any divots on the edges
            .setErodeSize(3)        // Shrink blobs back to original size
            .setMorphOperationType(ColorBlobLocatorProcessor.MorphOperationType.CLOSING)


            .build();

    @Override
    public void init() {
        camera = hardwareMap.get(WebcamName.class, "Camera");
        portal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Camera"))
                .addProcessor(purpleColorLocator)
                .addProcessor(yellowColorLocator)
                .setCameraResolution(new Size(320, 240))
                .build();
    }

    @Override
    public void loop() {
        List<ColorBlobLocatorProcessor.Blob> purpleBlobList = purpleColorLocator.getBlobs();
        List<ColorBlobLocatorProcessor.Blob> yellowBlobList = yellowColorLocator.getBlobs();

        // Filter out small blobs
        ColorBlobLocatorProcessor.Util.filterByCriteria(
                ColorBlobLocatorProcessor.BlobCriteria.BY_CONTOUR_AREA,
                100, 20000, purpleBlobList);

        // Filter out ones that aren't circles
        ColorBlobLocatorProcessor.Util.filterByCriteria(
                ColorBlobLocatorProcessor.BlobCriteria.BY_CIRCULARITY,
                0.6, 1, purpleBlobList);

        ColorBlobLocatorProcessor.Util.filterByCriteria(
                ColorBlobLocatorProcessor.BlobCriteria.BY_CONTOUR_AREA,
                50, 20000, yellowBlobList);

        // Filter out ones that aren't circles
        ColorBlobLocatorProcessor.Util.filterByCriteria(
                ColorBlobLocatorProcessor.BlobCriteria.BY_CIRCULARITY,
                0.4, 1, yellowBlobList);

        if (!purpleBlobList.isEmpty()) {
            telemetry.addLine("Purple:");
            for (ColorBlobLocatorProcessor.Blob ball : purpleBlobList) {
                telemetry.addLine("X: " + ball.getCircle().getX() +
                        " Y: " + ball.getCircle().getY()
                );
            }
            telemetry.addLine();
        }
        if (!yellowBlobList.isEmpty()) {
            telemetry.addLine("Yellow:");
            for (ColorBlobLocatorProcessor.Blob ball : yellowBlobList) {
                telemetry.addLine("X: " + ball.getCircle().getX() +
                        " Y: " + ball.getCircle().getY()
                );
            }
            telemetry.addLine();
        }

        telemetry.update();
    }
}
