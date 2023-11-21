package org.example.Models;

import javax.persistence.*;

@Entity
@Table(name = "tb_jogador")
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_jogador", unique = true, nullable = false)
    private Integer jogadorId;
    @Column(name = "nome", unique = true, nullable = false, length = 100)
    private String nome;
    public Jogador() {
    }
    public Jogador(String nome) {
        this.nome = nome;
    }
    public Integer getJogadorId() {
        return jogadorId;
    }
    public void setJogadorId(Integer jogadorId) {
        this.jogadorId = jogadorId;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}