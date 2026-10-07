package org.firstinspires.ftc.teamcode.Interfaces;

import org.firstinspires.ftc.teamcode.Core.FireMode;

public interface Launcher {
    void fire(FireMode mode);

    boolean isFiring();

    void update();
}
