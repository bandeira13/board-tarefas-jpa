package br.com.dio.board_tarefas_jpa.dto;

public record CardDTO(
        Long id,
        String title,
        String description,
        Long columnId
) {
}