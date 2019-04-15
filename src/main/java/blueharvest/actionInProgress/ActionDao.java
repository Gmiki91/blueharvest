package blueharvest.actionInProgress;

import blueharvest.character.Character;
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

    public void startAction(Character character){
        jdbcTemplate.update("Insert into actions(char_id,tatigkeit,end_time) values (?,?,?)",
                character.getId(),character.getStatus().toString(),
                Date.from(LocalDateTime.now().plusHours(character.getStatus().getHours()).toInstant(ZoneOffset.UTC)));
    }
    public LocalDateTime getOngoingAction(Character character){
        return jdbcTemplate.queryForObject("Select end_time from actions where char_id = ?",
                ((resultSet, i) -> resultSet.getTimestamp("end_time").toLocalDateTime()),character.getId());
    }
}
