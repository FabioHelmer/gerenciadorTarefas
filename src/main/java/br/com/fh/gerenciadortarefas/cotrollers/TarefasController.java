package br.com.fh.gerenciadortarefas.cotrollers;

import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.bcel.internal.generic.NEW;

import br.com.fh.gerenciadortarefas.modelos.Tarefa;
import br.com.fh.gerenciadortarefas.modelos.Usuario;
import br.com.fh.gerenciadortarefas.repositorios.RepositorioTarefa;
import br.com.fh.gerenciadortarefas.repositorios.RepositorioUsuario;
import br.com.fh.gerenciadortarefas.servicos.ServicoUsuario;

@Controller
@RequestMapping("/tarefas")
public class TarefasController {
	
	@Autowired
	private RepositorioTarefa repositorioTarefa;
	
	@Autowired
	private RepositorioUsuario repositorioUsuario;
	
	@Autowired
	private ServicoUsuario servicoUsuario;
	
	@GetMapping("/listar")
	public ModelAndView listar(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/tarefas/listar");
		String emailUsuario= request.getUserPrincipal().getName();
		mv.addObject("tarefas",repositorioTarefa.carregarTarefaUsuario (emailUsuario));
		
		return mv;
	}

	
	@GetMapping("/concluidas")
	public ModelAndView concluidas() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/tarefas/concluidas");
		mv.addObject("tarefas",repositorioTarefa.findAll());
		return mv;
	}
	
	
	

	@GetMapping("/inserir")
	public ModelAndView enserir() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("tarefas/inserir");
		mv.addObject("tarefa",new Tarefa());
		return mv;
	}
	@PostMapping("/inserir")
	public ModelAndView enserir(@Valid Tarefa tarefa, BindingResult result, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (tarefa.getDataLimite()==null) {
			result.rejectValue("dataLimite","tarefa.dataLimiteInvalida","Data de Expiração é obrigatoria");
		}else if (tarefa.getDataLimite().before(new Date())) {
			result.rejectValue("dataLimite","tarefa.dataLimiteInvalida","Data de expriração não pode ser menor ou igual a data atual");
			
		}
		
		
		if (result.hasErrors()) {
			mv.setViewName("tarefas/inserir");
			mv.addObject(tarefa);
		}else {
		String emailUsuario= request.getUserPrincipal().getName();
		Usuario usuarioLogado = servicoUsuario.encontrarUsuarioPorEmail(emailUsuario);
		tarefa.setUsuario(usuarioLogado);
		mv.setViewName("redirect:/tarefas/listar");
		repositorioTarefa.save(tarefa);
		}
		return mv;
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterar(@Valid Tarefa tarefa, BindingResult result, HttpServletRequest request) {
		System.out.println("entrou no mapeamento");
		ModelAndView mv = new ModelAndView();
		if (tarefa.getDataLimite() == null) {
			result.rejectValue("dataLimite", "tarefa.dataLimiteInvalida", "Preencha a Data de Expiração ");
		} else if (tarefa.getDataLimite().before(new Date())) {
			result.rejectValue("dataLimite", "tarefa.dataLimiteInvalida",
					"A data de expriração não pode ser menor que a data atual");

		}

		if (result.hasErrors()) {
			mv.setViewName("tarefas/alterar");
			mv.addObject(tarefa);
		} else {
			String emailUsuario = request.getUserPrincipal().getName();
			Usuario usuarioLogado = servicoUsuario.encontrarUsuarioPorEmail(emailUsuario);
			tarefa.setUsuario(usuarioLogado);
			mv.setViewName("redirect:/tarefas/listar");
			repositorioTarefa.save(tarefa);
		}
		return mv;
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar (@PathVariable("id") Integer id,HttpServletRequest request ) {
		ModelAndView mv = new ModelAndView();
		String emailUsuario= request.getUserPrincipal().getName();
		Usuario usuarioLogado = servicoUsuario.encontrarUsuarioPorEmail(emailUsuario);
		Tarefa tarefa = repositorioTarefa.getOne(id);
		tarefa.setId_usr(usuarioLogado.getId());
	
		Usuario usuario = new Usuario();
	
		
		mv.setViewName("tarefas/alterar");
		mv.addObject("tarefa",tarefa);//ids estão null
		return mv;
	}


	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Integer id) {
		repositorioTarefa.deleteById(id);
		return "redirect:/tarefas/listar";
	}
	
	@GetMapping("/concluir/{id}")
	public String concluir(@PathVariable("id")Integer id) {
		Tarefa tarefa = repositorioTarefa.getOne(id);
		tarefa.setConcluida(true);
		repositorioTarefa.save(tarefa);
		return "redirect:/tarefas/listar";
	}
	
	
}
