// MARTIN

package biblio.api;

import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;

import biblio.dao.IDAOUtilisateur;
import biblio.model.Collection;
import biblio.service.CollectionService;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(CollectionRestController.class)
class CollectionRestControllerTest
{

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CollectionService collectionService;

    @MockitoBean
    private IDAOUtilisateur daoUtilisateur;

    private Collection SF;
    private Collection Jeunesse;

    @BeforeEach
    void setUp() {
        this.SF = new Collection(1, "SF");
        this.Jeunesse   = new Collection(2, "Jeunesse");
    }

    // ── GET /api/collection ───────────────────────────────────────────────────

    @Test
    @WithMockUser
    void findAll_retourne200AvecListe() throws Exception 
    {
        Mockito.when(this.collectionService.findAll()).thenReturn(List.of(this.SF, this.Jeunesse));
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/collection"));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
        result.andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("SF"));
        Mockito.verify(this.collectionService).findAll();
    }

    // ── GET /api/collection/{id} ──────────────────────────────────────────────

    @Test
    @WithMockUser
    void findById_retourne200SiExistante() throws Exception 
    {
        Mockito.when(this.collectionService.findById(1)).thenReturn(this.SF);
        ResultActions result = this.mockMvc.perform
        (
                MockMvcRequestBuilders.get("/api/collection/1")
        );
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("SF"));
    }

    @Test
    @WithMockUser
    void findById_retourne404SiInexistante() throws Exception 
    {
        Mockito.when(this.collectionService.findById(99)).thenThrow(new ResponseStatusException(NOT_FOUND, "Collection introuvable"));
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/collection/99"));
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    // ── POST /api/collection ──────────────────────────────────────────────────

    @Test
    @WithMockUser
    void add_retourne201AvecCorps() throws Exception 
    {
        Collection sauvegardee = new Collection(3, "Thriller");
        Mockito.when(this.collectionService.add(Mockito.any())).thenReturn(sauvegardee);
        ResultActions result = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/api/collection")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"Thriller\"}")
        );
        result.andExpect(MockMvcResultMatchers.status().isCreated());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Thriller"));
    }

    // ── PUT /api/collection/{id} ──────────────────────────────────────────────

    @Test
    @WithMockUser
    void update_retourne200AvecCorps() throws Exception
    {
        Collection modifiee = new Collection(1, "Science-Fiction");
        Mockito.when(this.collectionService.update(Mockito.eq(1), Mockito.any())).thenReturn(modifiee);
        ResultActions result = this.mockMvc.perform(
                MockMvcRequestBuilders.put("/api/collection/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"Science-Fiction\"}")
        );
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Science-Fiction"));
    }

    @Test
    @WithMockUser
    void update_retourne409SiNomDejaUtilise() throws Exception
    {
        Mockito.when(this.collectionService.update(Mockito.eq(1), Mockito.any()))
                .thenThrow(new ResponseStatusException(org.springframework.http.HttpStatus.CONFLICT, "existe déjà"));
        ResultActions result = this.mockMvc.perform(
                MockMvcRequestBuilders.put("/api/collection/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"Jeunesse\"}")
        );
        result.andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    @WithMockUser
    void update_retourne400SiNomVide() throws Exception
    {
        ResultActions result = this.mockMvc.perform(
                MockMvcRequestBuilders.put("/api/collection/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"\"}")
        );
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @WithMockUser
    void add_retourne400SiNomVide() throws Exception
    {
        ResultActions result = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/api/collection")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"\"}")
        );
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    // ── DELETE /api/collection/{id} ───────────────────────────────────────────

    @Test
    @WithMockUser
    void deleteById_retourne204() throws Exception 
    {
        Mockito.doNothing().when(this.collectionService).deleteById(1);
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/collection/1").with(csrf()));
        result.andExpect(MockMvcResultMatchers.status().isNoContent());
        verify(this.collectionService).deleteById(1);
    }

    // ── 401 sans utilisateur ──────────────────────────────────────────────────

    @Test
    void findAll_retourne401SansAuthentification() throws Exception 
    {
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/collection"));
        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
