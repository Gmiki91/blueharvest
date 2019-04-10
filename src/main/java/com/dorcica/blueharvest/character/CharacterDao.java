package com.dorcica.blueharvest.character;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class CharacterDao {
    private JdbcTemplate jdbcTemplate;

    public CharacterDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createCharacter(Character character){
        jdbcTemplate.update("INSERT into characters (name) values(?)", character.getName());
    }
}
