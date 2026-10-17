package org.firstinspires.ftc.teamcode.Core;

import static org.firstinspires.ftc.teamcode.Core.Robot.MoveDirection;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

import static android.os.SystemClock.sleep;

import static com.pedropathing.api.Paths.curve;
import static com.pedropathing.api.Paths.line;
import static com.pedropathing.paths.interpolator.Interpolator.linear;

import com.bylazar.panels.Panels;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This class provides movement functions for autonomous.
 */

public class AutonomousPLUS {

    public Robot robot;

    // This section tells the program all of the different pieces of hardware that are on our robot that we will use in the program.
    public ElapsedTime runtime = new ElapsedTime();

    /**
     * The speed of the robot, expressed as potential motor power from 0-1. Make sure this is set on
     * a per-opmode basis.
     */
    public double speed;

    public AutonomousPLUS(Robot robot) {
        this.robot = robot;
        this.robot.panels = Panels.INSTANCE;
    }

    /**
     * Checks to see if a started movement is completed. If it is, does the cleanup work for it.
     * @return True if completed, otherwise false.
     */
    public boolean checkMovement() {
        if (robot.isWheelsBusy()) {
            return false;
        } else {
            robot.stopDriveMotors();
            robot.resetDriveEncoders();
            robot.encoderRunningMode();
            return true;
        }
    }

    /**
     * Starts strafing or turning the robot a number of ticks in a direction.
     * @param direction The Robot.MoveDirection enum, for the direction
     * @param ticks The number of ticks to target moving
     */
    public void move(MoveDirection direction, int ticks) {
        robot.setTargets(direction, ticks, true);
        robot.positionRunningMode();
        robot.powerSet(speed);
    }

    /**
     * Moves without changing the anchor point of the movements. It's like an "absolute mode" of
     * sorts.
     * @param direction The Robot.MoveDirection enum, for the direction
     * @param ticks The number of ticks to target moving
     */
    public void tentativeMove(MoveDirection direction, int ticks) {
        robot.setTargets(direction, ticks, false);
        robot.positionRunningMode();
        robot.powerSet(speed);
    }

    /**
     * Strafes or turns the robot a number of ticks in a direction. It will only return after the
     * movement is complete. Updates Robot while waiting.
     * @param direction The Robot.MoveDirection enum, for the direction
     * @param ticks The number of ticks to move
     * @param pause Milliseconds to wait after the movement
     */
    public void moveAndStall(MoveDirection direction, int ticks, long pause) {
        move(direction, ticks);

        while (checkMovement()) {
            robot.update();
        }

        sleep(pause);
    }

    /**
     * Pauses.
     * @param pause The time to pause, in milliseconds
     */
    public void prepareNextAction(long pause) {
        sleep(pause);
    }

    /**
     * Sets the speed of the robot as a value from 0-1.
     */
    public void setSpeed(double speed) {
        // Validate input
        if (speed < 0) speed = 0;
        else if (speed > 1) speed = 1;

        // Set it
        this.speed = speed;
    }

    /**
     * Sets the tolerance for the drive train motors to accept as "in position" when in position
     * running mode.
     * @param tolerance The tolerance in ticks
     */
    public void setTolerances(int tolerance) {
        robot.frontLeftDrive.setTargetPositionTolerance(tolerance);
        robot.frontRightDrive.setTargetPositionTolerance(tolerance);
        robot.backLeftDrive.setTargetPositionTolerance(tolerance);
        robot.backRightDrive.setTargetPositionTolerance(tolerance);
    }

    public Path makeDynamicPath(Follower follower, Pose targetPose) {
        return line(follower.pose(), targetPose);

    }

    // Game-specific auto-only functions can go here:


}
