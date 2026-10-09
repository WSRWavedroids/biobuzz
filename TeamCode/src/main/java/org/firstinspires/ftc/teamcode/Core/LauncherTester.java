package org.firstinspires.ftc.teamcode.Core;

import org.firstinspires.ftc.teamcode.Core.PuppetClasses.PuppetLauncher;
import org.firstinspires.ftc.teamcode.Interfaces.Launcher;

public class LauncherTester implements Launcher {
    private Robot robot;
    public LauncherTester(Robot robot) {
        this.robot = robot;
    }
    public static double frontLauncherSpeed, backLauncherSpeed = 0, p = 0, i = 0, d = 0, f = 0, kneecapIN = 0, tolerance = 0;
    public static boolean isFiring;
    @Override
    public void fire(FireMode mode) {

    }
    @Override
    public boolean isFiring() {
        return isFiring;
    }
    @Override
    public void update() {
        robot.frontLauncherWheels.runCalledPID(frontLauncherSpeed);
        robot.backLauncherWheels.runCalledPID(backLauncherSpeed);
        robot.frontLauncherWheels.changeBehaviorValues(p,i,d,f,kneecapIN,tolerance);
        robot.backLauncherWheels.changeBehaviorValues(p,i,d,f,kneecapIN,tolerance);
    }
}
