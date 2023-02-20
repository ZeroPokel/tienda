package com.zeropokel.springprojects.tienda.services.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zeropokel.springprojects.tienda.model.Contacto;
import com.zeropokel.springprojects.tienda.services.ContactosService;

@Service
public class ContactosServiceImpl implements ContactosService{

    @Value("${url.agenda.rest.service}")
    String urlAgenda;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Contacto> findAll() {
        
        Contacto[] ca = restTemplate.getForObject(urlAgenda + "contactos", Contacto[].class);
        List<Contacto> contactos = Arrays.asList(ca);

        return contactos;
    }

    @Override
    public Contacto findByID(int id) {
        Contacto ca = restTemplate.getForObject(urlAgenda + "contactos/" + id, Contacto.class);

        return ca;
    }

    @Override
    public Contacto insert(Contacto contacto) {
        Contacto ca = restTemplate.postForObject(urlAgenda + "contactos", contacto, Contacto.class);

        return ca;
    }

    @Override
    public void update(Contacto contacto) {
        restTemplate.put(urlAgenda + "contactos/" + contacto.getId(), contacto);
    }

    @Override
    public void delete(int id) {
        restTemplate.delete(urlAgenda + "contactos/" + id);
        
    }
    
}
