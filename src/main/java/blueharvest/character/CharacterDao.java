package blueharvest.character;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;

@Repository
public class CharacterDao {
    private JdbcTemplate jdbcTemplate;

    public CharacterDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createCharacter(Character character) {
        jdbcTemplate.update("INSERT into characters (name,password,enabled,role,image_id,last_visit,food) values(?,?,?,?,?,?,?)",
                character.getName(), character.getPassword(), 1, "ROLE_USER", character.getImageId(), Date.valueOf(LocalDate.now()), 3);
    }

    public Character getCharacterByName(String name) {
        return jdbcTemplate.queryForObject("SELECT id, name, password, image_id, last_visit, food from characters where name = ?",
                (resultSet, i) -> new Character(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("password"),
                        resultSet.getLong("image_id"),
                        LocalDate.parse(resultSet.getString("last_visit")),
                        resultSet.getInt("food")), name);
    }

    public void updateLastVisit(Character character) {
        jdbcTemplate.update("UPDATE characters set last_visit=? where id = ?",
                Date.valueOf(character.getLastVisit()),character.getId());
    }

    public void updateFood(Character character) {
        jdbcTemplate.update("UPDATE characters set food=? where id = ?",
                character.getFood(),character.getId());
    }
}
