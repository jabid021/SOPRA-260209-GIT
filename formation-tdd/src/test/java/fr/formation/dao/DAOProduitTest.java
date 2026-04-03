package fr.formation.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import fr.formation.model.Produit;

@DataJpaTest
public class DAOProduitTest {
    @Autowired
    private IDAOProduit daoProduit;

    @Test
    @Sql(scripts = "classpath:/create-produit.sql")
    void shouldFindByNomReturnAllValues() {
        // given

        // when
        List<Produit> result = this.daoProduit.findByNom("Parachute");

        // then
        Assertions.assertEquals(2, result.size());
    }
}
