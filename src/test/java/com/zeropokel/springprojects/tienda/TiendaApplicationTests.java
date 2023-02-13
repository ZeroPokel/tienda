package com.zeropokel.springprojects.tienda;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.zeropokel.springprojects.tienda.model.Permiso;
import com.zeropokel.springprojects.tienda.model.Usuario;
import com.zeropokel.springprojects.tienda.repository.PermisoRepository;
import com.zeropokel.springprojects.tienda.repository.UsuarioRepository;

@SpringBootTest
class TiendaApplicationTests {

	@Autowired
	UsuarioRepository userRepository;

	@Autowired
	PermisoRepository permissionRepository;

	@Autowired
	PasswordEncoder encoder;

	@Test
	void crearUsuariosTest() {

		Usuario u1 = new Usuario();
		u1.setCodigo(1);
		u1.setNombre("usuario1");
		u1.setPassword(encoder.encode("1234"));
		u1.setEmail("mafv@gmail.com");

		Usuario u2 = new Usuario();
		u2.setCodigo(2);
		u2.setNombre("usuario2");
		u2.setPassword(encoder.encode("5555"));
		u2.setEmail("mafv2@gmail.com");

		Usuario u3 = new Usuario();
		u3.setCodigo(3);
		u3.setNombre("usuario3");
		u3.setPassword(encoder.encode("666"));
		u3.setEmail("mafv3@gmail.com");

		Usuario u4 = new Usuario();
		u4.setCodigo(20);
		u4.setNombre("usuario4");
		u4.setPassword(encoder.encode("777"));
		u4.setEmail("mafv4@gmail.com");

		Permiso permisoAdmin = new Permiso();
		permisoAdmin.setCodigo(1);
		permisoAdmin.setNombre("ADMIN");

		Permiso permisoPedidos = new Permiso();
		permisoPedidos.setCodigo(2);
		permisoPedidos.setNombre("PEDIDOS");

		Permiso permisoClientes = new Permiso();
		permisoClientes.setCodigo(3);
		permisoClientes.setNombre("CLIENTES");

		Permiso permisoCesta = new Permiso();
		permisoCesta.setCodigo(4);
		permisoCesta.setNombre("CESTA");

		Permiso permisoProveedores = new Permiso();
		permisoProveedores.setCodigo(5);
		permisoProveedores.setNombre("PROVEEDORES");

		Permiso permisoVendedores = new Permiso();
		permisoVendedores.setCodigo(6);
		permisoVendedores.setNombre("VENDEDORES");

		Permiso permisoEmpleados = new Permiso();
		permisoEmpleados.setCodigo(7);
		permisoEmpleados.setNombre("EMPLEADOS");

		Permiso permisoDepartamentos = new Permiso();
		permisoDepartamentos.setCodigo(8);
		permisoDepartamentos.setNombre("DEPARTAMENTOS");

		List<Permiso> permisosTodos = new ArrayList<Permiso>();
		permisosTodos.add(permisoAdmin);

		List<Permiso> permisosUsuario2 = new ArrayList<Permiso>();
		permisosUsuario2.add(permisoPedidos);
		permisosUsuario2.add(permisoCesta);

		List<Permiso> permisosUsuario3 = new ArrayList<Permiso>();
		permisosUsuario3.add(permisoPedidos);
		permisosUsuario3.add(permisoClientes);
		permisosUsuario3.add(permisoCesta);

		u1.setPermissions(permisosTodos);
		u2.setPermissions(permisosUsuario2);
		u3.setPermissions(permisosUsuario3);

		permissionRepository.save(permisoAdmin);
		permissionRepository.save(permisoPedidos);
		permissionRepository.save(permisoClientes);
		permissionRepository.save(permisoCesta);
		permissionRepository.save(permisoProveedores);
		permissionRepository.save(permisoVendedores);
		permissionRepository.save(permisoEmpleados);
		permissionRepository.save(permisoDepartamentos);

		userRepository.save(u1);
		userRepository.save(u3);
		userRepository.save(u4);
		Usuario saveUsuario2 = userRepository.save(u2);

		assertTrue(u2.getPassword().equalsIgnoreCase(saveUsuario2.getPassword()));

	}
}
