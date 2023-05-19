package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import utils.PeriodicitaRivista;

@Entity
@Table(name="riviste")
public class Rivista extends Catalogo {

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PeriodicitaRivista periodicita;
	
	
	public Rivista() {
		// TODO Auto-generated constructor stub
	}

	public Rivista(String titolo, int annoPubblicazione, int numeroPagine, PeriodicitaRivista periodicita) {
		super(titolo, annoPubblicazione, numeroPagine);
		this.periodicita=periodicita;
	}

	public PeriodicitaRivista getPeriodicita() {
		return periodicita;
	}

	public void setPeriodicita(PeriodicitaRivista periodicita) {
		this.periodicita = periodicita;
	}

	@Override
	public String toString() {
		return  "Rivista [periodicita=" + periodicita + super.toString();
	}
	
	

}
