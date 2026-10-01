package com.example.hormiga.dto;

public class ConfiguracionDto {
    private String dimension; 
    private Integer aumentoVision;
    private String posicionInicial; 
    private String direccionInicial;
    private Integer movimientosVista;

    public String getDimension() { return dimension; }
    public void setDimension(String dimension) { this.dimension = dimension; }

    public Integer getAumentoVision() { return aumentoVision; }
    public void setAumentoVision(Integer aumentoVision) { this.aumentoVision = aumentoVision; }

    public String getPosicionInicial() { return posicionInicial; }
    public void setPosicionInicial(String posicionInicial) { this.posicionInicial = posicionInicial; }

    public String getDireccionInicial() { return direccionInicial; }
    public void setDireccionInicial(String direccionInicial) { this.direccionInicial = direccionInicial; }

    public Integer getMovimientosVista() { return movimientosVista; }
    public void setMovimientosVista(Integer movimientosVista) { this.movimientosVista = movimientosVista; }
}
