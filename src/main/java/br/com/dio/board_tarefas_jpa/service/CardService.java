package br.com.dio.board_tarefas_jpa.service;

import br.com.dio.board_tarefas_jpa.dto.CardDTO;
import br.com.dio.board_tarefas_jpa.model.BoardColumn;
import br.com.dio.board_tarefas_jpa.model.Card;
import br.com.dio.board_tarefas_jpa.repository.BoardColumnRepository;
import br.com.dio.board_tarefas_jpa.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final BoardColumnRepository columnRepository;

    public Card create(CardDTO dto) {
        BoardColumn column = columnRepository.findById(dto.columnId())
                .orElseThrow(() -> new RuntimeException("Coluna não encontrada"));

        Card card = new Card();
        card.setTitle(dto.title());
        card.setDescription(dto.description());
        card.setColumn(column);

        return cardRepository.save(card);
    }

    public void delete(Long id) {
        cardRepository.deleteById(id);
    }
}