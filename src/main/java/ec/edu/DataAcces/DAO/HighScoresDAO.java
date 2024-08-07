package ec.edu.DataAcces.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ec.edu.DataAcces.DTO.HighScoresDTO;


public class HighScoresDAO extends SQLiteDataHelper implements IDAO <HighScoresDTO>{
    private   Connection conn  ; 
    public HighScoresDAO() {
        try {
            
            conn = opConnection() ;
        } catch (Exception e) {
        
        }
    }

    // TODO
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
    @Override
    public boolean delete(Integer id) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

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
