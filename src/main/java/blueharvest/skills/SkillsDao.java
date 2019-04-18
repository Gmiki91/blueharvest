package blueharvest.skills;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SkillsDao {
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Skill> SKILL_ROW_MAPPER = (resultSet, i) ->
            new Skill(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("time_to_master"),
                    resultSet.getInt("time_to_execute"),
                    SkillType.valueOf(resultSet.getString("type")),
                    resultSet.getString("description"));

    public SkillsDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Skill> getSkillsToLearn(long id) {
        return jdbcTemplate.query("Select id, name,time_to_master,time_to_execute, type, description from skills where id NOT IN (select skill_id from skills_learned where char_id=?)", SKILL_ROW_MAPPER, id);
    }

    public Skill getSkillById(long id) {
        return jdbcTemplate.queryForObject("SELECT id,name,time_to_master,time_to_execute,type, description from skills where id=? ",
                SKILL_ROW_MAPPER, id);
    }

    public void addSkillLearned(long charId, long skillId) {
        jdbcTemplate.update("Insert into skills_learned(char_id,skill_id) values(?,?)",
                charId, skillId);
    }

    public List<Skill> getSkillsLearned(long charId) {
        return jdbcTemplate.query("Select skills.id,skills.time_to_master,skills.time_to_execute,skills.name," +
                        " skills.type, skills.description from skills join skills_learned" +
                        " on skills.id = skills_learned.skill_id where skills_learned.char_id=?",
                SKILL_ROW_MAPPER, charId);
    }
    public List<Skill> getActiveSkillsLearned(long charId) {
        return jdbcTemplate.query("Select skills.id,skills.time_to_master,skills.time_to_execute,skills.name," +
                        " skills.type, skills.description from skills join skills_learned" +
                        " on skills.id = skills_learned.skill_id where skills_learned.char_id=?" +
                        " and skills.type=?",
                SKILL_ROW_MAPPER, charId, "ACTIVE");
    }
}
