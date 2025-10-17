package com.campanha_incentivo.services;

import java.util.List;

public interface IService<T, I> {
    public T criar(T dto);
    public List<T> findAll();
    public void delete(Long id) ;
    public T update(T dto) ;
      

    
}
