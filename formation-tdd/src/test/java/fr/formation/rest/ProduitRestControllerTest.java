package fr.formation.rest;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.config.SecurityConfig;
import fr.formation.dao.IDAOProduit;
import fr.formation.model.Produit;
import fr.formation.rest.dto.response.request.CreateOrUpdateProduitRequest;

@WebMvcTest(controllers = ProduitRestController.class)
// @EnableMethodSecurity(prePostEnabled = true)
@Import(SecurityConfig.class)
public class ProduitRestControllerTest {
    private static final int PRODUIT_ID = 1;
    private static final String PRODUIT_NAME = "Parachute";
    private static final String PRODUIT_CODE = "PF001";

    private static final String API_URL = "/api/produit";
    private static final String API_URL_BY_ID = API_URL + "/" + PRODUIT_ID;
    private static final String API_URL_BY_NAME = API_URL + "/by-name/" + PRODUIT_NAME;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IDAOProduit daoProduit;

    @Test
    void shouldFindAllStatusUnauthorized() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL));

        // then
        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void shouldFindAllStatusOkAndAttibutesOk() throws Exception {
        // given
        Produit p1 = new Produit(PRODUIT_ID, PRODUIT_NAME, PRODUIT_CODE);

        Mockito.when(this.daoProduit.findAll()).thenReturn(List.of(p1));

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].nom").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].code").doesNotExist());

        Mockito.verify(this.daoProduit).findAll();
    }

    @Test
    void shouldFindByIdStatusUnauthorized() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void shouldFindByIdStatusOkAndAttibutesOk() throws Exception {
        // given
        Produit p1 = new Produit(PRODUIT_ID, PRODUIT_NAME, PRODUIT_CODE);

        Mockito.when(this.daoProduit.findById(PRODUIT_ID)).thenReturn(Optional.of(p1));

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.nom").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.code").doesNotExist());

        Mockito.verify(this.daoProduit).findById(PRODUIT_ID);
    }

    @Test
    @WithMockUser
    void shouldFindByIdStatusNotFound() throws Exception {
        // given
        Mockito.when(this.daoProduit.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(this.daoProduit).findById(PRODUIT_ID);
    }

    @Test
    void shouldFindByNomStatusUnauthorized() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_NAME));

        // then
        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void shouldFindByNomStatusOkAndAttibutesOk() throws Exception {
        // given
        Produit p1 = new Produit(PRODUIT_ID, PRODUIT_NAME, PRODUIT_CODE);

        Mockito.when(this.daoProduit.findByNom(PRODUIT_NAME)).thenReturn(List.of(p1));

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_NAME));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].nom").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].code").doesNotExist());

        Mockito.verify(this.daoProduit).findByNom(PRODUIT_NAME);
    }

    @Test
    void shouldCreateStatusUnauthorized() throws Exception {
        // given

        // when
        ResultActions result = this.createAndPost(PRODUIT_NAME, PRODUIT_CODE);

        // then
        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void shouldCreateStatusForbidden() throws Exception {
        // given

        // when
        ResultActions result = this.createAndPost(PRODUIT_NAME, PRODUIT_CODE);

        // then
        result.andExpect(MockMvcResultMatchers.status().isForbidden());

        Mockito.verify(this.daoProduit, Mockito.never()).save(Mockito.any());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateStatusCreatedAndSave() throws Exception {
        // given

        // when
        ResultActions result = this.createAndPost(PRODUIT_NAME, PRODUIT_CODE);

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.daoProduit).save(Mockito.any());
    }

    @ParameterizedTest
    @CsvSource({
        "'',code",
        "'  ',code",
        ",code",
        "nom,",
        "nom,''",
        "nom,'     '",
    })
    @WithMockUser(roles = "ADMIN")
    void shouldCreateStatusBadRequest(String nom, String code) throws Exception {
        // given

        // when
        ResultActions result = this.createAndPost(nom, code);

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(this.daoProduit, Mockito.never()).save(Mockito.any());
    }

    @Test
    void shouldUpdateStatusUnauthorized() throws Exception {
        // given

        // when
        ResultActions result = this.createAndPut(PRODUIT_NAME, PRODUIT_CODE);

        // then
        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void shouldUpdateStatusForbidden() throws Exception {
        // given

        // when
        ResultActions result = this.createAndPut(PRODUIT_NAME, PRODUIT_CODE);

        // then
        result.andExpect(MockMvcResultMatchers.status().isForbidden());

        Mockito.verify(this.daoProduit, Mockito.never()).save(Mockito.any());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldUpdateStatusNotFound() throws Exception {
        // given

        // when
        ResultActions result = this.createAndPut(PRODUIT_NAME, PRODUIT_CODE);

        // then
        result.andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(this.daoProduit, Mockito.never()).save(Mockito.any());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldUpdateStatusOkAndSave() throws Exception {
        // given
        Produit p1 = new Produit(PRODUIT_ID, PRODUIT_NAME, PRODUIT_CODE);

        Mockito.when(this.daoProduit.findById(PRODUIT_ID)).thenReturn(Optional.of(p1));

        // when
        ResultActions result = this.createAndPut(PRODUIT_NAME, PRODUIT_CODE);

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.daoProduit).save(Mockito.any());
    }

    @ParameterizedTest
    @CsvSource({
        "'',code",
        "'  ',code",
        ",code",
        "nom,",
        "nom,''",
        "nom,'     '",
    })
    @WithMockUser(roles = "ADMIN")
    void shouldUpdateStatusBadRequest(String nom, String code) throws Exception {
        // given

        // when
        ResultActions result = this.createAndPut(nom, code);

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(this.daoProduit, Mockito.never()).save(Mockito.any());
    }

    @Test
    void shouldDeleteStatusUnauthorized() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.delete(API_URL_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void shouldDeleteStatusForbidden() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.delete(API_URL_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isForbidden());

        Mockito.verify(this.daoProduit, Mockito.never()).deleteById(Mockito.any());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeleteStatusOkAndDelete() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.delete(API_URL_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.daoProduit).deleteById(PRODUIT_ID);
    }

    private ResultActions createAndPost(String nom, String code) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CreateOrUpdateProduitRequest request = new CreateOrUpdateProduitRequest();

        request.setNom(nom);
        request.setCode(code);

        return this.mockMvc.perform(MockMvcRequestBuilders
            .post(API_URL)
            // .with(SecurityMockMvcRequestPostProcessors.csrf())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(mapper.writeValueAsString(request))
        );
    }

    private ResultActions createAndPut(String nom, String code) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CreateOrUpdateProduitRequest request = new CreateOrUpdateProduitRequest();

        request.setNom(nom);
        request.setCode(code);

        return this.mockMvc.perform(MockMvcRequestBuilders
            .put(API_URL_BY_ID)
            // .with(SecurityMockMvcRequestPostProcessors.csrf())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(mapper.writeValueAsString(request))
        );
    }
}
