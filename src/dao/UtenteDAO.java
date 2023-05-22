package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Utente;
import utils.JpaUtil;

public class UtenteDAO implements IUtenteDAO {
	
	@Override
	public void save(Utente u) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
			System.out.println("Utente: "+u.getNome() + " salvato sul DB!!");
		}catch(Exception e) {
			System.out.println("Errore su salvataggio dell'utente!!"+e);
		}finally {
			em.close();
		}

	}

	@Override
	public Utente getById(long id) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Utente u=em.find(Utente.class, id);
			em.getTransaction().commit();
			if(u==null) {
				System.out.println("Non esiste nessun utente con id: !!"+id);
			}
			return u;
		}catch(Exception e) {
			System.out.println("Errore sulla ricerca dell'utente!!"+e);
		}finally {
			em.close();
		}
		return null;
	}

	@Override
	public void delete(Utente u) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(u);
			em.getTransaction().commit();
			System.out.println("Utente: "+u.getNome() + " eliminato dal DB!!");
		}catch(Exception e) {
			System.out.println("Errore sull'eliminazione dell'utente!!"+e);
		}finally {
			em.close();
		}
	}

	@Override
	public void update(Utente u) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.merge(u);
		em.getTransaction().commit();
		System.out.println("Utente: "+u.getNome() + " modificato nel DB!!");
	}catch(Exception e) {
		System.out.println("Errore sulla modifica dell'utente!!"+e);
	}finally {
		em.close();
	}

	}

	@Override
	public List<Utente> getAll() {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q=em.createQuery("SELECT u FROM Utente u");
			return q.getResultList();
		}catch(Exception e) {
			System.out.println("Errore su lettura di tutti gli utenti!!"+e);
		}finally {
			em.close();
		}
		return null;
	}
	
	

}
