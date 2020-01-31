package br.com.fh.gerenciadortarefas.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fh.gerenciadortarefas.modelos.Tarefa;

public interface RepositorioTarefa extends JpaRepository<Tarefa, Integer>  {
	
	@Query("SELECT t from Tarefa t WHERE t.usuario.email=:emailUsuario")
	List<Tarefa>carregarTarefaUsuario(@Param("emailUsuario") String email);
}
