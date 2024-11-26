import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LicitacoesScreen extends JPanel {
    private MainFrame mainFrame;
    private JPanel topBar;

    public LicitacoesScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        // Top bar with title and refresh button
        topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Licitações");
        titleLabel.setFont(new Font("Sans Serif", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topBar.add(titleLabel, BorderLayout.WEST);

        JButton refreshButton = createModernButton("Atualizar");
        refreshButton.addActionListener(e -> refreshLicitacoes());
        topBar.add(refreshButton, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        // Licitações List Panel
        List<Licitacao> licitacoes = LicitacoesDAO.getAllLicitacoes();
        add(createListPanel(licitacoes), BorderLayout.CENTER);
    }

    public JPanel createListPanel(List<Licitacao> licitacoes) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        for (Licitacao licitacao : licitacoes) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setPreferredSize(new Dimension(panel.getWidth(), 50));
            itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

            JLabel title = new JLabel(licitacao.getObjeto());
            title.setFont(new Font("Sans Serif", Font.BOLD, 16));

            JLabel subtitle = new JLabel(licitacao.getLocal());
            subtitle.setFont(new Font("Sans Serif", Font.PLAIN, 12));
            subtitle.setForeground(Color.GRAY);

            JPanel textPanel = new JPanel();
            textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
            textPanel.setBackground(Color.WHITE);
            textPanel.add(title);
            textPanel.add(subtitle);

            itemPanel.add(textPanel, BorderLayout.CENTER);

            // Add star icon if "salvo" is true
            if (licitacao.isSalvo()) {
                JLabel starLabel = new JLabel("★");
                starLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
                starLabel.setForeground(Color.ORANGE);
                itemPanel.add(starLabel, BorderLayout.EAST);
            }

            // Add a MouseListener to open DetailsScreen on click
            itemPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    new DetailsScreen(mainFrame, licitacao);
                }
            });

            panel.add(itemPanel);
        }

        return panel;
    }

    private void refreshLicitacoes() {
        List<Licitacao> updatedLicitacoes = LicitacoesDAO.getAllLicitacoes();
        Component[] components = getComponents();
		for (Component component : components) {
		    if (component instanceof JScrollPane || component instanceof JPanel && component != topBar) {
		        remove(component);
		    }
		}
        add(createListPanel(updatedLicitacoes), BorderLayout.CENTER);
        revalidate();
        repaint();
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

