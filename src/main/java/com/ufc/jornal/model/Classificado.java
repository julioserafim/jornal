package com.ufc.jornal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Classificado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotEmpty(message = "Titulo não pode ser nulo")
	private String tituloClassificado;
	
	@NotEmpty(message = "Texto não pode ser nulo")
	private String textoClassificado;
	
	@NotNull(message = "Preço Inicial não pode ser nulo")
	private Double precoInicial;
	
	@NotEmpty(message = "Telefone não pode ser nulo")
	private String telefone;
	
	private Double melhorOferta;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataOferta;
	
	@ManyToOne
	@JoinColumn(name = "cod_autor")
	private Usuario autorMelhorOferta;
	

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getTituloClassificado() {
		return tituloClassificado;
	}

	public void setTituloClassificado(String tituloClassificado) {
		this.tituloClassificado = tituloClassificado;
	}


	public String getTextoClassificado() {
		return textoClassificado;
	}

	public void setTextoClassificado(String textoClassificado) {
		this.textoClassificado = textoClassificado;
	}

	public Double getPrecoInicial() {
		return precoInicial;
	}

	public void setPrecoInicial(Double precoInicial) {
		this.precoInicial = precoInicial;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Double getMelhorOferta() {
		return melhorOferta;
	}

	public void setMelhorOferta(Double melhorOferta) {
		this.melhorOferta = melhorOferta;
	}

	public Date getDataOferta() {
		return dataOferta;
	}

	public void setDataOferta(Date dataOferta) {
		this.dataOferta = dataOferta;
	}

	public Usuario getAutorMelhorOferta() {
		return autorMelhorOferta;
	}

	public void setAutorMelhorOferta(Usuario autorMelhorOferta) {
		this.autorMelhorOferta = autorMelhorOferta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Classificado other = (Classificado) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	

}
