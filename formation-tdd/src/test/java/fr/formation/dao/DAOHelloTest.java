package fr.formation.dao;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import fr.formation.model.Hello;

// @SpringBootTest
// @Transactional

@DataJpaTest // @Transactional implicite + @Rollback implicite
public class DAOHelloTest {
    @Autowired
    private IDAOHello daoHello;

    @Test
    @Sql(scripts = "classpath:/hello.sql")
    // @Sql(statements = "INSERT INTO hello (message) VALUES ('hello world')")
    void shouldFindByIdPresent() {
        // given

        // when
        Optional<Hello> optHello = this.daoHello.findById(1);

        // then
        Assertions.assertTrue(optHello.isPresent());
    }

    @Test
    void shouldFindByIdEmpty() {
        // given

        // when
        Optional<Hello> optHello = this.daoHello.findById(1);

        // then
        Assertions.assertTrue(optHello.isEmpty());
    }
}
