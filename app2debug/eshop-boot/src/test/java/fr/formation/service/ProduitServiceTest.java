package fr.formation.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import fr.formation.exception.EntityNotDeletedException;
import fr.formation.exception.EntityNotFoundException;
import fr.formation.exception.EntityNotPersistedException;
import fr.formation.model.Produit;
import fr.formation.repo.ProduitRepository;
import fr.formation.request.CreateOrUpdateProduitRequest;

@SpringBootTest(classes = ProduitService.class)
@ContextConfiguration(classes = ValidationAutoConfiguration.class)
class ProduitServiceTest {
    @MockitoBean
    private ProduitRepository repository;

    @Autowired
    private ProduitService service;

    @Test
    void shouldReturnProduitById() throws Exception {
        // given
        Mockito.when(this.repository.findById(1)).thenReturn(Optional.of(new Produit()));

        // when / then
        Assertions.assertNotNull(this.service.findById(1));

        // then
        Mockito.verify(this.repository, Mockito.times(1)).findById(1);
    }

    @Test
    void shouldThrowExceptionOnFindById() {
        // given

        // when / then
        Assertions.assertThrows(EntityNotFoundException.class, () -> this.service.findById(1));

        // then
        Mockito.verify(this.repository, Mockito.never()).findById(0);
    }

    @Test
    void shouldSave() {
        // given
        CreateOrUpdateProduitRequest request = this.generateProduit();

        // when / then
        Assertions.assertDoesNotThrow(() -> this.service.save(null, request));

        // then
        Mockito.verify(this.repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void shouldThrowExceptionOnSave() {
        // given
        CreateOrUpdateProduitRequest request = this.generateProduit();

        Mockito.when(this.repository.save(Mockito.any())).thenThrow(ConstraintViolationException.class);

        // when / then
        Assertions.assertThrows(EntityNotPersistedException.class, () -> this.service.save(null, request));
    }

    @Test
    void shouldReturnList() {
        // given
        List<Produit> laListe = new ArrayList<>();

        laListe.add(new Produit());

        Mockito.when(this.repository.findAll()).thenReturn(laListe);

        // when / then
        Assertions.assertEquals(this.service.findAll(), laListe);

        // then
        Mockito.verify(this.repository, Mockito.times(1)).findAll();
    }

    @Test
    void shouldReturnListIfNull() {
        // given
        Mockito.when(this.repository.findAll()).thenReturn(null);

        // when / then
        Assertions.assertNotNull(this.service.findAll());

        // then
        Mockito.verify(this.repository, Mockito.times(1)).findAll();
    }

    @Test
    void shouldDeleteById() throws Exception {
        // given

        // when
        this.repository.deleteById(1);

        // then
        Mockito.verify(this.repository, Mockito.times(1)).deleteById(1);
    }

    @Test
    void shouldThrowExceptionOnDeleteById() {
        // given
        Mockito.doThrow(ConstraintViolationException.class).when(this.repository).deleteById(1);

        // when / then
        Assertions.assertThrows(EntityNotDeletedException.class, () -> this.service.deleteById(1));
    }

    private CreateOrUpdateProduitRequest generateProduit() {
        CreateOrUpdateProduitRequest request = new CreateOrUpdateProduitRequest();

        request.setNom("un nom");
        request.setPrix(new BigDecimal("1"));
        request.setFournisseurId(1);

        return request;
    }
}
