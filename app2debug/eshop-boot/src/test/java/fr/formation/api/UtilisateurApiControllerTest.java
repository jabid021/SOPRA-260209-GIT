package fr.formation.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.repo.UtilisateurRepository;
import fr.formation.request.SubscriptionRequest;
import fr.formation.security.config.SecurityConfig;

@WebMvcTest(UtilisateurApiController.class)
@Import(SecurityConfig.class)
class UtilisateurApiControllerTest {
    private final static String API_URL = "/api/user/subscribe";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UtilisateurRepository repository;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldSubscribeStatusOk() throws Exception {
        // given
        SubscriptionRequest request = this.createRequest("test", "123", "test@test.fr");

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post(API_URL)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.json(request))
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.passwordEncoder).encode("123");
        Mockito.verify(this.repository).save(Mockito.any());
    }

    @ParameterizedTest
    @CsvSource({
        "'','',''",
        "test,,test@test.fr",
        "test,123,wrong-email"
    })
    void shouldSubscribeStatusBadRequest(String username, String password, String email) throws Exception {
        // given
        SubscriptionRequest request = this.createRequest(username, password, email);

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

        Mockito.verify(this.repository, Mockito.never()).save(Mockito.any());
    }

    private String json(SubscriptionRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(request);
    }

    private SubscriptionRequest createRequest(String username, String password, String email) {
        SubscriptionRequest request = new SubscriptionRequest();

        request.setUsername(username);
        request.setPassword(password);
        request.setEmail(email);

        return request;
    }
}
