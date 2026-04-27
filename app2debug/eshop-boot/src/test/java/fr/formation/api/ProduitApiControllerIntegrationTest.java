package fr.formation.api;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class ProduitApiControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldFindAllStatusOk() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders.get("/api/produit")
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
        result.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
        result.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
        result.andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("P0"));
        result.andExpect(MockMvcResultMatchers.jsonPath("$[0].prix").value(200));
        result.andExpect(MockMvcResultMatchers.jsonPath("$[0].fournisseurId").value(1));
        result.andExpect(MockMvcResultMatchers.jsonPath("$[0].fournisseurNom").value("F1"));
    }
}
