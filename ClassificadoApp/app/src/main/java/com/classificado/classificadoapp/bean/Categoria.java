package com.classificado.classificadoapp.bean;

import java.io.Serializable;

/**
 * Created by Diego on 05/10/2015.
 */
public class Categoria implements Serializable {
    private Long id;
    private String nome;
    private String descricao;
    private String imageUrl;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
