package fr.formation.service;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.formation.exception.EntityNotFoundException;
import fr.formation.model.Fournisseur;

@SpringBootTest
class FournisseurServiceIntegrationTest {
    @Autowired
    private FournisseurService service;
    
    @Test
    void shouldReturnFournisseurById() throws Exception {
        // given
        Fournisseur fournisseur = this.service.findById(1);
        
        // when / then
        Assertions.assertNotNull(fournisseur);
        Assertions.assertThrows(
            LazyInitializationException.class,
            () -> fournisseur.getProduits().size()
        );
    }
    
    @Test
    void shouldReturnDetailedFournisseurById() throws Exception {
        // given
        Fournisseur fournisseur = this.service.findDetailedById(1);

        // when / then
        Assertions.assertNotNull(this.service.findById(1));
        Assertions.assertEquals(true, fournisseur.getProduits().size() > 0);
    }
    
    @Test
    void shouldThrowExceptionOnFindById() {
        // given

        // when / then
        Assertions.assertThrows(EntityNotFoundException.class, () -> this.service.findById(10));
    }
}
