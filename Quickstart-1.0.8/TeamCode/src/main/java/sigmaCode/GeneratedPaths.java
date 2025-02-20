package sigmaCode;

import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;

//todo: replace below with generated code from the visualizer, and separate lines
public class GeneratedPaths {

    public static PathBuilder builder = new PathBuilder();

    public static PathChain line1 = builder
            .addPath(
                    new BezierLine(
                            new Point(7.200, 65.000, Point.CARTESIAN),
                            new Point(34.000, 65.000, Point.CARTESIAN)
                    )
            )
            .setConstantHeadingInterpolation(Math.toRadians(0))
            .build();

    public static PathChain line2 = builder
            .addPath(
                    new BezierCurve(
                            new Point(34.000, 65.000, Point.CARTESIAN),
                            new Point(1.669, 25.272, Point.CARTESIAN),
                            new Point(130.172, 25.748, Point.CARTESIAN),
                            new Point(20.000, 29.801, Point.CARTESIAN)
                    )
            )
            .setConstantHeadingInterpolation(Math.toRadians(0))
            .build();

    public static PathChain line3 = builder
            .addPath(
                    new BezierCurve(
                            new Point(20.000, 29.801, Point.CARTESIAN),
                            new Point(112.768, 12.636, Point.CARTESIAN),
                            new Point(20.000, 15.735, Point.CARTESIAN)
                    )
            )
            .setConstantHeadingInterpolation(Math.toRadians(0))
            .build();

    public static PathChain line4 = builder
            .addPath(
                    new BezierCurve(
                            new Point(20.000, 15.735, Point.CARTESIAN),
                            new Point(80.106, 15.974, Point.CARTESIAN),
                            new Point(90.119, 3.815, Point.CARTESIAN),
                            new Point(20.000, 9.060, Point.CARTESIAN)
                    )
            )
            .setConstantHeadingInterpolation(Math.toRadians(0))
            .build();

    public static PathChain line5 = builder
            .addPath(
                    new BezierLine(
                            new Point(20.000, 9.060, Point.CARTESIAN),
                            new Point(8.000, 33.000, Point.CARTESIAN)
                    )
            )
            .setConstantHeadingInterpolation(Math.toRadians(0))
            .build();
}
