package org.firstinspires.ftc.teamcode.Core;

import org.firstinspires.ftc.teamcode.Interfaces.Intake;

public class FirstMeetIntake implements Intake {

    private final Robot robot;

    public FirstMeetIntake(Robot robot) {
        this.robot = robot;
    }


    @Override
    public void intake() {
        robot.frontIntakeMotor.setPower(0.75);
        robot.frontLauncherWheels.setVelocity(100);

    }

    @Override
    public void outtake() {
        robot.frontIntakeMotor.setPower(-0.5);
        robot.frontLauncherWheels.setVelocity(-100);

    }

    @Override
    public void stop() {
        robot.frontIntakeMotor.setPower(0);
        robot.frontLauncherWheels.setVelocity(100);
    }

    @Override
    public void update() {}

    @Override
    public void addTelemetry() {
        robot.telemetry.addLine("Intake:");
        robot.telemetry.addData("Intake Motor Speed", robot.frontIntakeMotor.getVelocity());
        robot.telemetry.addLine();
    }
}
