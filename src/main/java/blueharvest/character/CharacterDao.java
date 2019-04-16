package blueharvest.character;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;

@Repository
public class CharacterDao {
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Character> CHARACTER_ROW_MAPPER = (resultSet, i) -> new Character(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getString("password"),
            resultSet.getLong("image_id"),
            LocalDate.parse(resultSet.getString("last_visit")),
            resultSet.getInt("food"),
            resultSet.getInt("money"),
            Status.valueOf(resultSet.getString("status")));

    public CharacterDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createCharacter(Character character) {
        jdbcTemplate.update("INSERT into characters (name,password,enabled,role,image_id,last_visit,food,money,status) values(?,?,?,?,?,?,?,?,?)",
                character.getName(), character.getPassword(), 1, "ROLE_USER", character.getImageId(),
                Date.valueOf(LocalDate.now()), 3,0, Status.AVAILABLE.toString());
    }

    public Character getCharacterByName(String name) {
        return jdbcTemplate.queryForObject("SELECT id, name, password, image_id, last_visit, food, money, status from characters where name = ?",
                CHARACTER_ROW_MAPPER, name);
    }

    public void updateLastVisit(long id, LocalDate lastVisit) {
        jdbcTemplate.update("UPDATE characters set last_visit=? where id = ?",
                Date.valueOf(lastVisit),id);
    }

    public void updateFood(long id, int food) {
        jdbcTemplate.update("UPDATE characters set food=? where id = ?",
                food,id);
    }
    public void updateMoney(long id, int money) {
        jdbcTemplate.update("UPDATE characters set money=? where id = ?",
                money,id);
    }

    public void updateStatus(long id, Status status){
        jdbcTemplate.update("UPDATE characters set status=? where id = ?",
                status.toString(),id);
    }
}
