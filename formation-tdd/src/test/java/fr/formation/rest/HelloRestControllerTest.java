package fr.formation.rest;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.formation.dao.IDAOHello;
import fr.formation.model.Hello;

// @SpringBootTest
// @AutoConfigureMockMvc // Permet d'injecter, si on utilise SpringBootTest, un MockMvc dans le contexte

@WebMvcTest
public class HelloRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean // Demander à Mockito de créer une instance IDAOHello dans le contexte de Spring
    private IDAOHello daoHello;

    @Test
    @WithMockUser // Simuler la connexion OK d'un utilisateur
    void shouldHelloStatusOk() throws Exception {
        // given

        // when
        // Exécuter une requête HTTP GET simulée vers /api/hello
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/hello"));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string("Hello world!"));
    }

    @Test
    @WithMockUser
    void shouldHelloAllStatusOk() throws Exception {
        // given
        // Mockito.when permet de simuler des valeurs de retour
        Mockito.when(this.daoHello.findAll()).thenReturn(List.of(new Hello()));

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/hello/all"));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        // On demande à Mockito de vérifier si daoHello.findAll() a été appelée une et une seule fois
        Mockito.verify(this.daoHello).findAll();
    }

    @Test
    @WithMockUser
    void shouldHelloByIdStatusOk() throws Exception {
        // given
        int id = 5;

        Mockito
            .when(this.daoHello.findById(id))
            .thenReturn(Optional.of(new Hello(id, "Le contenu du message")))
        ;

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/hello/" + id));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        // On demande à Mockito de vérifier si daoHello.findAll() a été appelée une et une seule fois
        Mockito.verify(this.daoHello).findById(id);
    }

    @Test
    @WithMockUser
    void shouldHelloByIdStatusNotFound() throws Exception {
        // given
        int id = 5;

        Mockito
            .when(this.daoHello.findById(id))
            .thenReturn(Optional.empty())
        ;

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/hello/" + id));

        // then
        result.andExpect(MockMvcResultMatchers.status().isNotFound());

        // On demande à Mockito de vérifier si daoHello.findAll() a été appelée une et une seule fois
        Mockito.verify(this.daoHello).findById(id);
    }
}
