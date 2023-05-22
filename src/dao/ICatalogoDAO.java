package dao;

import java.util.List;

import model.Catalogo;
import model.Libro;
import model.Rivista;

public interface ICatalogoDAO {
	public void save(Catalogo c);
	public Catalogo getById(long id);
	public void delete(long id);
	public void update(Catalogo c);
	public List<Libro> getAllLibri();
	public List<Rivista> getAllRiviste();
	public List<Catalogo> getByAnnoPubblicazione(int n);
	public List<Libro> getByAutore(String s);
	public List<Catalogo> getByTitoloContenuto(String s);
	public List<Catalogo> getAll();
}
