package blueharvest.action;

import blueharvest.character.Character;
import blueharvest.character.Status;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Repository
public class ActionDao {
    private JdbcTemplate jdbcTemplate;

    public ActionDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void startAction(long id, Status status){
        jdbcTemplate.update("Insert into actions(char_id,tatigkeit,end_time) values (?,?,?)",
                id,status.toString(),
                Date.from(LocalDateTime.now().plusHours(status.getHours()).toInstant(ZoneOffset.UTC)));
    }
    public void startLearning(long id, int time){
        jdbcTemplate.update("Insert into actions(char_id,tatigkeit,end_time) values(?,?,?)",
                id,"LEARNING",
                Date.from(LocalDateTime.now().plusHours(time).toInstant(ZoneOffset.UTC)));
    }

    public LocalDateTime getEndTimeOfOngoingAction(Character character){
        return jdbcTemplate.queryForObject("Select end_time from actions where char_id = ?",
                ((resultSet, i) -> resultSet.getTimestamp("end_time").toLocalDateTime()),character.getId());
    }
    public void removeAction(long id){
        jdbcTemplate.update("DELETE from actions where char_id=?",id);
    }
}
