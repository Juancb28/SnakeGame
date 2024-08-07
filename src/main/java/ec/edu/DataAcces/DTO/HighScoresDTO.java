package ec.edu.DataAcces.DTO;

import java.sql.Date;

public class HighScoresDTO {
private  Integer id ;               
private String player_name  ;     
private Integer score ;
private String survived_time     ;
private Date FechaCrea         ;

public HighScoresDTO(String player_name, Integer score, String survived_time) {
    this.player_name = player_name;
    this.score = score;
    this.survived_time = survived_time;
}
public HighScoresDTO(Integer id, String player_name, Integer score, String survived_time, Date fechaCrea) {
    this.id = id;
    this.player_name = player_name;
    this.score = score;
    this.survived_time = survived_time;
    FechaCrea = fechaCrea;
}
public HighScoresDTO() {
}
public Integer getId() {
    return id;
}
public void setId(Integer id) {
    this.id = id;
}
public String getPlayer_name() {
    return player_name;
}
public void setPlayer_name(String player_name) {
    this.player_name = player_name;
}
public Integer getScore() {
    return score;
}
public void setScore(Integer score) {
    this.score = score;
}
public String getSurvived_time() {
    return survived_time;
}
public void setSurvived_time(String survived_time) {
    this.survived_time = survived_time;
}
public Date getFechaCrea() {
    return FechaCrea;
}
public void setFechaCrea(Date fechaCrea) {
    FechaCrea = fechaCrea;
}
@Override
    public String toString() {
        return getClass().getSimpleName()
                + "\n   id: " + getId()
                + "\nplayer_name: " + getPlayer_name()
                + "\n score: " + getScore()
                + "\n Survived_time: " + getSurvived_time()
                + "\n fechaCrea: " + getFechaCrea();
    }
}
