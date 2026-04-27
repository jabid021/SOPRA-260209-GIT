package fr.formation.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
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

import fr.formation.model.Fournisseur;
import fr.formation.request.CreateFournisseurRequest;
import fr.formation.security.config.SecurityConfig;
import fr.formation.service.FournisseurService;

@WebMvcTest(FournisseurApiController.class)
@WithMockUser
@Import(SecurityConfig.class)
class FournisseurApiControllerTest {
    private static final String FOURNISSEUR_NAME = "Nom de test";
    private final static String API_URL = "/api/fournisseur";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FournisseurService service;

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
    void shouldCreateStatusForbidden() throws Exception {
        // given
        CreateFournisseurRequest request = this.createRequest(FOURNISSEUR_NAME);

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post(API_URL)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.json(request))
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isForbidden());

        Mockito.verify(this.service, Mockito.never()).save(Mockito.any(), Mockito.any());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateStatusCreated() throws Exception {
        // given
        CreateFournisseurRequest request = this.createRequest(FOURNISSEUR_NAME);

        Mockito.when(this.service.save(Mockito.eq(null), Mockito.any())).thenReturn(new Fournisseur());

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
    @NullSource
    @ValueSource(strings = { "", " " })
    @WithMockUser(roles = "ADMIN")
    void shouldCreateStatusBadRequest(String nom) throws Exception {
        // given
        CreateFournisseurRequest request = this.createRequest(nom);

        Mockito.lenient().when(this.service.save(Mockito.eq(null), Mockito.any())).thenReturn(new Fournisseur());

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

        Mockito.verify(this.service, Mockito.never()).save(Mockito.any(), Mockito.any());
    }

    private String json(CreateFournisseurRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(request);
    }

    private CreateFournisseurRequest createRequest(String nom) {
        CreateFournisseurRequest request = new CreateFournisseurRequest();

        request.setNom(nom);

        return request;
    }
}
