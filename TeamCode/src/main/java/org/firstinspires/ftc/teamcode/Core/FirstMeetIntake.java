package org.firstinspires.ftc.teamcode.Core;

import static org.firstinspires.ftc.teamcode.Core.OscarIntake.IntakeModes.*;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.Interfaces.Intake;

import java.util.ArrayList;

public class FirstMeetIntake implements Intake {

    private final Robot robot;

    public FirstMeetIntake(Robot robot) {
        this.robot = robot;
    }


    @Override
    public void intake() {
        robot.frontIntakeMotor.setPower(1);
    }

    @Override
    public void outtake() {
        robot.frontIntakeMotor.setPower(-1);
    }

    @Override
    public void stop() {
        robot.frontIntakeMotor.setPower(0);
    }

    @Override
    public void update() {
    }

    @Override
    public void addTelemetry() {
        robot.telemetry.addLine("Intake:");
        robot.telemetry.addData("Intake Motor", robot.frontIntakeMotor);
        robot.telemetry.addLine();
    }
}
