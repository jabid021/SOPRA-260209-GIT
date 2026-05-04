package fr.bibliotek.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.bibliotek.model.Auteur;
import fr.bibliotek.service.AuteurService;

@ExtendWith(MockitoExtension.class)
class AuteurResourceMockitoTest {
    private static final String AUTEUR_ID = "auteur-id-1";

    @Mock
    private AuteurService service;

    @InjectMocks
    private AuteurResource rs;

    @Test
    void shouldFindAllUseService() {
        // given

        // when
        this.rs.findAll();

        // then
        Mockito.verify(this.service).findAll();
    }

    @Test
    void shouldFindByIdUseService() {
        // given
        Mockito.when(this.service.findById(AUTEUR_ID)).thenReturn(new Auteur());

        // when
        this.rs.findById(AUTEUR_ID);

        // then
        Mockito.verify(this.service).findById(AUTEUR_ID);
    }
}