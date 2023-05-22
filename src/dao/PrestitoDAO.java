package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Catalogo;
import model.Prestito;
import utils.JpaUtil;

public class PrestitoDAO implements IPrestitoDAO{
	@Override
	public void save(Prestito p) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			System.out.println("Prestito con id: "+p.getIdPrestito() + " salvato sul DB!!");
		}catch(Exception e) {
			System.out.println("Errore su salvataggio del prestito!!"+e);
		}finally {
			em.close();
		}

	}

	@Override
	public Prestito getById(long id) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Prestito p=em.find(Prestito.class, id);
			em.getTransaction().commit();
			if(p==null) {
				System.out.println("Non c'è nessun prestito con id: "+id);
			}
			return p;
		}catch(Exception e) {
			System.out.println("Errore su ricerca del prestito!!"+e);
		}finally {
			em.close();
		}
		return null;
	}

	@Override
	public void delete(Prestito p) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(p);
			em.getTransaction().commit();
			System.out.println("Prestito con id: "+p.getIdPrestito() + " eliminato dal DB!!");
		}catch(Exception e) {
			System.out.println("Errore su eliminazione del prestito!!"+e);
		}finally {
			em.close();
		}
	}

	@Override
	public void update(Prestito p) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
		System.out.println("Prestito con id: "+p.getIdPrestito() + " modificato nel DB!!");
	}catch(Exception e) {
		System.out.println("Errore su modifica del prestito!!"+e);
	}finally {
		em.close();
	}

	}

	@Override
	public List<Prestito> getAll() {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q=em.createQuery("SELECT p FROM Prestito p");
			return q.getResultList();
		}catch(Exception e) {
			System.out.println("Errore su lettura di tutti i prestiti!!"+e);
		}finally {
			em.close();
		}
		return null;
	}
	
	@Override
	public List<Prestito> getElementiInPrestitoNonRestituiti() {
		
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q=em.createQuery("SELECT p FROM Prestito p WHERE ( Now() > p.dataRestituzionePrevista AND p.dataRestituzioneEffettiva IS NULL )");
			List<Prestito> listaPrestiti=q.getResultList();
			if(listaPrestiti.size()==0) {
				System.out.println("Non ci sono elementi in prestito non restituiti");
			}
			return listaPrestiti;
					
		}catch(Exception e) {
			System.out.println("Errore su lettura di tutti gli elementi in prestito e non restituiti!!"+e);
		}finally {
			em.close();
		}
		return null;
	}
	
	@Override
	public List<Catalogo> getElementiInPrestitoConIdUtente(long id) {
		
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q=em.createQuery("SELECT p.elementoPrestato FROM Prestito AS p WHERE  ( Now() < p.dataRestituzionePrevista AND p.dataRestituzioneEffettiva IS NULL AND p.utente.numeroTessera = :parametro_id )");
			List<Catalogo> listaPrestitiDiUnUtente=q.setParameter("parametro_id", id).getResultList();
			if(listaPrestitiDiUnUtente.size()==0) {
				System.out.println("Non ci sono elementi in prestito all'utente con id: "+id);
			}
			return listaPrestitiDiUnUtente;
		}catch(Exception e) {
			System.out.println("Errore su lettura di tutti i prestiti!!"+e);
		}finally {
			em.close();
		}
		return null;
	}
}
