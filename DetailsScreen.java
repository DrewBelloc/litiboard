import javax.swing.*;
import java.awt.*;

public class DetailsScreen extends JFrame {
    private Licitacao licitacao;
    private MainFrame mainFrame;

    public DetailsScreen(MainFrame mainFrame, Licitacao licitacao) {
        this.mainFrame = mainFrame;
        this.licitacao = licitacao;

        setTitle("Detalhes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header Section
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.DARK_GRAY);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel(licitacao.getObjeto());
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Sans Serif", Font.BOLD, 24));

        headerPanel.add(titleLabel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Details Section
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        detailsPanel.setBackground(Color.WHITE);

        // Wrap details in cards
        detailsPanel.add(createDetailCard("ID", licitacao.getId()));
        detailsPanel.add(createDetailCard("Local", licitacao.getLocal()));
        detailsPanel.add(createDetailCard("Órgão", licitacao.getOrgao()));
        detailsPanel.add(createDetailCard("União Compradora", licitacao.getUniCompradora()));
        detailsPanel.add(createDetailCard("Modalidade", licitacao.getModalidade()));
        detailsPanel.add(createDetailCard("Amparo Legal", licitacao.getAmparoLegal()));
        detailsPanel.add(createDetailCard("Tipo", licitacao.getTipo()));
        detailsPanel.add(createDetailCard("Modo de Disputa", licitacao.getModoDisputa()));
        detailsPanel.add(createDetailCard("Registro de Preço", licitacao.isRegistroPreco() ? "Sim" : "Não"));
        detailsPanel.add(createDetailCard("Data de Divulgação PNCP", licitacao.getDataDivulgaPncp()));
        detailsPanel.add(createDetailCard("Situação", licitacao.getSituacao()));
        detailsPanel.add(createDetailCard("Data Início Proposta", licitacao.getDataPropostaInicio()));
        detailsPanel.add(createDetailCard("Data Fim Proposta", licitacao.getDataPropostaFim()));
        detailsPanel.add(createDetailCard("Fonte", licitacao.getFonte()));
        detailsPanel.add(createDetailCard("Objeto", licitacao.getObjeto()));
        detailsPanel.add(createDetailCard("Valor Total", "R$ " + String.format("%.2f", licitacao.getValorTotal())));
        detailsPanel.add(createDetailCard("Salvo", licitacao.isSalvo() ? "Sim" : "Não"));

        add(new JScrollPane(detailsPanel), BorderLayout.CENTER);

        // Footer Section
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createModernButton("Voltar");
        backButton.addActionListener(e -> dispose());
        footerPanel.add(backButton);

        JButton saveButton = createModernButton("Salvar");
        saveButton.addActionListener(e -> {
            licitacao.setSalvo(true);
            LicitacoesDAO.updateLicitacao(licitacao);
            JOptionPane.showMessageDialog(this, "Licitacao salva com sucesso!");
            dispose();
        });
        footerPanel.add(saveButton);

        JButton removeButton = createModernButton("Remover");
        removeButton.addActionListener(e -> {
            licitacao.setSalvo(false);
            LicitacoesDAO.updateLicitacao(licitacao);
            JOptionPane.showMessageDialog(this, "Licitacao removida com sucesso!");
            dispose();
        });
        footerPanel.add(removeButton);

        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createDetailCard(String label, String value) {
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBackground(Color.LIGHT_GRAY);
        cardPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        cardPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        cardPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel labelComponent = new JLabel(label + ":");
        labelComponent.setFont(new Font("Sans Serif", Font.BOLD, 14));
        labelComponent.setForeground(Color.DARK_GRAY);

        JLabel valueComponent = new JLabel(value);
        valueComponent.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        valueComponent.setForeground(Color.BLACK);

        cardPanel.add(labelComponent, BorderLayout.WEST);
        cardPanel.add(valueComponent, BorderLayout.CENTER);

        return cardPanel;
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

