package fr.formation.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// @SpringBootTest
@WebMvcTest
public class HelloRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

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

}
