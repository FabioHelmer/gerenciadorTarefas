package br.com.fh.gerenciadortarefas.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import br.com.fh.gerenciadortarefas.modelos.Usuario;



public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {
		
	Usuario findByemail(String email);
	
	@Query("SELECT u from Usuario u WHERE u.email=:emailUsuario")
	List<Usuario>carregaPerfilUsuario(@Param("emailUsuario") String email);
}
