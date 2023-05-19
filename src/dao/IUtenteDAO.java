package dao;

import java.util.List;

import model.Utente;

public interface IUtenteDAO {

	public void save(Utente u);
	public Utente getById(long id);
	public void delete(Utente u);
	public void update(Utente u);
	public List<Utente> getAll();
}
