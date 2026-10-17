package org.firstinspires.ftc.teamcode.Core;

import static com.pedropathing.ivy.commands.Commands.*;
import static com.pedropathing.ivy.groups.Groups.*;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;

import org.firstinspires.ftc.teamcode.Interfaces.Launcher;

public class IvyLauncher implements Launcher {

    private final Robot robot;

    private final Command fireNow;
    private final Command fireWithinSecond;
    private final Command fireWhenSafe;


    private static final double NECTAR_FIRE_TIME = 1;
    private static final double POLLEN_FIRE_TIME = 1;

    private double frontTargetSpeed, backTargetSpeed = 0;


    public IvyLauncher(Robot robot) {
        this.robot = robot;

        Command revMotor = Command.build()
                .setStart(this::revMotor)
                .setDone(() -> robot.frontLauncherWheels.withinTolerance() && robot.backLauncherWheels.withinTolerance())
                .requiring(robot.frontLauncherWheels, robot.backLauncherWheels);

        Command lift = instant(this::lift);

        Command adjustForPollen = instant(this::adjustServoForPollen);

        Command reset = instant(this::reset);


        Command launcherSequence = sequential(
                revMotor,
                lift,
                waitMs(NECTAR_FIRE_TIME * 1000),
                adjustForPollen,
                waitMs(POLLEN_FIRE_TIME * 1000),
                reset
        );

        fireNow = launcherSequence.unless(this::isNotSafeToFire);

        fireWhenSafe = waitUntil(this::isSafeToFire).then(launcherSequence);

        fireWithinSecond = race(waitMs(1000), waitUntil(this::isSafeToFire))
                .then(fireNow);

    }

    @Override
    public void fire(FireMode mode) {
        switch (mode) {
            case FIRE_NOW_IF_READY:
                fireNow.schedule();
                break;
            case WAIT_FOR_SECOND_FOR_SAFE:
                fireWithinSecond.schedule();
                break;
            case FIRE_WHENEVER_SAFE:
                fireWhenSafe.schedule();
                break;
            case NOT_FIRING:
                fireNow.cancel();
                fireWithinSecond.cancel();
                fireWhenSafe.cancel();
        }
    }

    @Override
    public boolean isFiring() {
        return fireNow.isScheduled() || fireWithinSecond.isScheduled() || fireWhenSafe.isScheduled();
    }

    @Override
    public void update() {
        robot.frontLauncherWheels.runCalledPID(frontTargetSpeed);
        robot.backLauncherWheels.runCalledPID(backTargetSpeed);
    }

    private void revMotor() {
        frontTargetSpeed = findFrontMotorSpeed();
        backTargetSpeed = findBackMotorSpeed();
    }

    private void lift() {
        // TODO
    }

    private void adjustServoForPollen() {
        // TODO
    }

    private void reset() {
        frontTargetSpeed = 0;
        backTargetSpeed = 0;
        // TODO reset lift and other servo
    }

    private boolean isSafeToFire() {
        return false;
    }
    private boolean isNotSafeToFire() {
        return !isSafeToFire();
    }

    private double findFrontMotorSpeed() {
        return 0;
    }

    private double findBackMotorSpeed() {
        return 0;
    }

}
