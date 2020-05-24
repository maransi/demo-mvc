package com.mballem.curso.boot.service;

import java.util.List;

public interface IService<T, K> {
        void salvar(T o) throws Exception;        
        void editar(T o) throws Exception;        
        void excluir(K id) throws Exception;        
        T buscarPorId(K id) throws Exception;            
        List<T> buscarTodos();  
}
