package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.CatalogoDAO;
import dao.PrestitoDAO;
import dao.UtenteDAO;
import model.Catalogo;
import model.Libro;
import model.Prestito;
import model.Rivista;
import model.Utente;
import utils.PeriodicitaRivista;

public class MainProject {

	public static void main(String[] args) {
		
		//CREAZIONE UTENTI 
		List<Utente> listaUtenti= new ArrayList<Utente>();
		UtenteDAO u=new UtenteDAO();
		listaUtenti.add(new Utente("Mario", "Ricci", LocalDate.of(1999, 10, 20)));
		listaUtenti.add(new Utente("Marta", "Rossi", LocalDate.of(1985, 01, 18)));
		listaUtenti.add(new Utente("Luigi", "Bianchi", LocalDate.of(2001, 03, 05)));
		listaUtenti.add(new Utente("Ambra", "Verdi", LocalDate.of(1990, 05, 30)));
		listaUtenti.add(new Utente("Giulia", "Neri", LocalDate.of(1989, 11, 12)));
		
		//listaUtenti.forEach(e->u.save(e));
		//FINE CREAZIONE UTENTI
		
		
		//CREAZIONE LIBRI E RIVISTE
		List<Catalogo> listaCatalogo= new ArrayList<Catalogo>();
		CatalogoDAO c= new CatalogoDAO();
		listaCatalogo.add(new Libro("La custode dei segreti", 2023, 320, "Sally Page", "Romanzo"));
		listaCatalogo.add(new Libro("Un'estate dopo l'altra", 2020, 200, "Carley Fortune", "Avventura"));
		listaCatalogo.add(new Libro("La trama di Elena", 2021, 192, "Francesca Sensini", "Avventura"));
		listaCatalogo.add(new Libro("La portalettere", 2023, 416, "Francesca Giannone", "Romanzo"));
		listaCatalogo.add(new Rivista("Focus", 2019, 100, PeriodicitaRivista.SETTIMANALE));
		listaCatalogo.add(new Rivista("Gente", 2023, 150, PeriodicitaRivista.MENSILE));
		listaCatalogo.add(new Rivista("Casa facile", 2023, 250, PeriodicitaRivista.SEMESTRALE));
		listaCatalogo.add(new Rivista("The creators issue", 2020, 50, PeriodicitaRivista.MENSILE));
		
		//listaCatalogo.forEach(e->c.save(e));
		//FINE CREAZIONE LIBRI E RIVISTE
		
		
		//CREAZIONE PRESTITI 
		
			//RIFERIMENTI UTENTI
			
			Utente u1=u.getById(1);
			Utente u2=u.getById(2);
			Utente u3=u.getById(3);
			Utente u4=u.getById(4);
			Utente u5=u.getById(5);
		
			//RIFERIMENTI LIBRI E RIVISTE
		
			Catalogo ca1=c.getById(1);
			Catalogo ca2=c.getById(2);
			Catalogo ca3=c.getById(3);
			Catalogo ca4=c.getById(4);
			Catalogo ca5=c.getById(5);
			Catalogo ca6=c.getById(6);
			Catalogo ca7=c.getById(7);
			Catalogo ca8=c.getById(8);
			
		List<Prestito> listaPrestiti= new ArrayList<Prestito>();
		PrestitoDAO pr=new PrestitoDAO();
		
		listaPrestiti.add(new Prestito(u3, ca2, LocalDate.now(), null));
		listaPrestiti.add(new Prestito(u1, ca8, LocalDate.of(2023,04,01), LocalDate.of(2023,04,21)));
		listaPrestiti.add(new Prestito(u5, ca4, LocalDate.of(2022,04,01), null));
		listaPrestiti.add(new Prestito(u4, ca7, LocalDate.of(2023,01,01), null));
		listaPrestiti.add(new Prestito(u1, ca1, LocalDate.of(2023,05,11), LocalDate.of(2023,05,20)));
		listaPrestiti.add(new Prestito(u2, ca1, LocalDate.of(2023,05,20), null));
		listaPrestiti.add(new Prestito(u3, ca3, LocalDate.now(), null));
		
		//listaPrestiti.forEach(e->pr.save(e));
		//FINE CREAZIONE PRESTITI 
		
		
		
		//Rimozione di un elemento del catalogo dato un codice ISBN
		//c.delete(4);
		
		//Ricerca per ISBN
		//System.out.println(c.getById(2));
		
		//Ricerca per anno pubblicazione
		List<Catalogo> listaPerAnnoPubblicazione=c.getByAnnoPubblicazione(2023);
		//listaPerAnnoPubblicazione.forEach(e->System.out.println(e));
		
		
		//Ricerca per autore
		List<Libro> listaLibriPerAutore=c.getByAutore("Francesca Giannone");
		//listaLibriPerAutore.forEach(e->System.out.println(e));
		
		
		//Ricerca per titolo o parte di esso
		List<Catalogo> listaTitoloContenuto=c.getByTitoloContenuto("Gente");
		//listaTitoloContenuto.forEach(e->System.out.println(e));
		
		
		//Ricerca degli elementi attualmente in prestito dato un numero di tessera utente
		List<Catalogo> listaPrestitiDiUnUtente=pr.getElementiInPrestitoConIdUtente(2);
		//listaPrestitiDiUnUtente.forEach(e->System.out.println(e));
		
		
		//Ricerca di tutti i prestiti scaduti e non ancora restituiti
		List<Prestito> listaElementiInPrestitoNonRestituiti=pr.getElementiInPrestitoNonRestituiti();
		//listaElementiInPrestitoNonRestituiti.forEach(e->System.out.println(e));
		
		
		//Ricerca di tutti i libri nel catalogo
		List <Libro> listaTotaleLibri=c.getAllLibri();
		//listaTotaleLibri.forEach(e->System.out.println(e));
		
		//Ricerca di tutte le riviste nel catalogo
		List <Rivista> listaTotaleRiviste=c.getAllRiviste();
		//listaTotaleRiviste.forEach(e->System.out.println(e));
		
		//Ricerca di tutti gli elementi nel catalogo
		List <Catalogo> listaTotaleCatalogo=c.getAll();
		
	    //listaTotaleCatalogo.forEach(e->System.out.println(e));
				
	
		//Rimozione di un elemento del catalogo dato un codice ISBN
		// c.delete(2);
	
		
	}

}
