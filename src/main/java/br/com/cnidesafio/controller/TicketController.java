package br.com.cnidesafio.controller;

import br.com.cnidesafio.entities.Ticket;
import br.com.cnidesafio.entities.dto.TicketRequestDTO;
import br.com.cnidesafio.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<Ticket> criarChamado(@RequestBody @Valid TicketRequestDTO ticketRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.criarChamado(ticketRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> listarChamados() {
        return ResponseEntity.ok().body(ticketService.listarChamados());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarChamado(
            @RequestBody @Valid TicketRequestDTO ticketRequestDTO, @PathVariable("id") Long id) {
        ticketService.atualizarChamado(ticketRequestDTO, id);
        return ResponseEntity.noContent().build();
    }

}
