import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
    private ArrayList<Shape> shapes;
    private Color currentColor;
    private String currentTool;

    public DrawPanel() {
        shapes = new ArrayList<>();
        currentColor = Color.BLACK;
        currentTool = "Brush";

        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Shape shape = null;
                switch (currentTool) {
                    case "Brush":
                        shape = new BrushShape(e.getPoint(), currentColor);
                        break;
                    case "Rectangle":
                        shape = new RectangleShape(e.getPoint(), currentColor);
                        break;
                    case "Oval":
                        shape = new OvalShape(e.getPoint(), currentColor);
                        break;
                }
                if (shape != null) {
                    shapes.add(shape);
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }

    public void clear() {
        shapes.clear();
        repaint();
    }

    public void setCurrentColor(Color color) {
        currentColor = color;
    }

    public void setCurrentTool(String tool) {
        currentTool = tool;
    }
}

abstract class Shape {
    protected Point point;
    protected Color color;

    public Shape(Point point, Color color) {
        this.point = point;
        this.color = color;
    }

    abstract void draw(Graphics g);
}

class BrushShape extends Shape {
    public BrushShape(Point point, Color color) {
        super(point, color);
    }

    @Override
    void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(point.x, point.y, 5, 5);
    }
}

class RectangleShape extends Shape {
    public RectangleShape(Point point, Color color) {
        super(point, color);
    }

    @Override
    void draw(Graphics g) {
        g.setColor(color);
        g.drawRect(point.x, point.y, 50, 30);
    }
}

class OvalShape extends Shape {
    public OvalShape(Point point, Color color) {
        super(point, color);
    }

    @Override
    void draw(Graphics g) {
        g.setColor(color);
        g.drawOval(point.x, point.y, 50, 30);
    }
}
