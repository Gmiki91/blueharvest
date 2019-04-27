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
            resultSet.getInt("quantity"),
            Type.valueOf(resultSet.getString("type")),
            resultSet.getLong("image_id"));

    public ItemDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Item> getAllItems(){
        return jdbcTemplate.query("Select id, name, description, price, quantity, type, image_id from items",ITEM_ROWMAPPER);
    }
    public List<Item> getItemsOwnedBy(long id){
        return jdbcTemplate.query("Select id, name, description, price, items_owned.quantity, type, image_id from items" +
                " join items_owned on id = items_owned.item_id where items_owned.char_id =? ",ITEM_ROWMAPPER, id);
    }
    public Integer addToShop(long itemId, long charId){
        jdbcTemplate.update("Update items set quantity = quantity+1 where id = ?",itemId);
        jdbcTemplate.update("Update items_owned set quantity = quantity - 1 where char_id =? and item_id=?",charId,itemId);
       return jdbcTemplate.queryForObject("Select quantity from items_owned  where char_id =? and item_id=?",
               ((resultSet, i) -> resultSet.getInt("quantity")),charId,itemId);
    }
    public void deleteItemFromChar(long itemId, long charId){
        jdbcTemplate.update("Delete from items_owned where item_id = ? and char_id = ?",itemId,charId);
    }
    public void removeFromShop(long itemId){
        jdbcTemplate.update("Update items set quantity = quantity-1 where id = ?",itemId);

    }
    public void addQtyToBag(long itemId, long charId){
        jdbcTemplate.update("Update items_owned set quantity = quantity + 1 where char_id =? and item_id=?",charId,itemId);
    }
    public void addNewItemToBag(long itemId, long charId){
        jdbcTemplate.update("Insert into items_owned (char_id,item_id,quantity) values (?,?,?)",charId,itemId,1);
    }

    public Integer charAlreadyHasIt(long itemId, long charId){
       return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM items_owned WHERE item_id = ? and char_id=?",
                ((resultSet, i) -> resultSet.getInt("COUNT(*)")),itemId,charId);
    }
    public Item getRandomItem(int minPrice, int maxPrice){
        return jdbcTemplate.queryForObject("Select id, name, description, price, quantity, type, image_id from items" +
                " where price>? and price<? Order by RAND() Limit 1",ITEM_ROWMAPPER,minPrice,maxPrice);
    }
}
