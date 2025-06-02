package br.com.cnidesafio.service;

import br.com.cnidesafio.entities.Ticket;
import br.com.cnidesafio.entities.dto.TicketRequestDTO;
import br.com.cnidesafio.exception.ChamadoNaoEncontrado;
import br.com.cnidesafio.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketService {

    private final TicketRepository ticketRepository;
    private final SentimentService sentimentService;

    @Transactional
    public Ticket criarChamado(TicketRequestDTO ticketRequestDTO) {
        log.info("[start] TicketService - criarChamado");
        String sentimento = sentimentService.analisarSentimentoExterno(ticketRequestDTO.descricao());
        Ticket ticket = new Ticket(ticketRequestDTO, sentimento);
        ticketRepository.save(ticket);
        log.info("[finish] TicketService - criarChamado");
        return ticketRepository.save(ticket);
    }

    @Transactional(readOnly = true)
    public List<Ticket> listarChamados() {
        log.info("[start] TicketService - listarChamados");
        log.info("[finish] TicketService - listarChamados");
        return ticketRepository.findAll();
    }

    @Transactional
    public void atualizarChamado(TicketRequestDTO ticketRequestDTO, Long id) {
        log.info("[start] TicketService - atualizarChamado");
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ChamadoNaoEncontrado("Chamado não encontrado!"));

        String sentimento = sentimentService.analisarSentimentoExterno(ticketRequestDTO.descricao());
        ticket.atualizarInformacoes(ticketRequestDTO, sentimento);
        ticketRepository.save(ticket);
        log.info("[finish] TicketService - atualizarChamado");
    }
}
