package com.zeropokel.springprojects.tienda.services;

import java.util.Date;
import java.util.List;

import com.zeropokel.springprojects.tienda.model.Nota;

public interface NotasService {
    
    public List<Nota> findAll();

    public Nota findByID(int id);

    public Nota insert(Nota nota);

    public void update(Nota nota);

    public void delete(int id);

    public List<Nota> findByCriteria(String titulo, Date fecha);
}
