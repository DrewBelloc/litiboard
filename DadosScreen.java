import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class DadosScreen extends JPanel {
    public DadosScreen() {
        setLayout(new GridLayout(3, 1, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        // Export Data Button
        JButton exportButton = createStyledButton("Trocar conta", new Color(46, 204, 113)); // Green
        add(exportButton);

        // Import Data Button
        JButton importButton = createStyledButton("Fazer logout", new Color(52, 152, 219)); // Blue
        add(importButton);

        // Delete Data Button
        JButton deleteButton = createStyledButton("Apagar conta", new Color(231, 76, 60)); // Red
        add(deleteButton);
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Sans Serif", Font.BOLD, 16));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        return button;
    }
}

