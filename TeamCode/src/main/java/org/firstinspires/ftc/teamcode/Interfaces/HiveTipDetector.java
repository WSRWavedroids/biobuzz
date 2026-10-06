package org.firstinspires.ftc.teamcode.Interfaces;

import org.firstinspires.ftc.teamcode.Core.HivePosition;

public interface HiveTipDetector {
    void start();

    HivePosition getCurrentHivePosition();

    void update();
}
