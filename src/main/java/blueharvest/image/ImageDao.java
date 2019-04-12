package blueharvest.image;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ImageDao {
    private JdbcTemplate jdbcTemplate;

    public ImageDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Image getImageById(long id){
       return jdbcTemplate.queryForObject("Select id, image from images where id=?",
               (resultSet, i) -> new Image(resultSet.getLong("id"),resultSet.getBytes("image")),id);
    }

    public Integer getLengthOfImages(){
        return jdbcTemplate.queryForObject("Select count(id) from images", (resultSet, i) -> resultSet.getInt("count(id)"));
    }
}
