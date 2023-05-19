package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Libro;
import utils.JpaUtil;

public class LibroDAO implements ILibroDAO {

	 
	@Override
	public void save(Libro l) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(l);
			em.getTransaction().commit();
			System.out.println("Libro: "+l.getTitolo() + " salvato sul DB!!");
		}catch(Exception e) {
			System.out.println("Errore su salvataggio del libro!!"+e);
		}finally {
			em.close();
		}

	}

	@Override
	public Libro getById(long id) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Libro l=em.find(Libro.class, id);
			em.getTransaction().commit();
			return l;
		}catch(Exception e) {
			System.out.println("Errore su ricerca del libro!!"+e);
		}finally {
			em.close();
		}
		return null;
	}

	@Override
	public void delete(Libro l) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(l);
			em.getTransaction().commit();
			System.out.println("Libro: "+l.getTitolo() + " eliminato dal DB!!");
		}catch(Exception e) {
			System.out.println("Errore su eliminazione del libro!!"+e);
		}finally {
			em.close();
		}
	}

	@Override
	public void update(Libro l) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.merge(l);
		em.getTransaction().commit();
		System.out.println("Libro: "+l.getTitolo() + " modificato nel DB!!");
	}catch(Exception e) {
		System.out.println("Errore su modifica del libro!!"+e);
	}finally {
		em.close();
	}

	}

	@Override
	public List<Libro> getAll() {
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

}
