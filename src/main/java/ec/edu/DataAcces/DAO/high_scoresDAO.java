package ec.edu.DataAcces.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ec.edu.DataAcces.DTO.high_scoresDTO;


public class high_scoresDAO extends SQLiteDataHelper implements IDAO <high_scoresDTO>{
    private   Connection conn  ; 
    public high_scoresDAO() {
        try {
            
            conn = opConnection() ;
        } catch (Exception e) {
        
        }
    }

    @Override
    public boolean create(high_scoresDTO entity) throws Exception {
        String query = "INSERT INTO high_scores (player_name, score, survived_time) VALUES (?,?,?)";
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

    @Override
    public List<high_scoresDTO> readAll() throws Exception {
        List<high_scoresDTO> lst = new ArrayList<>(0);
        String query = "SELECT           "
                        +"id                        "     
                        +",player_name               "     
                        +",score                     "     
                        +",survived_time             "     
                        +",FechaCrea                 "
                        +"FROM high_scores          "   ;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                high_scoresDTO hs = new high_scoresDTO( 
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

    @Override
    public boolean update(high_scoresDTO entity) throws Exception {
        String query = "UPDATE high_scores SET player_name = player_name " ;
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
    @Override
    public boolean delete(Integer id) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public high_scoresDTO readby(Integer id) throws Exception {
        high_scoresDTO hs = new high_scoresDTO();
        String query = "SELECT           "
                    +"id                        "     
                    +",player_name               "     
                    +",score                     "     
                    +",survived_time             "     
                    +",FechaCrea                 "
                    +"FROM high_scores WHERE    "
                    +"id ="+ id.toString()   ;
         try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                                hs = new high_scoresDTO( 
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
