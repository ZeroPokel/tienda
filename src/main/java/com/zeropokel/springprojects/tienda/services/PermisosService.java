package com.zeropokel.springprojects.tienda.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeropokel.springprojects.tienda.model.Permiso;
import com.zeropokel.springprojects.tienda.repository.PermisoRepository;

@Service
public class PermisosService {

    @Autowired
    private PermisoRepository permissionRepository;

    public Permiso createPermiso(Permiso permission) {
        return permissionRepository.save(permission);
    }

    public Permiso updatePermiso(Permiso permission) {
        return permissionRepository.save(permission);
    }

    public void deletePermiso(Integer permissionId) {
        permissionRepository.deleteById(permissionId);
    }

    public Permiso getPermiso(Integer permissionId) {
        return permissionRepository.findById(permissionId).orElse(null);
    }

    public List<Permiso> getAllPermisos() {
        return permissionRepository.findAll();
    }
}
