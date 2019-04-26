package blueharvest.items;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ItemDao {
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Item> ITEM_ROWMAPPER = (resultSet,i) -> new Item(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getString("description"),
            resultSet.getInt("price"),
            Type.valueOf(resultSet.getString("type")),
            resultSet.getLong("image_id"));

    public ItemDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Item> getAllItems(){
        return jdbcTemplate.query("Select id, name, description, price, type, image_id from items",ITEM_ROWMAPPER);
    }
}
