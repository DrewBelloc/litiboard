import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public class DashboardScreen extends JPanel {
    private MainFrame mainFrame;
    private JPanel listPanel; // Panel for saved licitações
    private PieChartPanel chartPanel; // Custom panel for pie chart

    private static final Color[] COLORS = {
            Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW
    };

    public DashboardScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        // Top bar with title and refresh button
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Dashboard");
        titleLabel.setFont(new Font("Sans Serif", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topBar.add(titleLabel, BorderLayout.WEST);

        JButton refreshButton = createModernButton("Atualizar");
        refreshButton.addActionListener(e -> refreshDashboard());
        topBar.add(refreshButton, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        // Pie Chart Panel
        chartPanel = new PieChartPanel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int maxHeight = (int) (screenSize.height / 20);  // 20% of screen height
		chartPanel.setPreferredSize(new Dimension(800, maxHeight));
        add(chartPanel, BorderLayout.CENTER);

        // Licitações List Panel
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.SOUTH);

        // Initial load
        refreshDashboard();
    }

    private void refreshDashboard() {
        // Update Pie Chart
        chartPanel.repaint();

        // Update Licitações List
        listPanel.removeAll();
        List<Licitacao> savedLicitacoes = LicitacoesDAO.getSalvoLicitacoes();
        LicitacoesScreen licitacoesScreen = new LicitacoesScreen(mainFrame);
        JPanel updatedListPanel = licitacoesScreen.createListPanel(savedLicitacoes);
        listPanel.add(updatedListPanel);

        // Refresh UI
        listPanel.revalidate();
        listPanel.repaint();
    }

    // Custom JPanel to draw the pie chart
    class PieChartPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            List<Map.Entry<String, Float>> regionData = getRegionData();
            if (regionData.isEmpty()) return;

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            float totalAmount = 0;
            for (Map.Entry<String, Float> entry : regionData) {
                totalAmount += entry.getValue();
            }

            int startAngle = 0;
            for (int i = 0; i < regionData.size(); i++) {
                Map.Entry<String, Float> entry = regionData.get(i);
                float percentage = (entry.getValue() / totalAmount) * 360;

                g2d.setColor(COLORS[i % COLORS.length]);
                g2d.fillArc(50, 50, 200, 200, startAngle, (int) percentage);

                startAngle += percentage;
            }

            int x = 270;
            int y = 50;
            g2d.setColor(Color.BLACK);
            for (int i = 0; i < regionData.size(); i++) {
                Map.Entry<String, Float> entry = regionData.get(i);
                g2d.setColor(COLORS[i % COLORS.length]);
                g2d.fillRect(x, y, 20, 20);

                g2d.setColor(Color.BLACK);
                g2d.drawString(entry.getKey() + " (" + String.format("%.1f", (entry.getValue() / totalAmount) * 100) + "%)", x + 30, y + 15);

                y += 25;
            }
        }
    }

    private List<Map.Entry<String, Float>> getRegionData() {
        List<Licitacao> licitacoes = LicitacoesDAO.getAllLicitacoes();

        Map<String, Float> regionAmountMap = new HashMap<>();
        for (Licitacao licitacao : licitacoes) {
            if (licitacao.isSalvo()) {
                String local = licitacao.getLocal();
                String region = getRegionFromLocal(local);
                float value = licitacao.getValorTotal();
                regionAmountMap.put(region, regionAmountMap.getOrDefault(region, 0f) + value);
            }
        }

        List<Map.Entry<String, Float>> sortedRegions = new ArrayList<>(regionAmountMap.entrySet());
        sortedRegions.sort((entry1, entry2) -> Float.compare(entry2.getValue(), entry1.getValue()));
        return sortedRegions.size() > 8 ? sortedRegions.subList(0, 8) : sortedRegions;
    }

    private String getRegionFromLocal(String local) {
        return local; // Placeholder logic
    }

    private JButton createModernButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
            }
        });

        return button;
    }
}

