package org.firstinspires.ftc.teamcode.Core.PuppetClasses;

import static org.firstinspires.ftc.teamcode.Core.HivePosition.*;

import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.Core.HivePosition;
import org.firstinspires.ftc.teamcode.Core.Robot;
import org.firstinspires.ftc.teamcode.Interfaces.HiveTipDetector;
@Configurable
public class PuppetHiveTipDetector implements HiveTipDetector {
    private final Robot robot;
    public PuppetHiveTipDetector(Robot robot) {
        this.robot = robot;
    }
    public static HivePosition hivePosition = DRIVER_SIDE;

    @Override
    public void start() {

    }

    @Override
    public HivePosition getCurrentHivePosition() {
        return hivePosition;
    }

    @Override
    public void update() {

    }

    @Override
    public void addTelemetry() {

    }
}
