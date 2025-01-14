package com.stefanini.todoList.repository;

import com.stefanini.todoList.model.Task;
import com.stefanini.todoList.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Task> rowMapper = new RowMapper<Task>() {
        @Override
        public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
            Task task = new Task();
            task.setId(rs.getLong("id"));
            task.setTitle(rs.getString("title"));
            task.setDescription(rs.getString("description"));
            task.setCreationDate(rs.getDate("creationDate").toLocalDate());
            task.setStatus(Status.fromString(rs.getString("status")));
            return task;
        }
    };

    @Override
    public Task findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM task WHERE id = ?", rowMapper, id);
    }

    @Override
    public List<Task> findAll() {
        return jdbcTemplate.query("SELECT * FROM task", rowMapper);
    }

    @Override
    public void save(Task task) {
        jdbcTemplate.update("INSERT INTO task (title, description, creationDate, status) VALUES (?, ?, ?, ?)",
                task.getTitle(), task.getDescription(), task.getCreationDate(), task.getStatus().toString());
    }

    @Override
    public void update(Task task) {
        jdbcTemplate.update("UPDATE task SET title = ?, description = ?, creationDate = ?, status = ? WHERE id = ?",
                task.getTitle(), task.getDescription(), task.getCreationDate(), task.getStatus().toString(), task.getId());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM task WHERE id = ?", id);
    }
}
