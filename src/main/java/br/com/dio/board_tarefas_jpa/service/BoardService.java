package br.com.dio.board_tarefas_jpa.service;

import br.com.dio.board_tarefas_jpa.model.Board;
import br.com.dio.board_tarefas_jpa.model.BoardColumn;
import br.com.dio.board_tarefas_jpa.repository.BoardColumnRepository;
import br.com.dio.board_tarefas_jpa.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardColumnRepository columnRepository;

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

    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
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