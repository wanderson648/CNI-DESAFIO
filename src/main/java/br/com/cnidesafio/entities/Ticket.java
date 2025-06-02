package br.com.cnidesafio.entities;

import br.com.cnidesafio.entities.dto.TicketRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    private String categoria;
    private String sentimento;

    public Ticket(TicketRequestDTO ticketRequestDTO, String sentimento) {
        this.titulo = ticketRequestDTO.titulo();
        this.descricao = ticketRequestDTO.descricao();
        this.categoria = ticketRequestDTO.categoria();
        this.sentimento = sentimento;
    }

    public void atualizarInformacoes(TicketRequestDTO ticketRequestDTO, String sentimento) {
        this.titulo = ticketRequestDTO.titulo();
        this.descricao = ticketRequestDTO.descricao();
        this.categoria = ticketRequestDTO.categoria();
        this.sentimento = sentimento;
    }
}
