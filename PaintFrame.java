import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaintFrame extends JFrame {
    private DrawPanel drawPanel;

    public PaintFrame() {
        setTitle("Basic Paint Program");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.clear();
            }
        });

        String[] tools = {"Brush", "Rectangle", "Oval"};
        JComboBox<String> toolComboBox = new JComboBox<>(tools);
        toolComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.setCurrentTool((String) toolComboBox.getSelectedItem());
            }
        });

        JButton colorButton = new JButton("Choose Color");
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
                if (selectedColor != null) {
                    drawPanel.setCurrentColor(selectedColor);
                }
            }
        });

        controlPanel.add(toolComboBox);
        controlPanel.add(colorButton);
        controlPanel.add(clearButton);
        add(controlPanel, BorderLayout.NORTH);

        setVisible(true);
    }
}
