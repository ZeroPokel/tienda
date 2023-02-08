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

		Permiso p1 = new Permiso();
		p1.setCodigo(1);
		p1.setNombre("ADMIN");

		Permiso p2 = new Permiso();
		p2.setCodigo(2);
		p2.setNombre("USER");

		Permiso p3 = new Permiso();
		p3.setCodigo(3);
		p3.setNombre("CLIENTE");

		p1 = permissionRepository.save(p1);
		p2 = permissionRepository.save(p2);
		p3 = permissionRepository.save(p3);

		List<Permiso> permisos1 = new ArrayList<Permiso>();

		permisos1.add(p1);
		permisos1.add(p2);
		permisos1.add(p3);

		List<Permiso> permisos2 = new ArrayList<Permiso>();
		permisos2.add(p2);

		List<Permiso> permisos3 = new ArrayList<Permiso>();
		permisos3.add(p2);
		permisos3.add(p3);

		u1.setPermissions(permisos1);
		u2.setPermissions(permisos2);
		u3.setPermissions(permisos3);

		userRepository.save(u1);
		userRepository.save(u3);
		Usuario saveUsuario2 = userRepository.save(u2);

		assertTrue(u2.getPassword().equalsIgnoreCase(saveUsuario2.getPassword()));

	}
}
