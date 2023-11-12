public class DadosCamera {
    private Integer id;
    private String modelo;
    private String marca;
    private String resolucao;

    public DadosCamera(Integer id, String modelo, String marca, String resolucao) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.resolucao = resolucao;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getResolucao() {
        return resolucao;
    }

    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }
}

}
