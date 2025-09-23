package rayka.sos.model;

public enum StatusOrdemServico {
    ABERTA("Aberta"),
    EM_ANDAMENTO("Em andamento"),
    CONCLUIDA("Concluida"),
    FINALIZADA("Finalizada");
    
    private String descricao;
    
    StatusOrdemServico(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}
