package dao;

import java.util.ArrayList;
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
			System.out.println("Errore su salvataggio dell'elemento!!"+e);
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
			if(l==null) {
				System.out.println("Nel catalogo non è presente l'elemento con id: "+id);
			}
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
	public void delete(long id) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q=em.createQuery("SELECT c FROM Catalogo AS c WHERE c.codiceIsbn = :eliminazione_id");
			List<Catalogo> elementoEliminato=q.setParameter("eliminazione_id", id).getResultList();
			em.getTransaction().begin();
			if(elementoEliminato.size()==0) {
				System.out.println("Nel catalogo non è presente l'elemento che si voleva eliminare con id: "+id);
			}else if(elementoEliminato.size()==1) {
				System.out.println(elementoEliminato.get(0).getTitolo()+ " con id: " +id+ " eliminato dal DB!!");
			}
			em.remove(elementoEliminato.get(0));
			em.getTransaction().commit();
		}catch(Exception e) {
				System.out.println("Errore sull'eliminazione dell'elemento!!"+e);
			
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
		System.out.println("Errore su modifica dell'elemento!!"+e);
	}finally {
		em.close();
	}

	}

	@Override
	public List<Libro> getAllLibri() {
		List<Libro> listaLibri =new ArrayList<Libro>();
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q=em.createQuery("SELECT l FROM Libro l");
			listaLibri.addAll(q.getResultList());
			if(listaLibri.size()==0) {
				System.out.println("Non ci sono libri nel catalogo");
			}
			return listaLibri;
		}catch(Exception e) {
			System.out.println("Errore su lettura di tutti i libri!!"+e);
		}finally {
			em.close();
		}
		return null;
	}
	
	@Override
	public List<Rivista> getAllRiviste() {
		List<Rivista> listaRiviste =new ArrayList<Rivista>();
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q=em.createQuery("SELECT r FROM Rivista r");
			listaRiviste.addAll(q.getResultList());
			if(listaRiviste.size()==0) {
				System.out.println("Non ci sono riviste nel catalogo");
			}
			return listaRiviste;
		}catch(Exception e) {
			System.out.println("Errore su lettura di tutte le riviste!!"+e);
		}finally {
			em.close();
		}
		return null;
	}
	
	@Override
	public List<Catalogo> getAll() {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q=em.createQuery("SELECT c FROM Catalogo c");
			return q.getResultList();
		}catch(Exception e) {
			System.out.println("Errore su lettura di tutti gli elementi del catalogo!!"+e);
		}finally {
			em.close();
		}
		return null;
	}
	
	public List<Catalogo> getByAnnoPubblicazione(int n) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q=em.createQuery("SELECT c FROM Catalogo AS c WHERE c.annoPubblicazione = :anno_parametro");
			List<Catalogo> listaPerAnnoPubblicazione=q.setParameter("anno_parametro", n).getResultList();
			if (listaPerAnnoPubblicazione.size()==0) {
				System.out.println("Nel catalogo non ci sono elementi pubblicati nel: "+n);
			}
			return listaPerAnnoPubblicazione;
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
			List<Libro> listaLibriAutore=q.setParameter("autore_parametro", s).getResultList();
			if(listaLibriAutore.size()==0) {
				System.out.println("Non ci sono elementi nel catalogo scritti da: "+s);
			}
			return listaLibriAutore;
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
			List<Catalogo> listaCatalogoTitoloContenuto=q.setParameter("titolo_parametro", "%" + s + "%").getResultList();
			if(listaCatalogoTitoloContenuto.size()==0) {
				System.out.println("Non ci sono elementi con il titolo che contengono: "+s +" nel catalogo");
			}
			return listaCatalogoTitoloContenuto;		
		}catch(Exception e) {
			System.out.println("Errore su ricerca per titolo!!"+e);
		}finally {
			em.close();
		}
		return null;
	}

}
