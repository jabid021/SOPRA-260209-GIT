package biblio.api;


import biblio.dao.IDAOUtilisateur;
import biblio.model.Editeur;
import biblio.service.EditeurService;

import static org.mockito.Mockito.verify;

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

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(EditeurRestController.class)
public class EditeurRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EditeurService editeurService;

    @MockitoBean
    private IDAOUtilisateur daoUtilisateur;

    private Editeur Hachette;
    private Editeur Editis;

    @BeforeEach
    void setUp() {
        this.Hachette = new Editeur(1, "Hachette", "France");
        this.Editis   = new Editeur(2, "Editis", "France");
    }

    @Test
    @WithMockUser
    void findAll_retourne200AvecListe() throws Exception 
    {
        Mockito.when(this.editeurService.findAll()).thenReturn(List.of(this.Hachette, this.Editis));
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/editeur"));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
        result.andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("Hachette"));
        result.andExpect(MockMvcResultMatchers.jsonPath("$[0].pays").value("France"));
        Mockito.verify(this.editeurService).findAll();
    }

    @Test
    @WithMockUser
    void findById_retourne200SiExistante() throws Exception 
    {
        Mockito.when(this.editeurService.findById(1)).thenReturn(this.Hachette);
        ResultActions result = this.mockMvc.perform
        (
                MockMvcRequestBuilders.get("/api/editeur/1")
        );
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Hachette"));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.pays").value("France"));
    }

    @Test
    @WithMockUser
    void findById_retourne404SiInexistante() throws Exception 
    {
        Mockito.when(this.editeurService.findById(404)).thenThrow(new ResponseStatusException(NOT_FOUND, "Editeur introuvable"));
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/editeur/404"));
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @WithMockUser
    void add_retourne201AvecCorps() throws Exception 
    {
        Editeur sauvegarder = new Editeur(3, "Destino", "Espagne");
        Mockito.when(this.editeurService.create(Mockito.any())).thenReturn(sauvegarder);
        ResultActions result = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/api/editeur")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"Destino\"}")
                        .content("{\"pays\":\"Espagne\"}")
        );
        result.andExpect(MockMvcResultMatchers.status().isCreated());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Destino"));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.pays").value("Espagne"));
    }

    @Test
    @WithMockUser
    void deleteById_retourne204() throws Exception 
    {
        Mockito.doNothing().when(this.editeurService).deleteById(1);
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/editeur/1").with(csrf()));
        result.andExpect(MockMvcResultMatchers.status().isNoContent());
        verify(this.editeurService).deleteById(1);
    }

    @Test
    void findAll_retourne401SansAuthentification() throws Exception 
    {
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/editeur"));
        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    
}
