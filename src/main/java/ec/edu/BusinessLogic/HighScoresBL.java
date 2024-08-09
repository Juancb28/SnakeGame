package ec.edu.BusinessLogic;

import java.util.List;
import ec.edu.DataAcces.DAO.HighScoresDAO;
import ec.edu.DataAcces.DTO.HighScoresDTO;

public class HighScoresBL {
    private HighScoresDTO HighScores;
    private HighScoresDAO hsDao = new HighScoresDAO();

    public HighScoresBL() {

    }

    public List<HighScoresDTO> getAll() throws Exception {
        List<HighScoresDTO> listBls = hsDao.readAll();
        listBls.sort((o1, o2) -> o2.getScore() - o1.getScore());
        return listBls;
    }

    public HighScoresDTO readby(int id) throws Exception {
        HighScores = hsDao.readby(id);
        return HighScores;
    }
    public Boolean create(HighScoresDTO high_scoresDTO) throws Exception {
        return hsDao.create(high_scoresDTO); 
    }
    public boolean update(HighScoresDTO high_scoresDTO) throws Exception{
        return hsDao.update(high_scoresDTO);
    }
    public Boolean delete (int id) throws Exception {
        return hsDao.delete(id);

    }

}
