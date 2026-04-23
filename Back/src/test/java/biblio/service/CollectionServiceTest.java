package biblio.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import biblio.dao.IDAOCollection;
import biblio.model.Collection;

@ExtendWith(MockitoExtension.class)
class CollectionServiceTest 
{
    @Mock
    private IDAOCollection daoCollection;

    @InjectMocks
    private CollectionService collectionService;

    private Collection SF;
    private Collection Jeunesse;

    @BeforeEach
    void setUp() 
    {
        this.SF = new Collection(1, "SF");
        this.Jeunesse   = new Collection(2, "Jeunesse");
    }

    // ── findAll ───────────────────────────────────────────────────────────────

    @Test
    void findAll_retourneToutesLesCollections() 
    {
        Mockito.when(this.daoCollection.findAll()).thenReturn(List.of(this.SF, this.Jeunesse));
        List<Collection> result = this.collectionService.findAll();
        assertThat(result).hasSize(2);
        Mockito.verify(this.daoCollection).findAll();
    }

    @Test
    void findAll_retourneListeVideSiAucuneCollection() 
    {
        Mockito.when(this.daoCollection.findAll()).thenReturn(List.of());
        List<Collection> result = this.collectionService.findAll();
        assertThat(result).isEmpty();
    }

    // ── findById ──────────────────────────────────────────────────────────────

    @Test
    void findById_retourneLaCollectionSiExistante() 
    {
        Mockito.when(this.daoCollection.findById(1)).thenReturn(Optional.of(this.SF));
        Collection result = this.collectionService.findById(1);
        assertThat(result.getNom()).isEqualTo("SF");
    }

    @Test
    void findById_leve404SiCollectionInexistante() 
    {
        Mockito.when(this.daoCollection.findById(99)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> this.collectionService.findById(99))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Collection introuvable");
    }

    // ── add ───────────────────────────────────────────────────────────────────

    @Test
    void add_SaveEtRetourneLaNouvelleCollection() 
    {
        Collection nouvelle = new Collection("Thriller");
        Collection sauvegardee = new Collection(3, "Thriller");

        Mockito.when(this.daoCollection.existsByNom("Thriller")).thenReturn(false);
        Mockito.when(this.daoCollection.save(nouvelle)).thenReturn(sauvegardee);

        Collection result = this.collectionService.add(nouvelle);

        assertThat(result.getId()).isEqualTo(3);
        assertThat(result.getNom()).isEqualTo("Thriller");
        Mockito.verify(this.daoCollection).save(nouvelle);
    }

    @Test
    void add_leve409SiNomDejaExistant() 
    {
        Collection doublon = new Collection("SF");
        Mockito.when(this.daoCollection.existsByNom("SF")).thenReturn(true);

        assertThatThrownBy(() -> this.collectionService.add(doublon))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("existe déjà");

        verify(this.daoCollection, never()).save(doublon);
    }

    // ── update ────────────────────────────────────────────────────────────────

    @Test
    void update_modifieLeNomEtSauvegarde() 
    {
        Collection modifiee = new Collection("SF");
        Mockito.when(this.daoCollection.findById(1)).thenReturn(Optional.of(this.SF));
        Mockito.when(this.daoCollection.save(this.SF)).thenReturn(this.SF);
        Collection result = this.collectionService.update(1, modifiee);

        assertThat(result.getNom()).isEqualTo("SF");
    }

    @Test
    void update_leve404SiCollectionInexistante() 
    {
        Mockito.when(this.daoCollection.findById(99)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> this.collectionService.update(99, new Collection()))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Collection introuvable");
    }

    // ── deleteById ────────────────────────────────────────────────────────────

    @Test
    void deleteById_supprimeSiExistante() 
    {
        Mockito.when(this.daoCollection.findById(1)).thenReturn(Optional.of(this.SF));
        this.collectionService.deleteById(1);
        verify(this.daoCollection, times(1)).deleteById(1);
    }

    @Test
    void deleteById_leve404SiInexistante() 
    {
        Mockito.when(this.daoCollection.findById(99)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> this.collectionService.deleteById(99))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Collection introuvable");
        verify(this.daoCollection, never()).deleteById(99);
    }
}
