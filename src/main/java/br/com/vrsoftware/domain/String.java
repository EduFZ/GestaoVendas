package br.com.vrsoftware.domain;

public class String {
    private int id;
    private java.lang.String nome;

    public String() {
    }

    public String(int id, java.lang.String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.lang.String getNome() {
        return nome;
    }

    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }
}
