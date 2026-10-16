package org.firstinspires.ftc.teamcode.Core;

import static org.firstinspires.ftc.teamcode.Core.OscarIntake.IntakeModes.*;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.Interfaces.Intake;

import java.util.ArrayList;

public class OscarIntake implements Intake {

    private final Robot robot;
    private boolean flaggedFrontBall = false;
    private boolean flaggedBackBall = false;
    private double timerStartTime = 0;
    private final ArrayList<ColorSensor> frontColorSensors = new ArrayList<ColorSensor>();
    private final ArrayList<ColorSensor> backColorSensors = new ArrayList<>();

    private static final double EMERGENCY_OUTTAKE_TIME = 1;

    public OscarIntake(Robot robot) {
        this.robot = robot;
        frontColorSensors.add(robot.frontColorSensorOne);
        frontColorSensors.add(robot.frontColorSensorTwo);
        frontColorSensors.add(robot.frontColorSensorThree);
        frontColorSensors.add(robot.frontColorSensorFour);
        backColorSensors.add(robot.backColorSensorOne);
        backColorSensors.add(robot.backColorSensorTwo);
        backColorSensors.add(robot.backColorSensorThree);
        backColorSensors.add(robot.backColorSensorFour);
    }
    private void flagBall() {
        for (ColorSensor colorSensor : frontColorSensors) {
            if (colorSensor.red() >= 0.5 && robot.alliance == Robot.Alliance.BLUE) {
                flaggedFrontBall = true;
            }
            if (colorSensor.blue() >= 0.5 && robot.alliance == Robot.Alliance.RED) {
                flaggedFrontBall = true;
            }
        }
        for (ColorSensor colorSensor : backColorSensors) {
            if (colorSensor.red() >= 0.5); {
                flaggedBackBall = true;
            }
            if (colorSensor.blue() >= 0.5); {
                flaggedBackBall = true;
            }
        }
    }
    enum IntakeModes {
        STANDBY, INTAKE, OUTTAKE, OUTTAKE_FOR_TIME
    }
    private IntakeModes currentFrontMode = STANDBY;
    private IntakeModes currentBackMode = STANDBY;

    @Override
    public void intake() {
        currentFrontMode = INTAKE;
        currentBackMode = INTAKE;
    }

    @Override
    public void outtake() {
        currentFrontMode = OUTTAKE;
        currentBackMode = OUTTAKE;
    }

    @Override
    public void stop() {
        currentFrontMode = STANDBY;
    }

    @Override
    public void update() {
        flagBall();
        switch (currentFrontMode) {
            case STANDBY:
                robot.frontIntakeMotor.setVelocity(0);
                break;
            case INTAKE:
                    robot.frontIntakeMotor.setVelocity(3000);
                    if (flaggedFrontBall) {
                        currentFrontMode = OUTTAKE;
                        timerStartTime = robot.runtime.seconds();
                    }
                break;
            case OUTTAKE:
                robot.frontIntakeMotor.setVelocity(-1000);
                break;
            case OUTTAKE_FOR_TIME:
                if (flaggedFrontBall) {
                    robot.frontIntakeMotor.setVelocity(-1000);
                } else if (robot.runtime.seconds() - timerStartTime >= EMERGENCY_OUTTAKE_TIME) {
                    currentFrontMode = INTAKE;
                }
                break;
        }

        switch (currentBackMode) {
            case STANDBY:
                robot.backIntakeMotor.setVelocity(0);
                break;
            case INTAKE:
                robot.backIntakeMotor.setVelocity(3000);
                if (flaggedBackBall) {
                    currentFrontMode = OUTTAKE;
                    timerStartTime = robot.runtime.seconds();
                }
                break;
            case OUTTAKE:
                robot.backIntakeMotor.setVelocity(-1000);
                break;
            case OUTTAKE_FOR_TIME:
                if (flaggedBackBall) {
                    robot.backIntakeMotor.setVelocity(-1000);
                } else if (robot.runtime.seconds() - timerStartTime >= EMERGENCY_OUTTAKE_TIME) {
                    currentFrontMode = INTAKE;
                }
                break;
        }
    }
}
