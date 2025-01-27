package com.ashan.example.spring.jpa.repository;

import com.ashan.example.spring.jpa.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonJdbcRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Person> findAll(){
        return jdbcTemplate.query("select * from person", new PersonRowMapper());
    }

    public Person findById(int id) {
        return jdbcTemplate.queryForObject("select * from person where id=?",
                new BeanPropertyRowMapper<Person>(Person.class), id);
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("delete from person where id=?", new Object[]{id});
    }

    public int insert(Person person) {
        return jdbcTemplate.update("insert into person(id, name, location, birth_date) VALUES(?, ?, ?, ?)",
                new Object[]{person.getId(),
                person.getName(), person.getLocation(),
                new Date(person.getBirthDate().getTime())});
    }

    public int update(Person person) {
        return jdbcTemplate.update("update person set name=?, location=?, birth_date=? where id=?",
                new Object[]{person.getName(), person.getLocation(),
                        new Date(person.getBirthDate().getTime()),
                        person.getId()});
    }

    class PersonRowMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setLocation(rs.getString("location"));
            person.setBirthDate(rs.getTimestamp("birth_date"));
            return person;
        }
    }
}
