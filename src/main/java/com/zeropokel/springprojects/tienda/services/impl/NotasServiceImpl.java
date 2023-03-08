package com.zeropokel.springprojects.tienda.services.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zeropokel.springprojects.tienda.model.Nota;
import com.zeropokel.springprojects.tienda.services.NotasService;

import springfox.documentation.spring.web.json.Json;

@Service
public class NotasServiceImpl implements NotasService{

    @Value("${url.nota.rest.service}")
    String urlNota;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Nota> findAll() {
        Nota[] nt = restTemplate.getForObject(urlNota + "notas", Nota[].class);
        List<Nota> notas = Arrays.asList(nt);

        return notas;
    }

    @Override
    public Nota findByID(int id) {
        Nota nt = restTemplate.getForObject(urlNota + "notas/" + id, Nota.class);

        return nt;
    }

    @Override
    public Nota insert(Nota nota) {
        Nota nt = restTemplate.postForObject(urlNota + "notas", nota, Nota.class);

        return nt;
    }

    @Override
    public void login(String usuario, String password) {
        JSONObject json = new JSONObject();
        json.put("nombre", usuario);
        json.put("password", password);
        restTemplate.postForObject(urlNota + "/login", json ,Json.class);
    }

    @Override
    public void update(Nota nota) {
        restTemplate.put(urlNota + "notas/" + nota.getId(), nota);
    }

    @Override
    public void delete(int id) {
        restTemplate.delete(urlNota + "notas/" + id);
    }

    @Override
    public List<Nota> findByCriteria(String titulo, Date fecha) {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(fecha);

        Map<String, String> params = new HashMap<String, String>();
        params.put("titulo", titulo);
        params.put("fecha", date);
        
        Nota[] nt = restTemplate.getForObject(urlNota + "notas/buscar?titulo={titulo}&fecha={fecha}", Nota[].class, params);
        List<Nota> notas = Arrays.asList(nt);

        return notas;
    }

    
}
