package br.com.dogs.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PessoaModel implements IModel, Serializable{

    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private Date nascimento;
    private String sexo;
    private String email;
    private String senha;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        StringBuilder testCampos = new StringBuilder();
        testCampos.append(" Nome: "+ getNome() + "\n");
        testCampos.append(" Cpf: " + getCpf() + "\n");
        testCampos.append(" Telefone: " + getTelefone() + "\n");
        testCampos.append(" Nascimento: " + sdf.format(getNascimento()) + "\n");
        testCampos.append(" Sexo: " +   getSexo() + "\n");
        testCampos.append(" Email: " +   getEmail() );

        return testCampos.toString();
    }
}
