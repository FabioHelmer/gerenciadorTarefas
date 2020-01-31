package br.com.fh.gerenciadortarefas.cotrollers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import br.com.fh.gerenciadortarefas.modelos.Usuario;
import br.com.fh.gerenciadortarefas.repositorios.RepositorioUsuario;
import br.com.fh.gerenciadortarefas.servicos.ServicoUsuario;

@Controller

public class ContaController {
	@Autowired
	private ServicoUsuario servicoUsuario;
	
	@Autowired
	private RepositorioUsuario repositorioUsuario;
	
	@GetMapping("/login")
	public String login() {
		
		return "conta/login";
	}
	
	@GetMapping("/registration")
	public ModelAndView registrar() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("conta/registrar");
		mv.addObject("usuario", new Usuario());
		 
		return mv;
	}
	
	@PostMapping("/registration")
	public ModelAndView registrar(@Valid Usuario usuario, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		
		Usuario usr = servicoUsuario.encontrarUsuarioPorEmail(usuario.getEmail());
		if (usr!=null) {
			result.rejectValue("email","", "Usuario ja cadastrado");
		}
		if (result.hasErrors()) {
			mv.setViewName("conta/registrar");
			mv.addObject("usuario", usuario);
		}else {
			servicoUsuario.salvar(usuario);
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	@GetMapping("/perfil")
	public ModelAndView perfil(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/conta/perfil");
		String emailUsuario= request.getUserPrincipal().getName();
		mv.addObject("usuarios",repositorioUsuario.carregaPerfilUsuario(emailUsuario));
		
		return mv;
	}
	
	
	
}
