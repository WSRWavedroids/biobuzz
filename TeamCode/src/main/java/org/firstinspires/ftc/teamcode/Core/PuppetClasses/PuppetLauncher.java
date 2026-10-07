package org.firstinspires.ftc.teamcode.Core.PuppetClasses;

import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.Core.FireMode;
import org.firstinspires.ftc.teamcode.Interfaces.Launcher;
@Configurable
public class PuppetLauncher implements Launcher {
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

    }
}
