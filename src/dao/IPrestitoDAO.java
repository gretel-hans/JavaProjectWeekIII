package dao;

import java.util.List;

import model.Catalogo;
import model.Prestito;

public interface IPrestitoDAO {
	public void save(Prestito p);
	public Prestito getById(long id);
	public void delete(Prestito p);
	public void update(Prestito p);
	public List<Prestito> getAll();
	public List<Prestito> getElementiInPrestitoNonRestituiti();
	public List<Catalogo> getElementiInPrestitoConIdUtente(long id);
	
}
