package br.com.dio.board_tarefas_jpa.service;

import br.com.dio.board_tarefas_jpa.dto.BoardColumnDTO;
import br.com.dio.board_tarefas_jpa.dto.BoardDTO;
import br.com.dio.board_tarefas_jpa.dto.CardDTO;
import br.com.dio.board_tarefas_jpa.model.Board;
import br.com.dio.board_tarefas_jpa.model.BoardColumn;
import br.com.dio.board_tarefas_jpa.repository.BoardColumnRepository;
import br.com.dio.board_tarefas_jpa.repository.BoardRepository;
import br.com.dio.board_tarefas_jpa.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardColumnRepository columnRepository;
    private final CardRepository cardRepository;

    public BoardDTO findById(Long id) {
        Board entity = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("Board não encontrado"));


        List<BoardColumn> columns = columnRepository.findByBoardId(id);

        List<BoardColumnDTO> columnsDTO = columns.stream().map(column -> {
            List<CardDTO> cardsDTO = cardRepository.findByColumnId(column.getId())
                    .stream()
                    .map(card -> new CardDTO(card.getId(), card.getTitle(), card.getDescription(), column.getId()))
                    .toList();
            return new BoardColumnDTO(column.getId(), column.getName(), column.getKind(), cardsDTO);
        }).toList();

        return new BoardDTO(entity.getId(), entity.getName(), columnsDTO);
    }

    public Board save(Board entity) {

        Board savedBoard = boardRepository.save(entity);


        List<BoardColumn> columns = List.of(
                new BoardColumn("A Fazer", 1, "INITIAL", savedBoard),
                new BoardColumn("Em Progresso", 2, "PENDING", savedBoard),
                new BoardColumn("Concluído", 3, "FINAL", savedBoard)
        );


        columnRepository.saveAll(columns);
        
        return savedBoard;
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public boolean delete(Long id) {
        if (boardRepository.existsById(id)) {
            boardRepository.deleteById(id);
            return true;
        }
        return false;
    }
}