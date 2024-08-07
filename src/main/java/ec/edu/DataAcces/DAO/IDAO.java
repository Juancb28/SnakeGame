package ec.edu.DataAcces.DAO;

import java.util.List;

/**
 * Interfaz IDAO.
 * 
 * Define las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para una entidad genérica.
 * Esta interfaz se puede implementar para gestionar diferentes tipos de entidades en la base de datos.
 * 
 * @param <T> Tipo de entidad que la interfaz gestionará.
 */
public interface IDAO<T> {
    public boolean create(T entity) throws Exception ; 
    public List<T> readAll() throws Exception;
    public boolean update(T entity) throws Exception; 
    public boolean delete (Integer id) throws Exception ; 
    public T readby(Integer id) throws Exception ; 
}
