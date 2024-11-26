import java.sql.*;
import java.util.*;

public class LicitacoesDAO {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/litiboard"; // Fixed typo in "postgresql"
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "mypass4246";

    // Get list of all saved licitações
    public static List<Licitacao> getSalvoLicitacoes() {
        List<Licitacao> licitacoes = new ArrayList<>();
        String query = "SELECT * FROM licitacoes WHERE salvo = true";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
				Licitacao licitacao = new Licitacao(
					rs.getString("id"),
					rs.getString("local"),
					rs.getString("orgao"),
					rs.getString("uni_compradora"),
					rs.getString("modalidade"),
					rs.getString("amparo_legal"),
					rs.getString("tipo"),
					rs.getString("modo_disputa"),
					rs.getBoolean("registro_preco"),
					rs.getString("data_divulga_pncp"),
					rs.getString("situacao"),
					rs.getString("data_proposta_inicio"),
					rs.getString("data_proposta_fim"),
					rs.getString("fonte"),
					rs.getString("objeto"),
					rs.getFloat("valor_total"),
					rs.getBoolean("salvo")
				);
				licitacoes.add(licitacao);
			}

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return licitacoes;
    }

    // Update 'salvo' status in the database
    public static void updateLicitacao(Licitacao licitacao) {
        String query = "UPDATE licitacoes SET salvo = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setBoolean(1, licitacao.isSalvo());
            stmt.setString(2, licitacao.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get list of all licitações
    public static List<Licitacao> getAllLicitacoes() {
        List<Licitacao> licitacoes = new ArrayList<>();
        String query = "SELECT * FROM licitacoes";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Licitacao licitacao = new Licitacao(
					rs.getString("id"),
					rs.getString("local"),
					rs.getString("orgao"),
					rs.getString("uni_compradora"),
					rs.getString("modalidade"),
					rs.getString("amparo_legal"),
					rs.getString("tipo"),
					rs.getString("modo_disputa"),
					rs.getBoolean("registro_preco"),
					rs.getString("data_divulga_pncp"),
					rs.getString("situacao"),
					rs.getString("data_proposta_inicio"),
					rs.getString("data_proposta_fim"),
					rs.getString("fonte"),
					rs.getString("objeto"),
					rs.getFloat("valor_total"),
					rs.getBoolean("salvo")
				);

                licitacoes.add(licitacao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return licitacoes;
    }
}

