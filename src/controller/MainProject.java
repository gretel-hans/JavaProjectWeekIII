package controller;

import java.time.LocalDate;
import java.util.List;

import dao.CatalogoDAO;
import dao.LibroDAO;
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
		
		UtenteDAO u=new UtenteDAO();
		Utente u1=new Utente("Hansel", "Adjei", LocalDate.of(1999, 10, 20));
		Utente u2=new Utente("Marta", "Neri", LocalDate.of(1989, 11, 12));
		u.save(u1);
		u.save(u2);
		
		LibroDAO l=new LibroDAO();
		Libro l1= new Libro("La bella e la bestia", 2020, 100, "Hansl", "Fantasy");
		Libro l2= new Libro("Hansel e gretel", 2021, 300, "William", "Fantasy");
		l.save(l1);
		l.save(l2);
		
		CatalogoDAO c= new CatalogoDAO();
		Rivista r1=new Rivista("Verissimo",2021,60,PeriodicitaRivista.SETTIMANALE);
		c.save(r1);
		
		Utente hans=u.getById(1);
		Catalogo v=c.getById(1);
		Catalogo v1=c.getById(2);
		Catalogo v3=c.getById(3);
		
		PrestitoDAO pr=new PrestitoDAO();
		Prestito pr1=new Prestito(hans, v, LocalDate.of(2023, 10, 10), LocalDate.of(2023, 11, 01));
		Prestito pr2=new Prestito(hans, v1, LocalDate.now(), LocalDate.of(2023, 06, 01));
		Prestito pr3=new Prestito(hans, v1, LocalDate.of(2022,10,10), LocalDate.of(2022, 06, 01));
		Prestito pr4=new Prestito(hans, v3, LocalDate.of(2023,04,10), null);
		pr.save(pr1);
		pr.save(pr2);
		pr.save(pr3);
		pr.save(pr4);
		//pr4.getElementoPrestato().getCodiceIsbn()
		
		List<Catalogo> l22=pr.getElementiInPrestitoConIdUtente(1);
		
		
		
		l22.forEach(e->System.out.println(e));
		
	}

}
