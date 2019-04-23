package blueharvest.message;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MessageDao {
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Message> MESSAGE_ROWMAPPER = (resultSet, i) -> new Message(
            resultSet.getString("from_name"),
            resultSet.getString("to_name"),
            resultSet.getString("subject"),
            resultSet.getString("content"),
            resultSet.getTimestamp("sending_time").toLocalDateTime().plusHours(2));

    public MessageDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addMessage(Message message) {
        jdbcTemplate.update("INSERT into messages (from_name, to_name, subject, content, sending_time) values (?,?,?,?,?)",
                message.getFrom(), message.getTo(), message.getSubject(), message.getContent(), LocalDateTime.now());
    }

    public List<Message> getInboxMessages(String toName) {
        return jdbcTemplate.query("SELECT from_name, to_name, subject, content, sending_time from messages where to_name=?",
                     MESSAGE_ROWMAPPER, toName);
    }

    public List<Message> getOutboxMessages(String fromName) {
        return jdbcTemplate.query("SELECT from_name, to_name, subject, content, sending_time from messages where from_name=?",
                       MESSAGE_ROWMAPPER , fromName);
    }

}
