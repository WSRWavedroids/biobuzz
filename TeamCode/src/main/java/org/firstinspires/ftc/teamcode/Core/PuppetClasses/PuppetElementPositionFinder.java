package org.firstinspires.ftc.teamcode.Core.PuppetClasses;

import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.Core.Robot;
import org.firstinspires.ftc.teamcode.Core.XYSet;
import org.firstinspires.ftc.teamcode.Interfaces.ElementPositionFinder;

import java.util.ArrayList;
@Configurable
public class PuppetElementPositionFinder implements ElementPositionFinder {
    public static ArrayList<XYSet> XYSet;
    @Override
    public ArrayList<XYSet> getBallPositions(Robot.BallColor targetColor) {
        return XYSet;
    }

    @Override
    public void update() {

    }
}
