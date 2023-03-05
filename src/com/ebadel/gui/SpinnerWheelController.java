import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SpinnerWheelController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Canvas wheelCanvas;

    @FXML
    private Button spinButton;

    private final List<String> customers = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Frank");

    private final List<Color> colors = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.PURPLE);

    private final double startAngle = -90;

    private final double arcAngle = 360.0 / customers.size();

    private final double centerX = 150;

    private final double centerY = 150;

    private final double radius = 120;

    private final Random random = new Random();

    @FXML
    private void initialize() {
        drawWheel();
    }

    private void drawWheel() {
        GraphicsContext gc = wheelCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, wheelCanvas.getWidth(), wheelCanvas.getHeight());
        for (int i = 0; i < customers.size(); i++) {
            gc.setFill(getColorForCustomer(i));
            gc.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, startAngle + i * arcAngle, arcAngle, javafx.scene.shape.ArcType.ROUND);
            drawTextOnArc(gc, customers.get(i), startAngle + i * arcAngle, arcAngle, centerX, centerY, radius);
        }
    }

    private Color getColorForCustomer(int index) {
        return colors.get(index % colors.size());
    }

    private void drawTextOnArc(GraphicsContext gc, String text, double startAngle, double arcExtent, double centerX, double centerY, double radius) {
        Font font = new Font(14);
        gc.setFont(font);
        gc.setFill(Color.WHITE);

        double halfArcExtent = Math.toRadians(arcExtent / 2.0);
        double centerX1 = centerX + radius * Math.cos(Math.toRadians(startAngle + arcExtent / 2));
        double centerY1 = centerY + radius * Math.sin(Math.toRadians(startAngle + arcExtent / 2));
        double x1 = centerX1;
        double y1 = centerY1;

    Rotate rotation = new Rotate(startAngle + halfArcExtent, centerX, centerY);
    gc.setTransform(rotation.getMxx(), rotation.getMyx(), rotation.getMxy(), rotation.getMyy(), rotation.getTx(), rotation.getTy());
    gc.fillText(text, x1 - gc.getFont().getSize() / 2, y1 - gc.getFont().getSize() / 2);

    gc.setTransform(1, 0, 0, 1, 0, 0);
}

@FXML
private void spinWheel() {
    int randomIndex = random.nextInt(customers.size());

    double endAngle = startAngle + randomIndex * arcAngle + arcAngle / 2;
    RotateTransition rotateTransition = new RotateTransition(Duration.seconds(3), wheelCanvas);
    rotateTransition.setFromAngle(0);
    rotateTransition.setToAngle(endAngle);
    rotateTransition.setCycleCount(1);
    rotateTransition.setAutoReverse(false);
    rotateTransition.play();
}
}