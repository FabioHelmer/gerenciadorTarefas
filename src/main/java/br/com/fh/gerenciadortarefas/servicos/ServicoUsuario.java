package br.com.fh.gerenciadortarefas.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fh.gerenciadortarefas.modelos.Usuario;
import br.com.fh.gerenciadortarefas.repositorios.RepositorioUsuario;

@Service
public class ServicoUsuario {

	@Autowired
	private RepositorioUsuario repositorioUsuario;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public Usuario encontrarUsuarioPorEmail(String email) {
		return repositorioUsuario.findByemail(email);
	}
	
	public void salvar(Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		repositorioUsuario.save(usuario);
	}
}
