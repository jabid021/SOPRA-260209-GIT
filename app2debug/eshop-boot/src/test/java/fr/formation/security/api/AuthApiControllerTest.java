package fr.formation.security.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.request.AuthRequest;
import fr.formation.response.AuthResponse;
import fr.formation.security.service.SecurityService;

@ExtendWith(MockitoExtension.class)
class AuthApiControllerTest {
    private final static String API_URL = "/api/auth";

    private MockMvc mockMvc;

    @Mock
    private SecurityService service;

    @InjectMocks
    private AuthApiController ctrl;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
            .standaloneSetup(ctrl)
            .build();
    }

    @Test
    void shouldAuthOk() throws Exception {
        // given
        AuthRequest request = new AuthRequest("localuser", "123456$");

        Mockito
            .when(this.service.auth(Mockito.any()))
            .thenReturn(new AuthResponse(true, "the.token"))
        ;

        // when
        ResultActions result = this.mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post(API_URL)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(this.json(request))
            )
        ;

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.token").value("the.token"));

        Mockito.verify(this.service).auth(Mockito.any());
    }

    @Test
    void shouldAuthFail() throws Exception {
        // given
        AuthRequest request = new AuthRequest("localuser", "123456$");

        Mockito.when(this.service.auth(Mockito.any())).thenReturn(new AuthResponse());

        // when
        ResultActions result = this.mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post(API_URL)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(this.json(request))
            )
        ;

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.token").isEmpty());

        Mockito.verify(this.service).auth(Mockito.any());
    }

    @Test
    void shouldAuthBadRequest() throws Exception {
        // given
        AuthRequest request = new AuthRequest("localuser", "");

        // when
        ResultActions result = this.mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post(API_URL)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(this.json(request))
            )
        ;

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(this.service, Mockito.never()).auth(Mockito.any());
    }

    @Test
    void shouldAuthBadRequestWhenNoRequestBody() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.post(API_URL));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(this.service, Mockito.never()).auth(Mockito.any());
    }

    private String json(AuthRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(request);
    }
}
