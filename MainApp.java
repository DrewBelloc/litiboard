import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainApp {
    public static void main(String[] args) {
        // Create the main application frame but don't make it visible yet
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(false);

        // Show the login screen
        SwingUtilities.invokeLater(() -> {
            LoginScreen loginScreen = new LoginScreen(mainFrame);
            loginScreen.setVisible(true);
        });
    }
}

