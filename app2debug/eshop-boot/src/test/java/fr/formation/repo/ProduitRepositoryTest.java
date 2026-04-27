package fr.formation.repo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;

@DataJpaTest
class ProduitRepositoryTest {
    @Autowired
    private ProduitRepository repository;

    @Test
    void testFindAll() {
        // given

        // when
        List<Produit> produits = this.repository.findAll();

        // then
        Assertions.assertNotNull(produits);
        Assertions.assertTrue(produits.size() > 0);
        Assertions.assertEquals(1, produits.get(0).getId());
    }

    @Test
    void testFindById() {
        // given

        // when
        Optional<Produit> produit = this.repository.findById(1);

        // then
        Assertions.assertNotNull(produit);
        Assertions.assertTrue(produit.isPresent());
        Assertions.assertEquals(1, produit.get().getId());
    }

    @Test
    void testFindByNom() {
        // given

        // when
        Optional<Produit> optProduit = this.repository.findByNom("P1");

        // then
        Assertions.assertNotNull(optProduit);
        Assertions.assertTrue(optProduit.isPresent());
        Assertions.assertEquals(2, optProduit.get().getId());
    }

    @Test
    void testFindByPrixBetween() {
        // given

        // when
        List<Produit> produits = this.repository.findByPrixBetween(50, 250);

        // then
        Assertions.assertNotNull(produits);
        Assertions.assertEquals(2, produits.size());
        Assertions.assertEquals(1, produits.get(0).getId());
    }

    @Test
    void shouldAdd() {
        // given
        Produit produit = generateProduit();
        String produitNom = produit.getNom();

        Assertions.assertEquals(0, produit.getId());

        // when
        this.repository.save(produit);

        // then
        Assertions.assertNotEquals(0, produit.getId());

        produit = this.repository.findById(produit.getId()).get();
        Assertions.assertEquals(produitNom, produit.getNom());
    }

    @Test
    void shouldUpdate() {
        // given
        Produit produit = this.repository.findById(1).get();
        String produitNom = produit.getNom();

        produit.setNom("new nom");

        // when
        this.repository.save(produit);
        produit = this.repository.findById(1).get();

        // then
        Assertions.assertNotEquals(produitNom, produit.getNom());
    }

    @Test
    void shouldDelete() {
        // given

        // whn
        this.repository.deleteById(3);
        Optional<Produit> produit = this.repository.findById(3);

        // then
        Assertions.assertNotNull(produit);
        Assertions.assertTrue(produit.isEmpty());
    }

    private static Produit generateProduit() {
        Produit produit = new Produit();

        generateDataForProduit(produit);
        return produit;
    }

    private static void generateDataForProduit(Produit produit) {
        Random r = new Random();

        produit.setNom(UUID.randomUUID().toString());
        produit.setFournisseur(new Fournisseur());
        produit.getFournisseur().setId(1);
        produit.setPrix(new BigDecimal(r.nextFloat() * 1000 + 500));
    }
}
