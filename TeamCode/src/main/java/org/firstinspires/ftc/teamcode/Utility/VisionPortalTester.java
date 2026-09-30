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
            new Scalar(27, 60, 130),
            new Scalar(32, 255, 255)
    );
    //use built in colors instead for nectar
    final static private ColorRange NECTAR_BLUE = new ColorRange(
            ColorSpace.HSV,
            new Scalar(115, 90, 65),
            new Scalar(122, 255, 255)
    );
    final static private ColorRange NECTAR_RED = new ColorRange(
            ColorSpace.HSV,
            new Scalar(160, 10, 10),
            new Scalar(20, 255, 255)
    );


    ColorBlobLocatorProcessor yellowColorLocator = new ColorBlobLocatorProcessor.Builder()
            .setTargetColorRange(POLLEN)   // Use a predefined color match
            .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
            .setRoi(ImageRegion.asUnityCenterCoordinates(-1, 1, 1, -1))
            .setDrawContours(true)   // Show contours on the Stream Preview
            .setBoxFitColor(0)       // Disable the drawing of rectangles
            .setCircleFitColor(Color.rgb(255, 255, 0)) // Draw a circle
            .setBlurSize(5)          // Smooth the transitions between different colors in image

            // the following options have been added to fill in perimeter holes.
            .setDilateSize(4)       // Expand blobs to fill any divots on the edges
            .setErodeSize(4)        // Shrink blobs back to original size
            .setMorphOperationType(ColorBlobLocatorProcessor.MorphOperationType.CLOSING)


            .build();
    ColorBlobLocatorProcessor blueColorLocator = new ColorBlobLocatorProcessor.Builder()
            .setTargetColorRange(ColorRange.BLUE)   // Use a predefined color match
            .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
            .setRoi(ImageRegion.asUnityCenterCoordinates(-1, 1, 1, -1))
            .setDrawContours(true)   // Show contours on the Stream Preview
            .setBoxFitColor(0)       // Disable the drawing of rectangles
            .setCircleFitColor(Color.rgb(0, 0, 255)) // Draw a circle
            .setBlurSize(5)          // Smooth the transitions between different colors in image

            // the following options have been added to fill in perimeter holes.
            .setDilateSize(4)       // Expand blobs to fill any divots on the edges
            .setErodeSize(4)        // Shrink blobs back to original size
            .setMorphOperationType(ColorBlobLocatorProcessor.MorphOperationType.CLOSING)


            .build();
    ColorBlobLocatorProcessor redColorLocator = new ColorBlobLocatorProcessor.Builder()
            .setTargetColorRange(ColorRange.RED)   // Use a predefined color match
            .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
            .setRoi(ImageRegion.asUnityCenterCoordinates(-1, 1, 1, -1))
            .setDrawContours(true)   // Show contours on the Stream Preview
            .setBoxFitColor(0)       // Disable the drawing of rectangles
            .setCircleFitColor(Color.rgb(255, 0, 0)) // Draw a circle
            .setBlurSize(5)          // Smooth the transitions between different colors in image

            // the following options have been added to fill in perimeter holes.
            .setDilateSize(4)       // Expand blobs to fill any divots on the edges
            .setErodeSize(4)        // Shrink blobs back to original size
            .setMorphOperationType(ColorBlobLocatorProcessor.MorphOperationType.CLOSING)


            .build();

    @Override
    public void init() {
        camera = hardwareMap.get(WebcamName.class, "Camera");
        portal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Camera"))
                .addProcessor(yellowColorLocator)
                .addProcessor(blueColorLocator)
                .addProcessor(redColorLocator)
                .setCameraResolution(new Size(320, 240))
                .build();
    }

    @Override
    public void loop() {
        List<ColorBlobLocatorProcessor.Blob> yellowBlobList = yellowColorLocator.getBlobs();
        List<ColorBlobLocatorProcessor.Blob> blueBlobList = blueColorLocator.getBlobs();
        List<ColorBlobLocatorProcessor.Blob> redBlobList = redColorLocator.getBlobs();

        ColorBlobLocatorProcessor.Util.filterByCriteria(
                ColorBlobLocatorProcessor.BlobCriteria.BY_CONTOUR_AREA,
                100, 20000, yellowBlobList);

        // Filter out ones that aren't circles
        ColorBlobLocatorProcessor.Util.filterByCriteria(
                ColorBlobLocatorProcessor.BlobCriteria.BY_CIRCULARITY,
                0.4, 1, yellowBlobList);


        if (!yellowBlobList.isEmpty()) {
            telemetry.addLine("Yellow:");
            for (ColorBlobLocatorProcessor.Blob ball : yellowBlobList) {
                telemetry.addLine("X: " + ball.getCircle().getX() +
                        " Y: " + ball.getCircle().getY()
                );
            }
            telemetry.addLine();
        }

        ColorBlobLocatorProcessor.Util.filterByCriteria(
                ColorBlobLocatorProcessor.BlobCriteria.BY_CONTOUR_AREA,
                100, 20000, blueBlobList);

        // Filter out ones that aren't circles
        ColorBlobLocatorProcessor.Util.filterByCriteria(
                ColorBlobLocatorProcessor.BlobCriteria.BY_CIRCULARITY,
                0.4, 1, blueBlobList);

        if (!blueBlobList.isEmpty()) {
            telemetry.addLine("Blue:");
            for (ColorBlobLocatorProcessor.Blob ball : blueBlobList) {
                telemetry.addLine("X: " + ball.getCircle().getX() +
                        " Y: " + ball.getCircle().getY()
                );
            }
            telemetry.addLine();
        }

        ColorBlobLocatorProcessor.Util.filterByCriteria(
                ColorBlobLocatorProcessor.BlobCriteria.BY_CONTOUR_AREA,
                100, 20000, redBlobList);

        // Filter out ones that aren't circles
        ColorBlobLocatorProcessor.Util.filterByCriteria(
                ColorBlobLocatorProcessor.BlobCriteria.BY_CIRCULARITY,
                0.4, 1, redBlobList);
        if (!redBlobList.isEmpty()) {
            telemetry.addLine("Red:");
            for (ColorBlobLocatorProcessor.Blob ball : redBlobList) {
                telemetry.addLine("X: " + ball.getCircle().getX() +
                        " Y: " + ball.getCircle().getY()
                );
            }
            telemetry.addLine();
        }

        telemetry.update();
    }
}
