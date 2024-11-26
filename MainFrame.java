import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel menuPanel;
    private JPanel topBarPanel;
    private CardLayout cardLayout;
    private boolean isMenuVisible = true;

    public MainFrame() {
        setTitle("Litiboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialize components
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Add screens
        LicitacoesScreen licitacoesScreen = new LicitacoesScreen(this); // Pass MainFrame for navigation
        contentPanel.add(licitacoesScreen, "Licitações");
        contentPanel.add(new DashboardScreen(this), "Dashboard");
        contentPanel.add(new DadosScreen(), "Dados");

        // Create other UI components
        menuPanel = createMenuPanel();
        topBarPanel = createTopBar();

        // Main panel setup
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(menuPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(topBarPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        // Default screen
        cardLayout.show(contentPanel, "Licitações");
    }

    /**
     * Navigate to the Licitações screen.
     */
    public void showLicitacoesScreen() {
        cardLayout.show(contentPanel, "Licitações");
    }

    /**
     * Navigate to the Details screen for a specific Licitação.
     *
     * @param licitacao The selected Licitação.
     */
    public void showDetailsScreen(Licitacao licitacao) {
        DetailsScreen detailsScreen = new DetailsScreen(this, licitacao);

        contentPanel.add(detailsScreen, "Details");
        cardLayout.show(contentPanel, "Details");
    }

    /**
     * Create the side menu with buttons for navigation.
     */
    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(220, 600));
        panel.setBackground(new Color(45, 52, 54));

        // Add buttons
        panel.add(createMenuButton("Licitações", "📄", "Licitações"));
        panel.add(createMenuButton("Dashboard", "📊", "Dashboard"));
        panel.add(createMenuButton("Dados", "🗃️", "Dados"));

        return panel;
    }

    /**
     * Create a button for the side menu.
     */
    private JButton createMenuButton(String label, String icon, String cardName) {
        JButton button = new JButton(icon + " " + label);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setBackground(new Color(99, 110, 114));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(e -> cardLayout.show(contentPanel, cardName));
        button.setOpaque(true);
        button.setMaximumSize(new Dimension(220, 50));
        return button;
    }

    /**
     * Create the top bar with a Hamburger menu and app name.
     */
    private JPanel createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setPreferredSize(new Dimension(900, 60));
        topBar.setBackground(new Color(41, 128, 185));

        // Hamburger Menu Button
        JButton menuButton = new JButton("\u2630"); // Unicode for Hamburger Menu
        menuButton.addActionListener(e -> toggleMenuVisibility());
        menuButton.setFont(new Font("Sans Serif", Font.BOLD, 20));
        menuButton.setBackground(new Color(41, 128, 185));
        menuButton.setForeground(Color.WHITE);
        menuButton.setFocusPainted(false);
        menuButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        menuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // App Name
        JLabel appName = new JLabel("Litiboard", SwingConstants.CENTER);
        appName.setForeground(Color.WHITE);
        appName.setFont(new Font("Sans Serif", Font.BOLD, 24));

        topBar.add(menuButton, BorderLayout.WEST);
        topBar.add(appName, BorderLayout.CENTER);

        return topBar;
    }

    /**
     * Toggle the visibility of the side menu.
     */
    private void toggleMenuVisibility() {
        isMenuVisible = !isMenuVisible;
        menuPanel.setVisible(isMenuVisible);
        revalidate();
        repaint();
    }

    /**
     * Main entry point.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}

