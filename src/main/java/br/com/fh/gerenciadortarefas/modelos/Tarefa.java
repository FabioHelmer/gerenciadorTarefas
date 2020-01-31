package br.com.fh.gerenciadortarefas.modelos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "tar_tarefas")

public class Tarefa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "titulo", length = 100, nullable = false)
	@NotNull(message = "O Título é Obrigatório")
	@Length(max = 100, min = 3, message = " Título deve ter entre 3 á 100 caracteres")
	private String titulo;
	@Column(name = "descricao", length = 125, nullable = true)
	private String descricao;
	@Column(name = "data_limite", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataLimite;
	@Column(name = "concluida", nullable = false)
	private boolean concluida = false;
	
	@Column(name = "id_usr",insertable = false,updatable = false)
	private Integer id_usr;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_usr")
	private Usuario usuario;
	

	public Integer getId_usr() {
		return id_usr;
	}

	public void setId_usr(Integer id_usr) {
		this.id_usr = id_usr;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(Date dataLimite) {
		this.dataLimite = dataLimite;
	}

	public boolean isConcluida() {
		return concluida;
	}

	public void setConcluida(boolean concluida) {
		this.concluida = concluida;
	}

}
