package br.com.fh.gerenciadortarefas.modelos;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "usr_usuarios")
public class Usuario {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome_usuario", length = 125, nullable = false)
	@NotNull(message = "Nome é obrigatório")
	@Length(max = 125, min = 4, message = "Tamanho Inválido")
	private String nomeUsuario;

	@Column(name = "email", length = 100, nullable = false)
	@NotNull(message = "E-mail é obrigatório")
	@Length(max = 100, min = 12, message = "Tamanho Inválido")
	private String email;

	@Column(name = "senha", length = 100, nullable = false)
	@NotNull(message = "Senha é obrigatório")
	@Length(min = 3, message = "Tamanho Inválido")
	private String senha;

	@Column(name = "profissao", length = 150)
	private String profissao;

	@Column(name = "celular", length = 14, nullable = false)
	@NotNull(message = "Telefone é obrigatório")
	@Length(min = 14, max = 14, message = "Tamanho Inválido")
	private String celular;

	@Column(name = "idade", length = 3, nullable = false)
	@NotNull(message = "Idade obrigatória")
	@Length(max = 3, message = "Tamanho Inválido")
	private String idade;

	@Column(name = "CPF", length = 11, nullable = false)
	@NotNull(message = "Numero CPF é obrigatório")
	@Length(min = 11, message = "Tamanho Inválido")
	private String CPF;
	
	
	@OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY)
	private List<Tarefa> tarefas;

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

}
