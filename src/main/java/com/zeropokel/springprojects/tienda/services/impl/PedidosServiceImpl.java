package com.zeropokel.springprojects.tienda.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeropokel.springprojects.tienda.model.DetallePedido;
import com.zeropokel.springprojects.tienda.model.DetallePedidoKey;
import com.zeropokel.springprojects.tienda.model.Pedido;
import com.zeropokel.springprojects.tienda.repository.ClienteRepository;
import com.zeropokel.springprojects.tienda.repository.DetallePedidoRepository;
import com.zeropokel.springprojects.tienda.repository.PedidoRepository;
import com.zeropokel.springprojects.tienda.services.PedidosService;

@Service
public class PedidosServiceImpl implements PedidosService{

    @Autowired
    DetallePedidoRepository repositoryDetallePedidos;

    @Autowired
    PedidoRepository repository;

    @Override
    public Page<Pedido> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Pedido findByID(int codigo) {
        Optional<Pedido> findById = repository.findById(codigo);
        if(findById != null){
            Pedido pedido = findById.get();

            pedido.setDetallepedidos(repositoryDetallePedidos.findByPedidoCodigo(pedido.getCodigo()));
            return pedido;
        }
        return null;
    }

    @Override
    public void save(Pedido pedido) {
        
        repository.save(pedido);  
         
        List<DetallePedido> detallePedidos = pedido.getDetallepedidos();
        for (DetallePedido detallePedido : detallePedidos) {
            DetallePedidoKey id = new DetallePedidoKey(pedido.getCodigo(), detallePedido.getProducto().getCodigo());
            detallePedido.setId(id);
            repositoryDetallePedidos.save(detallePedido);
        }
    
    }

    @Override
    @Transactional
    public void delete(int codigo) {
        Pedido pedido = new Pedido();
        pedido.setCodigo(codigo);

        repositoryDetallePedidos.deleteByPedidoCodigo(codigo);
        repository.deleteById(codigo);       
    }

}
