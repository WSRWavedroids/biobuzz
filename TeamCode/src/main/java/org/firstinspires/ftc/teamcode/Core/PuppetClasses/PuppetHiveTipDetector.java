package org.firstinspires.ftc.teamcode.Core.PuppetClasses;

import static org.firstinspires.ftc.teamcode.Core.HivePosition.*;

import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.Core.HivePosition;
import org.firstinspires.ftc.teamcode.Interfaces.HiveTipDetector;
@Configurable
public class PuppetHiveTipDetector implements HiveTipDetector {

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
}
