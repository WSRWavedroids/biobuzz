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
    private IntakeModes currentMode = STANDBY;
    @Override
    public void intake() {
        currentMode = INTAKE;
    }

    @Override
    public void outtake() {
        currentMode = OUTTAKE;
    }

    @Override
    public void stop() {
        currentMode = STANDBY;
    }

    @Override
    public void update() {
        flagBall();
        switch (currentMode) {
            case STANDBY:
                robot.frontIntakeMotor.setVelocity(0);
                robot.backIntakeMotor.setVelocity(0);
                break;
            case INTAKE:
                    robot.frontIntakeMotor.setVelocity(3000);
                    robot.backIntakeMotor.setVelocity(3000);
                    if (flaggedFrontBall || flaggedBackBall) {
                        currentMode = OUTTAKE;
                        timerStartTime = robot.runtime.seconds();
                    }
                break;
            case OUTTAKE:
                if (flaggedFrontBall) {
                    robot.frontIntakeMotor.setVelocity(-1000);
                } else if (robot.runtime.seconds() - timerStartTime >= 1){
                   currentMode = INTAKE;
                }
                if (flaggedBackBall) {
                    robot.backIntakeMotor.setVelocity(-1000);
                } else if (robot.runtime.seconds() - timerStartTime >= 1){
                    currentMode = INTAKE;
                }
                robot.frontIntakeMotor.setVelocity(-1000);
                robot.backIntakeMotor.setVelocity(-1000);
                break;
            case OUTTAKE_FOR_TIME:

                break;
        }
    }
}
