package br.com.dio.board_tarefas_jpa.dto;

import java.util.List;

public record BoardColumnDTO(
        Long id,
        String name,
        String kind,
        List<CardDTO> cards
) {
}