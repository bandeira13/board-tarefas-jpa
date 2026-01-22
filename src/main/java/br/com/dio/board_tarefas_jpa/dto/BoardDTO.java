package br.com.dio.board_tarefas_jpa.dto;

import java.util.List;

public record BoardDTO(
        Long id,
        String name,
        boolean completed,
        List<BoardColumnDTO> columns
) {
}