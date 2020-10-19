package de.braeluca;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.TransactionalAdvice;

import javax.inject.Named;
import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luca.braeutigam
 */

@JdbcRepository(dialect = Dialect.H2)
@Repository("test")
public abstract class DemoRepository implements CrudRepository<Book,Long> {

    private final JdbcOperations jdbcOperations;

    public DemoRepository(@Named("test") JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Transactional
    @TransactionalAdvice("test")
    public List<Book> findByTitle(String name) {
        String sql = "SELECT * FROM book WHERE name = ?";
        return jdbcOperations.prepareStatement(sql, statement -> {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            return jdbcOperations.entityStream(resultSet, Book.class).collect(Collectors.toList());
        });
    }
}
