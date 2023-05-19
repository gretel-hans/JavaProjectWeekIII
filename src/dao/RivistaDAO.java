package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Rivista;
import utils.JpaUtil;

public class RivistaDAO implements IRivistaDAO{
	@Override
	public void save(Rivista r) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(r);
			em.getTransaction().commit();
			System.out.println("Rivista: "+r.getTitolo() + " salvato sul DB!!");
		}catch(Exception e) {
			System.out.println("Errore su salvataggio della rivista!!"+e);
		}finally {
			em.close();
		}

	}

	@Override
	public Rivista getById(long id) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Rivista r=em.find(Rivista.class, id);
			em.getTransaction().commit();
			return r;
		}catch(Exception e) {
			System.out.println("Errore su ricerca della rivista!!"+e);
		}finally {
			em.close();
		}
		return null;
	}

	@Override
	public void delete(Rivista r) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(r);
			em.getTransaction().commit();
			System.out.println("Rivista: "+r.getTitolo() + " eliminata dal DB!!");
		}catch(Exception e) {
			System.out.println("Errore su eliminazione della rivista!!"+e);
		}finally {
			em.close();
		}
	}

	@Override
	public void update(Rivista r) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.merge(r);
		em.getTransaction().commit();
		System.out.println("Rivista: "+r.getTitolo() + " modificata nel DB!!");
	}catch(Exception e) {
		System.out.println("Errore su modifica della rivista!!"+e);
	}finally {
		em.close();
	}

	}

	@Override
	public List<Rivista> getAll() {
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
}
