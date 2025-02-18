package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
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

        myFirstBot.runAction(myFirstBot.getDrive().actionBuilder(new Pose2d(11, -66.5, Math.toRadians(90)))

                .strafeTo(new Vector2d(11,-34))
        //preload scored
                .strafeTo(new Vector2d(11,-41))
                //.strafeTo(new Vector2d(33, -41))
                .splineToConstantHeading(new Vector2d(48, -18),Math.toRadians(30))
                .strafeTo(new Vector2d(48,-58))
                //1 sample in human player zone
                .splineToConstantHeading(new Vector2d(60, -18), Math.toRadians(300))
                .splineToConstantHeading(new Vector2d(60, -58), Math.toRadians(90))
                //2 samples in human player zone
                .strafeTo(new Vector2d(60, -52))
                //wait for human player
                .waitSeconds(.25)
                .strafeTo(new Vector2d(60, -68))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                // Add both of our declared bot entities
                .addEntity(myFirstBot)
                .start();
    }
}