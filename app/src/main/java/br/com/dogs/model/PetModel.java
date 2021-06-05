package br.com.dogs.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PetModel implements IModel, Serializable {

    private int id;
    private String nome;
    private String raca;
    private Date nascimento;
    private String sexo;
    private String descricao;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
           SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            StringBuilder testCampos = new StringBuilder();
            testCampos.append(" Nome: "+ getNome() + "\n");
            testCampos.append(" Raça: " + getRaca() + "\n");
            testCampos.append(" Nascimento: " + sdf.format(getNascimento()) + "\n");
            testCampos.append(" Sexo: " +   getSexo() + "\n");
            testCampos.append(" Descrição: " + getDescricao());


            return testCampos.toString();
        }
    }

