package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(500);

        // Declare our first bot
        RoadRunnerBotEntity myFirstBot = new DefaultBotBuilder(meepMeep)
                // We set this bot to be blue
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 11.5)
                .build();

        myFirstBot.runAction(myFirstBot.getDrive().actionBuilder(new Pose2d(7.5, -66.5, Math.toRadians(90)))
                .strafeTo(new Vector2d(7.5,-35))
                .waitSeconds(1)
                .strafeTo(new Vector2d(7.5,-41))
                //preload scored
                .strafeTo(new Vector2d(33, -41))
                .splineToConstantHeading(new Vector2d(44, -18),Math.toRadians(90))
                .strafeTo(new Vector2d(48,-18))
                .strafeTo(new Vector2d(48,-58))
                //1 sample in human player zone
                .strafeTo(new Vector2d(48,-18))
                .strafeTo(new Vector2d(60, -18))
                .strafeTo(new Vector2d(60, -58))
                //2 samples in human player zone
                .strafeTo(new Vector2d(60, -18))
                .strafeTo(new Vector2d(67, -18))
                .strafeTo(new Vector2d(67, -58))
                //3 samples in human player zone
                .strafeTo(new Vector2d(67, -52))
                .waitSeconds(1)
                .strafeTo(new Vector2d(67, -66))
                .waitSeconds(.5)
                .splineToConstantHeading(new Vector2d(9, -35), Math.toRadians(90))
                //spec 2 scored
                .waitSeconds(.5)
                .strafeTo(new Vector2d(8.5, -41))
                .splineToConstantHeading(new Vector2d(58, -66), Math.toRadians(270))
                .waitSeconds(.5)
                .splineToConstantHeading(new Vector2d(6, -35), Math.toRadians(90))
                //spec 3 scored
                .waitSeconds(.5)
                .strafeTo(new Vector2d(6, -41))
                .splineToConstantHeading(new Vector2d(58, -66), Math.toRadians(270))
                .waitSeconds(.5)
                .splineToConstantHeading(new Vector2d(4.5, -35), Math.toRadians(90))
                //spec 4 scored
                .strafeTo(new Vector2d(4.5, -41))
                .splineToConstantHeading(new Vector2d(58, -66), Math.toRadians(270))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                // Add both of our declared bot entities
                .addEntity(myFirstBot)
                .start();
    }
}