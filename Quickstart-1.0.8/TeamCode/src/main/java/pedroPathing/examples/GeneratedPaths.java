package pedroPathing.examples;

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
                            new Point(8.000, 80.000, Point.CARTESIAN),
                            new Point(36.000, 80.000, Point.CARTESIAN)
                    )
            )
            .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
            .build();

    public static PathChain line2 = builder
            .addPath(
                    new BezierCurve(
                            new Point(36.000, 80.000, Point.CARTESIAN),
                            new Point(55.000, 45.000, Point.CARTESIAN),
                            new Point(32.000, 101.000, Point.CARTESIAN)
                    )
            )
            .setTangentHeadingInterpolation()
            .build();
}
