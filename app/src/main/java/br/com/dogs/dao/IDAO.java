package br.com.dogs.dao;

import java.util.ArrayList;

public interface IDAO<T> {

    public int inserir(T dml);

    public ArrayList<T> consultar(String filtro);

    public int alterar(T dml, String filtro);

    public void deletar(T dml);


}
