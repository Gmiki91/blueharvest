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
            resultSet.getInt("time_to_master"));

    public SkillsDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Skill> getSkills(){
        return jdbcTemplate.query("Select id, name,time_to_master from skills", SKILL_ROW_MAPPER);
    }
    public Skill getSkillById(long id){
        return jdbcTemplate.queryForObject("SELECT id,name,time_to_master where id=?",
                SKILL_ROW_MAPPER,id);
    }
    public void addSkillLearned(long charId, long skillId){
         jdbcTemplate.update("Insert into skills_learned(char_id,skill_id,enabled) values(?,?)",
                charId,skillId,0);

    }
}
