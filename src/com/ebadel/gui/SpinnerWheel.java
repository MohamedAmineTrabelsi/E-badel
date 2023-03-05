package com.ebadel.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class SpinnerWheel extends Canvas {
    private SpinnerWheel spinnerWheel;

    private static final double WHEEL_RADIUS = 100;
    private static final double WHEEL_DIAMETER = WHEEL_RADIUS * 2;
    private static final double CENTER_X = WHEEL_RADIUS;
    private static final double CENTER_Y = WHEEL_RADIUS;

    private static final Color[] COLORS = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PURPLE };
    private static final String[] SECTIONS = { "Section 1", "Section 2", "Section 3", "Section 4", "Section 5", "Section 6" };

    public SpinnerWheel() {
        super(WHEEL_DIAMETER, WHEEL_DIAMETER);
    }

    
    public void paint() {
        GraphicsContext gc = getGraphicsContext2D();

        double angle = 0;
        double sectionAngle = 360.0 / SECTIONS.length;

        for (int i = 0; i < SECTIONS.length; i++) {
            gc.setFill(COLORS[i]);
            gc.fillArc(0, 0, WHEEL_DIAMETER, WHEEL_DIAMETER, angle, sectionAngle, javafx.scene.shape.ArcType.ROUND);
            gc.setFill(Color.WHITE);
            gc.fillArc(20, 20, WHEEL_DIAMETER - 40, WHEEL_DIAMETER - 40, angle, sectionAngle, javafx.scene.shape.ArcType.ROUND);
            gc.setStroke(Color.BLACK);
            gc.strokeArc(0, 0, WHEEL_DIAMETER, WHEEL_DIAMETER, angle, sectionAngle, javafx.scene.shape.ArcType.ROUND);

            gc.save();
            gc.translate(CENTER_X, CENTER_Y);
            gc.rotate(angle + sectionAngle / 2);
            gc.setFill(Color.BLACK);
            gc.fillText(SECTIONS[i], WHEEL_RADIUS - 40, 0);
            gc.restore();

            angle += sectionAngle;
        }
    }

    public void rotate(double angle) {
        GraphicsContext gc = getGraphicsContext2D();

        gc.clearRect(0, 0, getWidth(), getHeight());
        gc.save();
        gc.translate(CENTER_X, CENTER_Y);
        gc.rotate(angle);
        paint();
        gc.restore();
    }
}