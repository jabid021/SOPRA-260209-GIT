package fr.formation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import fr.formation.exception.EntityNotDeletedException;
import fr.formation.model.Fournisseur;
import fr.formation.repo.FournisseurRepository;
import fr.formation.request.CreateFournisseurRequest;
import jakarta.validation.ConstraintViolationException;

@SpringBootTest(classes = FournisseurService.class)
@ContextConfiguration(classes = ValidationAutoConfiguration.class)
class FournisseurServiceTest {
    @MockitoBean
    private FournisseurRepository repository;

    @Autowired
    private FournisseurService service;

    @Test
    void shouldReturnFournisseurById() throws Exception {
        // given

        // when
        Mockito.when(this.repository.findById(1)).thenReturn(Optional.of(new Fournisseur()));

        // then
        Assertions.assertNotNull(this.service.findById(1));
        Mockito.verify(this.repository).findById(1);
    }

    @Test
    void shouldThrowExceptionOnFindById() {
        // given

        // when / then
        Assertions.assertThrows(ConstraintViolationException.class, () -> this.service.findById(0));

        // then
        Mockito.verify(this.repository, Mockito.never()).findById(0);
    }

    @Test
    void shouldThrowExceptionOnDetailedFindById() {
        // given

        // when / then
        Assertions.assertThrows(ConstraintViolationException.class, () -> this.service.findDetailedById(0));

        // then
        Mockito.verify(this.repository, Mockito.never()).findById(0);
    }

    @Test
    void shouldThrowExceptionOnSaveWhenNullOrEmptyNom() {
        // given
        CreateFournisseurRequest fournisseur = this.generateFournisseur();

        fournisseur.setNom(null);

        // when / then
        Assertions.assertThrows(ConstraintViolationException.class, () -> this.service.save(null, fournisseur));

        // given
        fournisseur.setNom("");

        // when / then
        Assertions.assertThrows(ConstraintViolationException.class, () -> this.service.save(null, fournisseur));

        // then
        Mockito.verify(this.repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void shouldSave() {
        // given
        CreateFournisseurRequest request = this.generateFournisseur();

        // when / then
        Assertions.assertDoesNotThrow(() -> this.service.save(null, request));

        // then
        Mockito.verify(this.repository).save(Mockito.any());
    }

    @Test
    void shouldReturnList() {
        // given
        List<Fournisseur> laListe = new ArrayList<>();

        laListe.add(new Fournisseur());

        Mockito.when(this.repository.findAll()).thenReturn(laListe);

        // when / then
        Assertions.assertEquals(this.service.findAll(), laListe);

        // then
        Mockito.verify(this.repository).findAll();
    }

    @Test
    void shouldReturnListIfNull() {
        // given
        Mockito.when(this.repository.findAll()).thenReturn(null);

        // when / then
        Assertions.assertNotNull(this.service.findAll());

        // then
        Mockito.verify(this.repository).findAll();
    }

    @Test
    void shouldDeleteById() {
        // given

        // when
        this.service.deleteById(1);

        // then
        Mockito.verify(this.repository).deleteById(1);
    }

    @Test
    void shouldThrowExceptionOnDeleteById() {
        // given
        Mockito.doThrow(ConstraintViolationException.class).when(this.repository).deleteById(1);

        // when / then
        Assertions.assertThrows(EntityNotDeletedException.class, () -> this.service.deleteById(1));
    }

    private CreateFournisseurRequest generateFournisseur() {
        CreateFournisseurRequest request = new CreateFournisseurRequest();

        request.setNom("un nom");

        return request;
    }
}
