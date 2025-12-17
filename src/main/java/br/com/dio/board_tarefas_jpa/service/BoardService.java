package br.com.dio.board_tarefas_jpa.service;

import br.com.dio.board_tarefas_jpa.model.Board;
import br.com.dio.board_tarefas_jpa.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;

    public Board save(Board entity) {
        return repository.save(entity);
    }

    public Optional<Board> findById(Long id) {
        return repository.findById(id);
    }

    public List<Board> findAll() {
        return repository.findAll();
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}