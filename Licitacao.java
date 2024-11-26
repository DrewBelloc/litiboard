import java.util.Date;

public class Licitacao {
    private String id;
    private String local;
    private String orgao;
    private String uniCompradora;
    private String modalidade;
    private String amparoLegal;
    private String tipo;
    private String modoDisputa;
    private boolean registroPreco;
    private String dataDivulgaPncp;
    private String situacao;
    private String dataPropostaInicio;
    private String dataPropostaFim;
    private String fonte;
    private String objeto;
    private float valorTotal;
    private boolean salvo;

    // Constructor, Getters and Setters

    public Licitacao(String id, String local, String orgao, String uniCompradora, String modalidade, String amparoLegal,
                     String tipo, String modoDisputa, boolean registroPreco, String dataDivulgaPncp, String situacao,
                     String dataPropostaInicio, String dataPropostaFim, String fonte, String objeto, float valorTotal, boolean salvo) {
        this.id = id;
        this.local = local;
        this.orgao = orgao;
        this.uniCompradora = uniCompradora;
        this.modalidade = modalidade;
        this.amparoLegal = amparoLegal;
        this.tipo = tipo;
        this.modoDisputa = modoDisputa;
        this.registroPreco = registroPreco;
        this.dataDivulgaPncp = dataDivulgaPncp;
        this.situacao = situacao;
        this.dataPropostaInicio = dataPropostaInicio;
        this.dataPropostaFim = dataPropostaFim;
        this.fonte = fonte;
        this.objeto = objeto;
        this.valorTotal = valorTotal;
        this.salvo = salvo;
    }

    public String getId() {
        return id;
    }

    public String getLocal() {
        return local;
    }

    public String getOrgao() {
        return orgao;
    }

    public String getUniCompradora() {
        return uniCompradora;
    }

    public String getModalidade() {
        return modalidade;
    }

    public String getAmparoLegal() {
        return amparoLegal;
    }

    public String getTipo() {
        return tipo;
    }

    public String getModoDisputa() {
        return modoDisputa;
    }

    public boolean isRegistroPreco() {
        return registroPreco;
    }

    public String getDataDivulgaPncp() {
        return dataDivulgaPncp;
    }

    public String getSituacao() {
        return situacao;
    }

    public String getDataPropostaInicio() {
        return dataPropostaInicio;
    }

    public String getDataPropostaFim() {
        return dataPropostaFim;
    }

    public String getFonte() {
        return fonte;
    }

    public String getObjeto() {
        return objeto;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public boolean isSalvo() {
        return salvo;
    }

    public void setSalvo(boolean salvo) {
        this.salvo = salvo;
    }
}

