package ec.edu.BusinessLogic;

import java.util.List;
import ec.edu.DataAcces.DAO.high_scoresDAO;
import ec.edu.DataAcces.DTO.high_scoresDTO;

public class high_scoresBL {
    private high_scoresDTO high_scores;
    private high_scoresDAO hsDao = new high_scoresDAO();

    public high_scoresBL() {

    }

    public List<high_scoresDTO> getAll() throws Exception {
        return hsDao.readAll();
    }

    public high_scoresDTO readby(int id) throws Exception {
        high_scores = hsDao.readby(id);
        return high_scores;
    }
    public Boolean create(high_scoresDTO high_scoresDTO) throws Exception {
        return hsDao.create(high_scoresDTO); 
    }
    public boolean update(high_scoresDTO high_scoresDTO) throws Exception{
        return hsDao.update(high_scoresDTO);
    }
    public Boolean delete (int id) throws Exception {
        return hsDao.delete(id);

    }

}
