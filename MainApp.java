import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}

