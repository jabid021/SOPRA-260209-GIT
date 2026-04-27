package fr.formation.api;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.model.Produit;
import fr.formation.request.CreateOrUpdateProduitRequest;
import fr.formation.service.ProduitService;

@WebMvcTest(ProduitApiController.class)
@WithMockUser
class ProduitApiControllerTest {
    private final static int PRODUIT_ID = 1;
    private final static String API_URL = "/api/produit";
    private final static String API_URL_BY_ID = API_URL + "/" + PRODUIT_ID;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProduitService service;

    @Test
    void shouldFindAllStatusOk() throws Exception {
        //given

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders.get(API_URL)
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.service).findAll();
    }

    @Test
    void shouldFindByIdStatusOk() throws Exception {
        //given
        Mockito.when(this.service.findById(PRODUIT_ID)).thenReturn(new Produit());

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders.get(API_URL_BY_ID)
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.service).findById(PRODUIT_ID);
    }

    @Test
    void shouldCreateStatusCreated() throws Exception {
        // given
        CreateOrUpdateProduitRequest request = this.createRequest();

        Mockito .when(this.service.save(Mockito.eq(null), Mockito.any()))
                .thenReturn(new Produit());

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post(API_URL)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.json(request))
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(this.service).save(Mockito.eq(null), Mockito.any());
    }

    @ParameterizedTest
    @CsvSource({
        "'', 58, 1",
        ", 58, 1",
        "test, 0, 1",
        "test, 1, 0"
    })
    void shouldCreateStatusBadRequest(String nom, BigDecimal prix, Integer fournisseurId) throws Exception {
        // given
        CreateOrUpdateProduitRequest request = this.createRequest();

        request.setNom(nom);
        request.setPrix(prix);
        request.setFournisseurId(fournisseurId);

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post(API_URL)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.json(request))
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(this.service, Mockito.never()).save(Mockito.eq(null), Mockito.any());
    }

    @Test
    void shouldEditStatusOk() throws Exception {
        // given
        CreateOrUpdateProduitRequest request = this.createRequest();

        Mockito .when(this.service.save(Mockito.eq(PRODUIT_ID), Mockito.any()))
                .thenReturn(new Produit());

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .put(API_URL_BY_ID)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.json(request))
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.service).save(Mockito.eq(PRODUIT_ID), Mockito.any());
    }

    @ParameterizedTest
    @CsvSource({
        "'', 58, 1",
        ", 58, 1",
        "test, 0, 1",
        "test, 1, 0"
    })
    void shouldEditStatusBadRequest(String nom, BigDecimal prix, Integer fournisseurId) throws Exception {
        // given
        CreateOrUpdateProduitRequest request = this.createRequest();

        request.setNom(nom);
        request.setPrix(prix);
        request.setFournisseurId(fournisseurId);

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .put(API_URL_BY_ID)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.json(request))
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(this.service, Mockito.never()).save(Mockito.eq(PRODUIT_ID), Mockito.any());
    }

    @Test
    void shouldDeleteByIdStatusOk() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .delete("/api/produit/2")
                .with(SecurityMockMvcRequestPostProcessors.csrf()
            )
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(this.service).deleteById(2);
    }

    private String json(CreateOrUpdateProduitRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(request);
    }

    private CreateOrUpdateProduitRequest createRequest() {
        CreateOrUpdateProduitRequest request = new CreateOrUpdateProduitRequest();

        request.setNom("Produit de test");
        request.setPrix(new BigDecimal("50"));
        request.setFournisseurId(2);

        return request;
    }
}
