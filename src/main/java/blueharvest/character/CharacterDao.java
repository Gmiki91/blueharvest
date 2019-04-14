package blueharvest.character;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CharacterDao {
    private JdbcTemplate jdbcTemplate;

    public CharacterDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createCharacter(Character character) {
        jdbcTemplate.update("INSERT into characters (name,password,enabled,role,image_id) values(?,?,?,?,?)",
                character.getName(), character.getPassword(), 1, "ROLE_USER", character.getImageId());
    }

    public Character getCharacterByName(String name) {
        return jdbcTemplate.queryForObject("SELECT id, name, password, image_id from characters where name = ?",
                (resultSet, i) -> new Character(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("password"),
                        resultSet.getLong("image_id")), name);
    }
}
