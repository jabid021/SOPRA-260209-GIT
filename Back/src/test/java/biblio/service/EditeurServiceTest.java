package biblio.service;


import biblio.dao.IDAOEditeur;
import biblio.model.Editeur;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EditeurServiceTest {

    @Mock
    private IDAOEditeur daoEditeur;

    @InjectMocks
    private EditeurService editeurService;

    private Editeur Hachette;
    private Editeur Editis;

    @BeforeEach
    void setUp() 
    {
        this.Hachette = new Editeur(1, "Hachette", "France");
        this.Editis   = new Editeur(2, "Editis", "France");
    }

    @Test
    void findAll_retourneToutLesEditeurs() 
    {
        Mockito.when(this.daoEditeur.findAll()).thenReturn(List.of(this.Hachette, this.Editis));
        List<Editeur> result = this.editeurService.findAll();
        assertThat(result).hasSize(2);
        Mockito.verify(this.daoEditeur).findAll();
    }

    @Test
    void findAll_retourneListeVideSiAucunEditeur() 
    {
        Mockito.when(this.daoEditeur.findAll()).thenReturn(List.of());
        List<Editeur> result = this.editeurService.findAll();
        assertThat(result).isEmpty();
    }

    @Test
    void findById_retourneEditeurSiExistant() 
    {
        Mockito.when(this.daoEditeur.findById(1)).thenReturn(Optional.of(this.Hachette));
        Editeur result = this.editeurService.findById(1);
        assertThat(result.getNom()).isEqualTo("Hachette");
        assertThat(result.getPays()).isEqualTo("France");
    }

    @Test
    void findById_404SiEditeurInexistant() 
    {
        Mockito.when(this.daoEditeur.findById(404)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> this.editeurService.findById(404))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Editeur introuvable");
    }

    @Test
    void add_SaveRetourneNouvelEditeur() 
    {
        Editeur nouveau = new Editeur("Destino", "Espagne");
        Editeur sauvegarder = new Editeur(3, "Destino", "Espagne");

        Mockito.when(this.daoEditeur.existsByNom("Destino")).thenReturn(false);
        Mockito.when(this.daoEditeur.save(nouveau)).thenReturn(sauvegarder);

        Editeur result = this.editeurService.create(nouveau);

        assertThat(result.getId()).isEqualTo(3);
        assertThat(result.getNom()).isEqualTo("Destino");
        assertThat(result.getPays()).isEqualTo("Espagne");
        Mockito.verify(this.daoEditeur).save(nouveau);
    }

    @Test
    void add_409SiNomDejaExistant() 
    {
        Editeur doublon = new Editeur("Hachette", "France");
        Mockito.when(this.daoEditeur.existsByNom("Hachette")).thenReturn(true);

        assertThatThrownBy(() -> this.editeurService.create(doublon))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Déjà Existant");

        verify(this.daoEditeur, never()).save(doublon);
    }

    @Test
    void update_modifieNomEtSauvegarde() 
    {
        Editeur modifier = new Editeur("Hachette", "France");
        Mockito.when(this.daoEditeur.findById(1)).thenReturn(Optional.of(this.Hachette));
        Mockito.when(this.daoEditeur.save(this.Hachette)).thenReturn(this.Hachette);
        Editeur result = this.editeurService.update(1, modifier);

        assertThat(result.getNom()).isEqualTo("Hachette");
    }

    @Test
    void update_404SiEditeurInexistant() 
    {
        Mockito.when(this.daoEditeur.findById(404)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> this.editeurService.update(404, new Editeur()))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Editeur introuvable");
    }

    @Test
    void deleteById_deleteSiExistant() 
    {
        Mockito.when(this.daoEditeur.findById(1)).thenReturn(Optional.of(this.Hachette));
        this.editeurService.deleteById(1);
        verify(this.daoEditeur, times(1)).deleteById(1);
    }

    @Test
    void deleteById_404SiInexistant() 
    {
        Mockito.when(this.daoEditeur.findById(404)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> this.editeurService.deleteById(404))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Editeur introuvable");
        verify(this.daoEditeur, never()).deleteById(99);
    }


    
}
