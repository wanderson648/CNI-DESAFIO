package br.com.cnidesafio.controller;

import br.com.cnidesafio.entities.dto.TicketRequestDTO;
import br.com.cnidesafio.service.SentimentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class TicketControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @MockBean
    private SentimentService sentimentService;

    @Test
    void deveCriarChamadoERetornarTicketCompleto() throws Exception {
        TicketRequestDTO requestDTO = new TicketRequestDTO(
                "Erro na tela",
                "A tela trava ao clicar no botão de salvar",
                "Usabilidade"
        );

        when(sentimentService.analisarSentimentoExterno(requestDTO.descricao()))
                .thenReturn("NEGATIVE");

        String json = objectMapper.writeValueAsString(requestDTO);

        mockMvc.perform(post("/api/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.titulo").value("Erro na tela"))
                .andExpect(jsonPath("$.descricao").value("A tela trava ao clicar no botão de salvar"))
                .andExpect(jsonPath("$.categoria").value("Usabilidade"))
                .andExpect(jsonPath("$.sentimento").value("NEGATIVE"));
    }
}
