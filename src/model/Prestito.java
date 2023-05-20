package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="prestiti")
public class Prestito {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "my_seq3" )
	@SequenceGenerator(name = "my_seq3", sequenceName = "my_seq3_name", initialValue = 1, allocationSize = 1)
	@Column(name="id_prestito")
	private long idPrestito;
	
	@ManyToOne
	private Utente utente;
	
	@ManyToOne
	@JoinColumn(name = "codice_elemento_prestato")
	private Catalogo elementoPrestato;
	
	@Column(name="data_inizio_prestito")
	private LocalDate dataInizioPrestito;
	
	@Column(name="data_prevista_restituzione_prestito")
	private LocalDate dataRestituzionePrevista;
	
	@Column(name="data_restituzione_effettiva")
	private LocalDate dataRestituzioneEffettiva;

	public Prestito() {
		super();
	}

	public Prestito(Utente utente, Catalogo elementoPrestato, LocalDate dataInizioPrestito,
			  LocalDate dataRestituzioneEffettiva) {
		super();
		this.utente = utente;
		this.elementoPrestato = elementoPrestato;
		this.dataInizioPrestito = dataInizioPrestito;
		this.dataRestituzionePrevista = this.dataInizioPrestito.plusDays(30);
		this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Catalogo getElementoPrestato() {
		return elementoPrestato;
	}

	public void setElementoPrestato(Catalogo elementoPrestato) {
		this.elementoPrestato = elementoPrestato;
	}

	public LocalDate getDataInizioPrestito() {
		return dataInizioPrestito;
	}

	public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
		this.dataInizioPrestito = dataInizioPrestito;
	}

	public LocalDate getDataRestituzionePrevista() {
		return dataRestituzionePrevista;
	}

	public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) {
		this.dataRestituzionePrevista = dataRestituzionePrevista;
	}

	public LocalDate getDataRestituzioneEffettiva() {
		return dataRestituzioneEffettiva;
	}

	public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
		this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
	}

	public long getIdPrestito() {
		return idPrestito;
	}

	@Override
	public String toString() {
		return "Prestito [idPrestito=" + idPrestito + ", utente=" + utente + ", elementoPrestato=" + elementoPrestato
				+ ", dataInizioPrestito=" + dataInizioPrestito + ", dataRestituzionePrevista="
				+ dataRestituzionePrevista + ", dataRestituzioneEffettiva=" + dataRestituzioneEffettiva + "]";
	}
	
	
}
