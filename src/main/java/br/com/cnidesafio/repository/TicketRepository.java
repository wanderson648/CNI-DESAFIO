package br.com.cnidesafio.repository;

import br.com.cnidesafio.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
