package ec.edu.DataAcces.DTO;

import java.sql.Date;

/**
 * Clase `HighScoresDTO`.
 * 
 * Representa un registro de puntuaciones en el juego, incluyendo el nombre del jugador, la puntuación, el tiempo sobrevivido y la fecha de creación del registro.
 */
public class HighScoresDTO {
    private  Integer id ;               
    private String player_name  ;     
    private Integer score ;
    private String survived_time     ;
    private Date FechaCrea         ;

     /**
     * Constructor que inicializa los campos `player_name`, `score` y `survived_time`.
     * 
     * @param player_name Nombre del jugador.
     * @param score Puntuación del jugador.
     * @param survived_time Tiempo sobrevivido en el juego.
     */
    public HighScoresDTO(String player_name, Integer score, String survived_time) {
        this.player_name = player_name;
        this.score = score;
        this.survived_time = survived_time;
    }

    /**
     * Constructor que inicializa todos los campos de la clase.
     * 
     * @param id Identificador único del registro.
     * @param player_name Nombre del jugador.
     * @param score Puntuación del jugador.
     * @param survived_time Tiempo sobrevivido en el juego.
     * @param fechaCrea Fecha de creación del registro.
     */
    public HighScoresDTO(Integer id, String player_name, Integer score, String survived_time, Date fechaCrea) {
        this.id = id;
        this.player_name = player_name;
        this.score = score;
        this.survived_time = survived_time;
        FechaCrea = fechaCrea;
    }

    /**
     * Constructor vacío por defecto.
     */
    public HighScoresDTO() {
    }

    /**
     * Obtiene el identificador único del registro.
     * 
     * @return Identificador único del registro.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del registro.
     * 
     * @param id Identificador único del registro.
     */
    public void setId(Integer id) {
        this.id = id;
    }

     /**
     * Obtiene el nombre del jugador.
     * 
     * @return Nombre del jugador.
     */
    public String getPlayer_name() {
        return player_name;
    }

     /**
     * Establece el nombre del jugador.
     * 
     * @param player_name Nombre del jugador.
     */
    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    /**
     * Obtiene la puntuación del jugador.
     * 
     * @return Puntuación del jugador.
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Establece la puntuación del jugador.
     * 
     * @param score Puntuación del jugador.
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * Obtiene el tiempo sobrevivido en el juego.
     * 
     * @return Tiempo sobrevivido en el juego.
     */
    public String getSurvived_time() {
        return survived_time;
    }

    /**
     * Establece el tiempo sobrevivido en el juego.
     * 
     * @param survived_time Tiempo sobrevivido en el juego.
     */
    public void setSurvived_time(String survived_time) {
        this.survived_time = survived_time;
    }

    /**
     * Obtiene la fecha de creación del registro.
     * 
     * @return Fecha de creación del registro.
     */
    public Date getFechaCrea() {
        return FechaCrea;
    }

    /**
     * Establece la fecha de creación del registro.
     * 
     * @param fechaCrea Fecha de creación del registro.
     */
    public void setFechaCrea(Date fechaCrea) {
        FechaCrea = fechaCrea;
    }

    /**
     * Devuelve una representación en cadena de texto del objeto `HighScoresDTO`.
     * 
     * @return Representación en cadena de texto del objeto.
     */
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
