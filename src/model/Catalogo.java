package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Catalogo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "my_sequence2" )
	@SequenceGenerator(name = "my_sequence2", sequenceName = "my_sequence2_name", initialValue = 1, allocationSize = 1)
	@Column(name = "codice_isbn")
	private long codiceIsbn;
	
	@Column(nullable = false)
	private String titolo;
	
	@Column(nullable = false, name = "anno_pubblicazione")
	private int annoPubblicazione;
	
	@Column(nullable = false, name = "numero_pagine" )
	private int numeroPagine;

	public Catalogo() {
		super();
	}

	public Catalogo(String titolo, int annoPubblicazione, int numeroPagine) {
		super();
		this.titolo = titolo;
		this.annoPubblicazione = annoPubblicazione;
		this.numeroPagine = numeroPagine;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public int getAnnoPubblicazione() {
		return annoPubblicazione;
	}

	public void setAnnoPubblicazione(int annoPubblicazione) {
		this.annoPubblicazione = annoPubblicazione;
	}

	public int getNumeroPagine() {
		return numeroPagine;
	}

	public void setNumeroPagine(int numeroPagine) {
		this.numeroPagine = numeroPagine;
	}

	public long getCodiceIsbn() {
		return codiceIsbn;
	}

	@Override
	public String toString() {
		return " codiceIsbn=" + codiceIsbn + ", titolo=" + titolo + ", annoPubblicazione=" + annoPubblicazione
				+ ", numeroPagine=" + numeroPagine +" ]";
	}
	
	
}
