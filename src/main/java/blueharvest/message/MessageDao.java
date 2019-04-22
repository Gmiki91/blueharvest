package blueharvest.message;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MessageDao {
    private JdbcTemplate jdbcTemplate;

    public MessageDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addMessage(Message message){
        jdbcTemplate.update("INSERT into messages (from_id, to_id, subject, content) values (?,?,?,?)",
                message.getFrom(),message.getTo(),message.getSubject(),message.getContent());
    }

    public List<Message> getMessages(long id){
        return jdbcTemplate.query("SELECT from_id, to_id, subject, content from messages where to_id=?",
                (resultSet, i) -> new Message(
                        resultSet.getLong("from_id"),
                        resultSet.getLong("to_id"),
                        resultSet.getString("subject"),
                        resultSet.getString("content")),id);
    }
}
