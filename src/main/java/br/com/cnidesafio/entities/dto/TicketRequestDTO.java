package br.com.cnidesafio.entities.dto;

import jakarta.validation.constraints.NotBlank;

public record TicketRequestDTO(

        @NotBlank(message = "O campo título não pode ser vazio!")
        String titulo,
        @NotBlank(message = "O campo descrição não pode ser vazio!")
        String descricao,
        String categoria) {
}
