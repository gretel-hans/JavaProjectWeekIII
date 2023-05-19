package dao;

import java.util.List;

import model.Libro;

public interface ILibroDAO {
	public void save(Libro l);
	public Libro getById(long id);
	public void delete(Libro l);
	public void update(Libro l);
	public List<Libro> getAll();
}
