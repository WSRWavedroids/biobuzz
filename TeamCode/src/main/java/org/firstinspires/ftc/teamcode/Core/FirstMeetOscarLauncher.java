package org.firstinspires.ftc.teamcode.Core;

import static org.firstinspires.ftc.teamcode.Core.FireMode.NOT_FIRING;
import static org.firstinspires.ftc.teamcode.Core.FirstMeetOscarLauncher.LauncherState.*;


import org.firstinspires.ftc.teamcode.Interfaces.Launcher;

public class FirstMeetOscarLauncher implements Launcher {
    private final Robot robot;
    private final ezPID front, back;
    enum LauncherState {
        STANDBY, WAIT_SECOND_FOR_SAFE, REV_MOTOR, LIFT, ADJUST_FOR_POLLEN, RESET
    }
    private LauncherState currentState = STANDBY;
    private boolean firing = false;
    private double timerStartTime = 0;
    private FireMode fireMode = NOT_FIRING;
    private double frontTargetSpeed, backTargetSpeed = 0;

    private final int TICKS_PER_ROTATION = 28;
    private final double p = 0.0005;
    private final double i = 0;
    private final double d = 0.0;
    private final double f = 0;
    private final double KNEECAP = 1;
    private final double TOLERANCE = 50;


    private static final double NECTAR_FIRE_TIME = 1;
    private static final double POLLEN_FIRE_TIME = 1;

    /**
     * When we fire pollen, "0 degrees" is no longer straight up. This number is the offset, in
     * degrees, required to find the temporary relative zero.
     */
    private static final double DEGREES_OFFSET_WHILE_POLLEN = 0; //TODO

    public FirstMeetOscarLauncher(Robot robot) {
        this.robot = robot;
        front = new ezPID(robot.frontLauncherWheels, TICKS_PER_ROTATION, p, i, d, f, KNEECAP, TOLERANCE, ezPID.movementType.SPEED);
        back = new ezPID(robot.backLauncherWheels, TICKS_PER_ROTATION, p, i, d, f, KNEECAP, TOLERANCE, ezPID.movementType.SPEED);

    }

    @Override
    public void fire(FireMode mode) {
        fireMode = mode;
    }

    @Override
    public boolean isFiring() {
        return firing;
    }

    @Override
    public void update() {
        front.runCalledPID(frontTargetSpeed);
        back.runCalledPID(backTargetSpeed);


        switch (currentState) {
            case STANDBY:
                switch (fireMode) {
                    case NOT_FIRING:
                        break;
                    case FIRE_NOW_IF_READY:
                        fireMode = NOT_FIRING;
                        if (isSafeToFire()) {
                            startRevvingMotors();
                        }
                        break;
                    case WAIT_FOR_SECOND_FOR_SAFE:
                        fireMode = NOT_FIRING;
                        timerStartTime = robot.runtime.seconds();
                        currentState = WAIT_SECOND_FOR_SAFE;
                        break;
                    case FIRE_WHENEVER_SAFE:
                        if (isSafeToFire()) {
                            startRevvingMotors();
                            fireMode = NOT_FIRING;
                        }
                        break;
                }
                break;
            case WAIT_SECOND_FOR_SAFE:
                if (isSafeToFire()) {
                    startRevvingMotors();
                } else if (robot.runtime.seconds() - timerStartTime >= 1) {
                    currentState = STANDBY;
                }
                break;
            case REV_MOTOR:
                if (front.withinTolerance() && back.withinTolerance()) {
                    lift();
                }
                break;
            case LIFT:
                if (robot.runtime.seconds() - timerStartTime >= NECTAR_FIRE_TIME) {
                    adjustForPollen();
                }
                break;
            case ADJUST_FOR_POLLEN:
                if (robot.runtime.seconds() - timerStartTime >= POLLEN_FIRE_TIME) {
                    currentState = RESET;
                }
                break;
            case RESET:
                robot.flipperLift.setPosition(0);

                robot.frontLauncherServo.setPosition(0);
                robot.backLauncherServo.setPosition(0);

                robot.frontHoodServo.setPosition(1);
                robot.backHoodServo.setPosition(1);

                frontTargetSpeed = 0;
                backTargetSpeed = 0;

                currentState = STANDBY;
                firing = false;
                break;
        }
    }

    @Override
    public void addTelemetry() {
        robot.telemetry.addLine("Launcher:");
        robot.telemetry.addData("Launcher State", currentState);
        robot.telemetry.addData("Firing", firing);
        robot.telemetry.addData("Front Wheel Velocity", robot.frontLauncherWheels.getVelocity());
        robot.telemetry.addData("Front Wheel Target Velocity", frontTargetSpeed);
        robot.telemetry.addData("Back Wheel Velocity", robot.backLauncherWheels.getVelocity());
        robot.telemetry.addData("Back Wheel Target Velocity", backTargetSpeed);
        robot.telemetry.addLine();
    }

    private void startRevvingMotors() {
        firing = true;

        robot.frontHoodServo.setPosition(findFrontHoodNectarPosition());
        robot.backHoodServo.setPosition(findBackHoodNectarPosition());

        timerStartTime = robot.runtime.seconds();
        currentState = REV_MOTOR;
    }


    private void lift() {
        robot.flipperLift.setPosition(1);
        timerStartTime = robot.runtime.seconds();
        currentState = LIFT;
    }

    private void adjustForPollen() {
        robot.frontLauncherServo.setPosition(1);
        robot.backLauncherServo.setPosition(1);

        robot.frontHoodServo.setPosition(findFrontHoodPollenPosition());
        robot.backHoodServo.setPosition(findBackHoodPollenPosition());

        timerStartTime = robot.runtime.seconds();
        currentState = ADJUST_FOR_POLLEN;
    }

    private boolean isSafeToFire() {
        return true;
    }

    private double findFrontMotorSpeed() {
        return 500;
    }

    private double findBackMotorSpeed() {
        return 500;
    }

    private double findFrontHoodNectarPosition() {return 1;}
    private double findBackHoodNectarPosition() {
        return angleToServoPosition(
                findHoodAngle(0)
        );
    }

    private double findFrontHoodPollenPosition() {return 1;}
    private double findBackHoodPollenPosition() {
        return angleToServoPosition(
            findHoodAngle(0) + DEGREES_OFFSET_WHILE_POLLEN
        );
    }


    private static final double TUNED_SERVO_UNITS = 0.5;
    private static final double TUNED_DEGREES = 60;
    private double angleToServoPosition(double angleDegreesFromVertical) {
        return (TUNED_DEGREES - angleDegreesFromVertical) * TUNED_SERVO_UNITS / TUNED_DEGREES;
    }

    private double findHoodAngle(double distance) {
        return 30;
    }
}
