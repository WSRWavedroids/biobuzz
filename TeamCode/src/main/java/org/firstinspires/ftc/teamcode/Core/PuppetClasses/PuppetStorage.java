package org.firstinspires.ftc.teamcode.Core.PuppetClasses;

import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.Core.Robot;
import org.firstinspires.ftc.teamcode.Interfaces.Storage;
@Configurable
public class PuppetStorage implements Storage {
    public static int pollenCount, allianceNectarCount, nonAllianceNectarCount = 0;
    @Override
    public int getBallCount(Robot.BallColor color) {
        switch (color) {
            case ANY:
                return pollenCount + allianceNectarCount + nonAllianceNectarCount;
            case ANY_VALID:
                return pollenCount + allianceNectarCount;
            case POLLEN:
                return pollenCount;
            case ALLIANCE_NECTAR:
                return allianceNectarCount;
            case NON_ALLIANCE_NECTAR:
                return nonAllianceNectarCount;
            case ANY_NECTAR:
                return allianceNectarCount + nonAllianceNectarCount;
            default:
                throw new IllegalArgumentException("Invalid color!");
        }
    }

    @Override
    public void update() {

    }
}
