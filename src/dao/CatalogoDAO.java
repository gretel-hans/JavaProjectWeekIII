package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Catalogo;
import model.Libro;
import model.Rivista;
import utils.JpaUtil;

public class CatalogoDAO implements ICatalogoDAO {


	@Override
	public void save(Catalogo l) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(l);
			em.getTransaction().commit();
			if(l instanceof Libro) {
				System.out.println("Libro: "+l.getTitolo() + " salvato sul DB!!");
			}else if(l instanceof Rivista) {
				System.out.println("Rivista: "+l.getTitolo() + " salvata sul DB!!");
			}
			
		}catch(Exception e) {
			System.out.println("Errore su salvataggio del libro!!"+e);
		}finally {
			em.close();
		}

	}

	@Override
	public Catalogo getById(long id) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Catalogo l=em.find(Catalogo.class, id);
			em.getTransaction().commit();
			return l;
		}catch(Exception e) {
			System.out.println("Errore su ricerca dell'elemento!!"+e);
		}finally {
			em.close();
		}
		return null;
	}

	@Override
	public void delete(Catalogo l) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(l);
			em.getTransaction().commit();
			if(l instanceof Libro) {
				System.out.println("Libro: "+l.getTitolo() + " eliminato dal DB!!");
			}else if(l instanceof Rivista) {
				System.out.println("Rivista: "+l.getTitolo() + " eliminata dal DB!!");
			}
			
		}catch(Exception e) {
			if(l instanceof Libro) {
				System.out.println("Errore su eliminazione del libro!!"+e);
			}else if(l instanceof Rivista) {
				System.out.println("Errore su eliminazione della rivista!!"+e);
			}
		}finally {
			em.close();
		}
	}

	@Override
	public void update(Catalogo l) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.merge(l);
		em.getTransaction().commit();
		if(l instanceof Libro) {
			System.out.println("Libro: "+l.getTitolo() + " modificato nel DB!!");
		}else if(l instanceof Rivista) {
			System.out.println("Rivista: "+l.getTitolo() + " modificata nel DB!!");
		}
		
	}catch(Exception e) {
		System.out.println("Errore su modifica del libro!!"+e);
	}finally {
		em.close();
	}

	}

	@Override
	public List<Libro> getAllLibri() {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q=em.createQuery("SELECT l FROM Libro l");
			return q.getResultList();
		}catch(Exception e) {
			System.out.println("Errore su lettura di tutti i libri!!"+e);
		}finally {
			em.close();
		}
		return null;
	}
	
	@Override
	public List<Rivista> getAllRiviste() {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q=em.createQuery("SELECT r FROM Rivista r");
			return q.getResultList();
		}catch(Exception e) {
			System.out.println("Errore su lettura di tutte le riviste!!"+e);
		}finally {
			em.close();
		}
		return null;
	}
	
	public List<Catalogo> getByAnnoPubblicazione(int n) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q=em.createQuery("SELECT c FROM Catalogo AS c WHERE c.annoPubblicazione = :anno_parametro");
			System.out.println("Ecco gli elementi del catalogo pubblicati nel: "+n);
			return q.setParameter("anno_parametro", n).getResultList();
		}catch(Exception e) {
			System.out.println("Errore su ricerca per anno pubblicazione !!"+e);
		}finally {
			em.close();
		}
		return null;
	}
	
	public List<Libro> getByAutore(String s) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q=em.createQuery("SELECT c FROM Catalogo AS c WHERE c.autore = :autore_parametro");
			System.out.println("Ecco i pubblicati da: "+s);
			return q.setParameter("autore_parametro", s).getResultList();
		}catch(Exception e) {
			System.out.println("Errore su ricerca per autore!!"+e);
		}finally {
			em.close();
		}
		return null;
	}
	
	public List<Catalogo> getByTitoloContenuto(String s) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q=em.createQuery("SELECT c FROM Catalogo AS c WHERE c.titolo LIKE :titolo_parametro  ");
			System.out.println("Ecco gli elementi che nel loro titolo contengono: "+s);
			return q.setParameter("titolo_parametro", "%" + s + "%").getResultList();
		}catch(Exception e) {
			System.out.println("Errore su ricerca per titolo!!"+e);
		}finally {
			em.close();
		}
		return null;
	}

}
