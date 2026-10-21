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

    enum IntakeModes {
        STANDBY, INTAKE, OUTTAKE, OUTTAKE_FOR_TIME
    }
    private IntakeModes currentFrontMode = STANDBY;
    private IntakeModes currentBackMode = STANDBY;


    public OscarIntake(Robot robot) {
        this.robot = robot;
        frontColorSensors.add(robot.frontColorSensorOne);
//        frontColorSensors.add(robot.frontColorSensorTwo);
//        frontColorSensors.add(robot.frontColorSensorThree);
//        frontColorSensors.add(robot.frontColorSensorFour);
        backColorSensors.add(robot.backColorSensorOne);
//        backColorSensors.add(robot.backColorSensorTwo);
//        backColorSensors.add(robot.backColorSensorThree);
//        backColorSensors.add(robot.backColorSensorFour);
    }


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
        currentBackMode = STANDBY;
    }

    @Override
    public void update() {
        flaggedFrontBall = flagBalls(frontColorSensors);
        flaggedBackBall = flagBalls(backColorSensors);
        switch (currentFrontMode) {
            case STANDBY:
                robot.frontIntakeMotor.setPower(0);
                break;
            case INTAKE:
                robot.frontIntakeMotor.setPower(1);
                if (flaggedFrontBall) {
                    currentFrontMode = OUTTAKE_FOR_TIME;
                    timerStartTime = robot.runtime.seconds();
                }
                break;
            case OUTTAKE:
                robot.frontIntakeMotor.setPower(-1);
                break;
            case OUTTAKE_FOR_TIME:
                if (flaggedFrontBall) {
                    robot.frontIntakeMotor.setPower(-1);
                } else if (robot.runtime.seconds() - timerStartTime >= EMERGENCY_OUTTAKE_TIME) {
                    currentFrontMode = INTAKE;
                    flaggedFrontBall = false;
                }
                break;
        }

        switch (currentBackMode) {
            case STANDBY:
                robot.backIntakeMotor.setPower(0);
                break;
            case INTAKE:
                robot.backIntakeMotor.setPower(1);
                if (flaggedBackBall) {
                    currentBackMode = OUTTAKE_FOR_TIME;
                    timerStartTime = robot.runtime.seconds();
                }
                break;
            case OUTTAKE:
                robot.backIntakeMotor.setPower(-1);
                break;
            case OUTTAKE_FOR_TIME:
                if (flaggedBackBall) {
                    robot.backIntakeMotor.setPower(-1);
                } else if (robot.runtime.seconds() - timerStartTime >= EMERGENCY_OUTTAKE_TIME) {
                    currentBackMode = INTAKE;
                }
                break;
        }

        robot.telemetry.addData("Front Intake Mode", currentFrontMode);
        robot.telemetry.addData("Back Intake Mode", currentBackMode);
        robot.telemetry.addData("Front Flagged", flaggedFrontBall);
        robot.telemetry.addData("Back Flagged", flaggedBackBall);
    }

    private boolean flagBalls(ArrayList<ColorSensor> sensorList) {
        for (ColorSensor colorSensor : sensorList) {
            if (colorSensor.red() >= 128 && robot.alliance == Robot.Alliance.BLUE) {
                return true;
            }
            if (colorSensor.blue() >= 128 && robot.alliance == Robot.Alliance.RED) {
                return true;
            }
        }
        return false;
    }
}
