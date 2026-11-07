package org.firstinspires.ftc.teamcode.Core;

import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.Core.PuppetClasses.PuppetLauncher;
import org.firstinspires.ftc.teamcode.Interfaces.Launcher;

@Configurable
public class LauncherTester implements Launcher {
    private Robot robot;
    private final int TICKS_PER_ROTATION = 28;

    private final ezPID front;
    private final ezPID back;
    public LauncherTester(Robot robot) {
        this.robot = robot;
        front = new ezPID(robot.frontLauncherWheels, TICKS_PER_ROTATION, p, i, d, f, kneecapIN, tolerance, ezPID.movementType.SPEED);
        back = new ezPID(robot.backLauncherWheels, TICKS_PER_ROTATION, p, i, d, f, kneecapIN, tolerance, ezPID.movementType.SPEED);
    }
    public static double frontLauncherSpeed, backLauncherSpeed = 0, p = 0, i = 0, d = 0, f = 0, kneecapIN = 0, tolerance = 0;
    public static boolean isFiring;
    public static double launcherServoPosition = 0.5;
    public static double hoodPosition = 1;
    @Override
    public void fire(FireMode mode) {

    }
    @Override
    public boolean isFiring() {
        return isFiring;
    }
    @Override
    public void update() {
        front.runCalledPID(frontLauncherSpeed);
        back.runCalledPID(backLauncherSpeed);
        front.changeBehaviorValues(p,i,d,f,kneecapIN,tolerance);
        back.changeBehaviorValues(p,i,d,f,kneecapIN,tolerance);

        robot.frontLauncherServo.setPosition(launcherServoPosition);
        robot.backLauncherServo.setPosition(launcherServoPosition);

        robot.frontHoodServo.setPosition(hoodPosition);
        robot.backHoodServo.setPosition(hoodPosition);
    }

    @Override
    public void addTelemetry() {

    }
}
