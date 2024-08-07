package ec.edu.DataAcces.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ec.edu.DataAcces.DTO.HighScoresDTO;

    /**
     * Clase HighScoresDAO.
     * 
     * Esta clase extiende de SQLiteDataHelper e implementa la interfaz IDAO para la entidad HighScoresDTO.
     * Proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la tabla
     * de puntuaciones altas en la base de datos.
     * 
     * Código de Casa Antonela.
     */
    public class HighScoresDAO extends SQLiteDataHelper implements IDAO <HighScoresDTO>{
    private   Connection conn  ; 

    /**
     * Constructor de HighScoresDAO.
     * 
     * Inicializa la conexión a la base de datos utilizando el método opConnection() de la clase base.
     */
    public HighScoresDAO() {
        try {
            
            conn = opConnection() ;
        } catch (Exception e) {
        
        }
    }

    // TODO
    /**
     * Crea un nuevo registro en la tabla HighScores.
     * 
     * @param entity Instancia de HighScoresDTO que contiene los datos a insertar.
     * @return true si el registro se insertó correctamente, false en caso contrario.
     * @throws Exception Si ocurre un error durante la operación.
     */
    @Override
    public boolean create(HighScoresDTO entity) throws Exception {
        String query = "INSERT INTO HighScores (player_name, score, survived_time) VALUES (?,?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(query);
            if(entity.getPlayer_name()!= null){
                pstm.setString(1, entity.getPlayer_name());
                pstm.setInt(2, entity.getScore());
                pstm.setString(3, entity.getSurvived_time());
                pstm.executeUpdate();
                return true ;
            }
        } catch (Exception e) {
            throw e ; 
        }
        return false ; 
    }

    /**
     * Lee todos los registros de la tabla HighScores.
     * 
     * @return Lista de objetos HighScoresDTO con los datos de la tabla.
     * @throws Exception Si ocurre un error durante la operación.
     */
    @Override
    public List<HighScoresDTO> readAll() throws Exception {
        List<HighScoresDTO> lst = new ArrayList<>(0);
        String query = "SELECT           "
                        +"idHighScores                        "     
                        +",player_name               "     
                        +",score                     "     
                        +",survived_time             "     
                        +",FechaCrea                 "
                        +"FROM HighScores          "   ;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                HighScoresDTO hs = new HighScoresDTO( 
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getInt(3),
                                rs.getString(4),
                                rs.getDate(5));
                lst.add(hs);
            }
        } catch (Exception e) {
        }  
        return lst ; 
    }

    /**
     * Actualiza un registro en la tabla HighScores.
     * 
     * @param entity Instancia de HighScoresDTO que contiene los datos a actualizar.
     * @return true si el registro se actualizó correctamente, false en caso contrario.
     * @throws Exception Si ocurre un error durante la operación.
     */

    @Override
    public boolean update(HighScoresDTO entity) throws Exception {
        String query = "UPDATE HighScores SET player_name = player_name " ;
        try {
            PreparedStatement pstm = conn.prepareStatement(query);
            if(entity.getPlayer_name()!= null){
                pstm.setString(1, entity.getPlayer_name());
                pstm.executeUpdate();
                return true ; 
            }
        } catch (Exception e) {
        }
        return false ; 
    }

    /**
     * Elimina un registro en la tabla HighScores.
     * 
     * Este método no está implementado y lanzará una excepción si se invoca.
     * 
     * @param id Identificador del registro a eliminar.
     * @throws Exception Si se intenta eliminar un registro.
     */
    @Override
    public boolean delete(Integer id) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    /**
     * Lee un registro específico de la tabla HighScores por ID.
     * 
     * @param id Identificador del registro a leer.
     * @return Instancia de HighScoresDTO con los datos del registro.
     * @throws Exception Si ocurre un error durante la operación.
     */
    @Override
    public HighScoresDTO readby(Integer id) throws Exception {
        HighScoresDTO hs = new HighScoresDTO();
        String query = "SELECT           "
                    +"idHighScores                        "     
                    +",player_name               "     
                    +",score                     "     
                    +",survived_time             "     
                    +",FechaCrea                 "
                    +"FROM HighScores WHERE    "
                    +"idHighScores ="+ id.toString()   ;
         try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                                hs = new HighScoresDTO( 
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getInt(3),
                                rs.getString(4),
                                rs.getDate(5));
                
            }
        } catch (Exception e) {
        }  
        return hs ;    
    }
}
