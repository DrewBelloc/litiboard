import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailsScreen extends JFrame {
    private Licitacao licitacao;
    private JLabel salvoLabel; // Label to show the "salvo" status

    public DetailsScreen(MainFrame mainFrame, Licitacao licitacao) {
        this.licitacao = licitacao;

        setTitle("Detalhes - " + licitacao.getObjeto()); // Set the title to show "Objeto"
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600); // Adjust the size as needed
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create a panel to display Licitacao details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        detailsPanel.setBackground(Color.WHITE);

        // Display each attribute of the Licitacao
        detailsPanel.add(createLabel("ID: " + licitacao.getId()));
        detailsPanel.add(createLabel("Local: " + licitacao.getLocal()));
        detailsPanel.add(createLabel("Órgão: " + licitacao.getOrgao()));
        detailsPanel.add(createLabel("União Compradora: " + licitacao.getUniCompradora()));
        detailsPanel.add(createLabel("Modalidade: " + licitacao.getModalidade()));
        detailsPanel.add(createLabel("Amparo Legal: " + licitacao.getAmparoLegal()));
        detailsPanel.add(createLabel("Tipo: " + licitacao.getTipo()));
        detailsPanel.add(createLabel("Modo de Disputa: " + licitacao.getModoDisputa()));
        detailsPanel.add(createLabel("Registro de Preço: " + (licitacao.isRegistroPreco() ? "Sim" : "Não")));
        detailsPanel.add(createLabel("Data de Divulgação PNCP: " + licitacao.getDataDivulgaPncp()));
        detailsPanel.add(createLabel("Situação: " + licitacao.getSituacao()));
        detailsPanel.add(createLabel("Data Início Proposta: " + licitacao.getDataPropostaInicio()));
        detailsPanel.add(createLabel("Data Fim Proposta: " + licitacao.getDataPropostaFim()));
        detailsPanel.add(createLabel("Fonte: " + licitacao.getFonte()));
        detailsPanel.add(createLabel("Objeto: " + licitacao.getObjeto()));
        detailsPanel.add(createLabel("Valor Total: R$ " + String.format("%.2f", licitacao.getValorTotal())));

        // Salvo label
        salvoLabel = new JLabel("Salvo: " + (licitacao.isSalvo() ? "Sim" : "Não"));
        salvoLabel.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        detailsPanel.add(salvoLabel);

        add(detailsPanel, BorderLayout.CENTER);

        // Button Panel with "Voltar", "Salvar", and "Remover" buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Back Button
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> dispose()); // Close the details window when clicked
        buttonPanel.add(backButton);

        // Save Button
        JButton saveButton = new JButton("Salvar");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                licitacao.setSalvo(true); // Update the object
                LicitacoesDAO.updateLicitacao(licitacao); // Save to the database
                salvoLabel.setText("Salvo: Sim"); // Update UI
                JOptionPane.showMessageDialog(
                        DetailsScreen.this,
                        "Licitacao salva com sucesso!",
                        "Informação",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
        buttonPanel.add(saveButton);

        // Remove Button
        JButton removeButton = new JButton("Remover");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                licitacao.setSalvo(false); // Update the object
                LicitacoesDAO.updateLicitacao(licitacao); // Save to the database
                salvoLabel.setText("Salvo: Não"); // Update UI
                JOptionPane.showMessageDialog(
                        DetailsScreen.this,
                        "Licitacao removida com sucesso!",
                        "Informação",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
        buttonPanel.add(removeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true); // Make the window visible
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        return label;
    }
}

