package com.example.firebaseopet;

public class Venda {
    private String titulo;
    private String desccricao;
    private double valor;

    public Venda(String titulo, String desccricao, double valor) {
        this.titulo = titulo;
        this.desccricao = desccricao;
        this.valor = valor;
    }

    public Venda() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDesccricao() {
        return desccricao;
    }

    public void setDesccricao(String desccricao) {
        this.desccricao = desccricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
