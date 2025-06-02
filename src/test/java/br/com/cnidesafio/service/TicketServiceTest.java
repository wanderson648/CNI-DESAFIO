package br.com.cnidesafio.service;

import br.com.cnidesafio.entities.Ticket;
import br.com.cnidesafio.entities.dto.TicketRequestDTO;
import br.com.cnidesafio.exception.ChamadoNaoEncontrado;
import br.com.cnidesafio.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private SentimentService sentimentService;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void deveCriarChamadoComSentimento() {
        TicketRequestDTO dto = new TicketRequestDTO("Título", "Sistema lento e travando", "Desempenho");
        String sentimento = "NEGATIVE";
        when(sentimentService.analisarSentimentoExterno(dto.descricao())).thenReturn(sentimento);

        Ticket ticketParaSalvar = new Ticket(dto, sentimento);
        ticketParaSalvar.setId(1L);

        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticketParaSalvar);

        Ticket ticketCriado = ticketService.criarChamado(dto);

        assertNotNull(ticketCriado);
        assertEquals(1L, ticketCriado.getId());
        assertEquals("NEGATIVE", ticketCriado.getSentimento());
        assertEquals("Título", ticketCriado.getTitulo());
        assertEquals("Sistema lento e travando", ticketCriado.getDescricao());
        assertEquals("Desempenho", ticketCriado.getCategoria());
    }

    @Test
    void deveListarTodosOsChamados() {
        List<Ticket> tickets = List.of(new Ticket(), new Ticket());
        when(ticketRepository.findAll()).thenReturn(tickets);

        List<Ticket> result = ticketService.listarChamados();
        assertEquals(2, result.size());
    }

    @Test
    void deveAtualizarChamadoComNovoSentimento() {
        Long id = 1L;
        Ticket ticketMock = Mockito.mock(Ticket.class);
        TicketRequestDTO dto = new TicketRequestDTO("Novo título", "Nova descrição", "Categoria");

        when(ticketRepository.findById(id)).thenReturn(Optional.of(ticketMock));
        when(sentimentService.analisarSentimentoExterno("Nova descrição")).thenReturn("NEUTRO");

        ticketService.atualizarChamado(dto, id);

        verify(ticketMock).atualizarInformacoes(dto, "NEUTRO");
        verify(ticketRepository).save(ticketMock);
    }

    @Test
    void deveLancarExcecaoQuandoChamadoNaoExistir() {
        Long id = 99L;
        TicketRequestDTO dto = new TicketRequestDTO("teste", "teste", "teste");
        when(ticketRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ChamadoNaoEncontrado.class, () -> ticketService.atualizarChamado(dto, id));
    }

}