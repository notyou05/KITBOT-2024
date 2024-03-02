// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.vision;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;

/** Add your docs here. */
public class Shape {
    private ShapeEnum name;
    private Point center;
    private MatOfPoint contour;

    public Shape(ShapeEnum shapeName, MatOfPoint shapeContour, Point shapeCenter) {
        name = shapeName;
        center = shapeCenter;
        contour = shapeContour;
    }
}
