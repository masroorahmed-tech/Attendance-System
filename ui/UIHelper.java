package ui;

import javax.swing.*;
import java.awt.*;

/**
 * UIHelper Class
 * Provides reusable UI component creation methods.
 * Demonstrates DRY PRINCIPLE: eliminates code duplication in UI.
 */
public class UIHelper {

    /**
     * Creates a styled gold button.
     */
    public static JButton createGoldButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(255, 215, 0));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setFocusPainted(false);
        return button;
    }

    /**
     * Creates a styled white label.
     */
    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    /**
     * Creates a background panel with logo.
     */
    public static JPanel createBackgroundPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = loadIcon("logo.png");
                if (img != null) {
                    g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
                } else {
                    g.setColor(new Color(20, 20, 40));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
    }

    /**
     * Loads an image icon safely.
     */
    public static ImageIcon loadIcon(String path) {
        try {
            java.io.File f = new java.io.File(path);
            if (f.exists()) return new ImageIcon(f.getAbsolutePath());
        } catch (Exception ignored) {}
        return null;
    }

    /**
     * Creates a transparent row panel.
     */
    public static JPanel createTransparentRow() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        panel.setBackground(new Color(0, 0, 0, 0));
        return panel;
    }

    /**
     * Creates a labeled control row.
     */
    public static JPanel createLabeledRow(String labelText, JComponent control) {
        JPanel panel = createTransparentRow();
        panel.add(createLabel(labelText));
        panel.add(control);
        return panel;
    }
}
