package org.firstinspires.ftc.teamcode.Core;

import static org.firstinspires.ftc.teamcode.Core.FireMode.*;
import static org.firstinspires.ftc.teamcode.Core.OscarLauncher.LauncherState.*;

import org.firstinspires.ftc.teamcode.Interfaces.Launcher;

public class OscarLauncher implements Launcher {
    public OscarLauncher(Robot robot) {
        this.robot = robot;
    }
    private final Robot robot;
    enum LauncherState {
        STANDBY, WAIT_SECOND_FOR_SAFE, REV_MOTOR, LIFT, ADJUST_FOR_POLLEN, RESET
    }
    private LauncherState currentState = STANDBY;
    private boolean triggerLaunch = false;
    private double timerStartTime = 0;
    private FireMode fireMode = NOT_FIRING;

    private static final double NECTAR_FIRE_TIME = 1;
    private static final double POLLEN_FIRE_TIME = 1;

    @Override
    public void fire(FireMode mode) {
        fireMode = mode;
    }

    @Override
    public boolean isFiring() {
        return false;
    }

    @Override
    public void update() {
        robot.frontLauncherWheels.runCalledPID(0);
        robot.backLauncherWheels.runCalledPID(0);


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
                if (robot.frontLauncherWheels.withinTolerance() && robot.backLauncherWheels.withinTolerance()) {
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
                // todo reset lift, turn off motors, set to standby
                break;
        }
    }

    private void startRevvingMotors() {
        timerStartTime = robot.runtime.seconds();
        currentState = REV_MOTOR;
    }

    private void lift() {
        // todo Make it lift
        timerStartTime = robot.runtime.seconds();
        currentState = LIFT;
    }

    private void adjustForPollen() {
        // todo Close the servo
        timerStartTime = robot.runtime.seconds();
        currentState = ADJUST_FOR_POLLEN;
    }

    private boolean isSafeToFire() {
        return false;
    }

    private double findFrontMotorSpeed() {
        return 0;
    }

    private double findBackMotorSpeed() {
        return 0;
    }
}
