package org.firstinspires.ftc.teamcode.Core;

import static org.firstinspires.ftc.teamcode.Core.OscarIntake.IntakeModes.*;

import org.firstinspires.ftc.teamcode.Interfaces.Intake;

public class OscarIntake implements Intake {
    public OscarIntake(Robot robot) {
        this.robot = robot;
    }
    private final Robot robot;
    private boolean flaggedBall;
    private boolean full;
    private boolean outtake;
    private void flagBall() {
    //TODO make flagging
    }
    enum IntakeModes {
        STANDBY, INTAKE, OUTTAKE,
    }
    private IntakeModes currentMode = STANDBY;
    @Override
    public void intake() {
        currentMode = INTAKE;
    }

    @Override
    public void outtake() {
        currentMode = OUTTAKE;
        outtake = true;
    }

    @Override
    public void stop() {

    }

    @Override
    public void update() {
        flagBall();
        switch (currentMode) {
            case INTAKE:
                outtake = false;
                if (!full) {
                    robot.intakeMotor.setVelocity(3000);
                } else if(flaggedBall) {
                    currentMode = OUTTAKE;
                }else {
                    currentMode = STANDBY;
                }
                break;
            case OUTTAKE:
                    robot.intakeMotor.setVelocity(1000);
                break;
                //TODO OUTAKE_FOR_TIME case
            case STANDBY:
                outtake = false;
                    robot.intakeMotor.setVelocity(0);
                break;
        }
    }
}
