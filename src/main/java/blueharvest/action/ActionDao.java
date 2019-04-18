package blueharvest.action;

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

    public void startAction(long id, long skillId, int time){
        jdbcTemplate.update("Insert into actions(char_id,skill_id,end_time) values (?,?,?)",
                id,skillId,
                Date.from(LocalDateTime.now().plusHours(time).toInstant(ZoneOffset.UTC)));
    }

    public LocalDateTime getEndTimeOfOngoingAction(Character character){
        return jdbcTemplate.queryForObject("Select end_time from actions where char_id = ?",
                ((resultSet, i) -> resultSet.getTimestamp("end_time").toLocalDateTime()),character.getId());
    }
    public Long getSkillIdOfOngoingAction(long charId){
        return jdbcTemplate.queryForObject("Select skill_id from actions where char_id = ?",
                ((resultSet, i) -> resultSet.getLong("skill_id")),charId);
    }
    public void removeAction(long id){
        jdbcTemplate.update("DELETE from actions where char_id=?",id);
    }
}
