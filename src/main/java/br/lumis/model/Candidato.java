package br.lumis.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

import br.lumis.model.enumerator.EnumCategoria;

@Entity
public class Candidato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_candidato")
	private long id;
	@NotNull
	@Column(nullable = false) 
	private String nome;
	@NotNull
	@Column(nullable = false) 
	private String conteudo;
	@NotNull
	@Column(nullable = false) 
	@DateTimeFormat (pattern="dd/MM/yyyy")
	private Date dataDePublicacao;
	@NotNull
	@Column(nullable = false) 
	private EnumCategoria categoria;
	
	public Candidato() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Date getDataDePublicacao() {
		return dataDePublicacao;
	}

	public void setDataDePublicacao(Date dataDePublicacao) {
		this.dataDePublicacao = dataDePublicacao;
	}

	public EnumCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(EnumCategoria categoria) {
		this.categoria = categoria;
	}

	
	
}
