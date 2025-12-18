package br.com.dio.board_tarefas_jpa.repository;

import br.com.dio.board_tarefas_jpa.model.BoardColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardColumnRepository extends JpaRepository<BoardColumn, Long> {
    List<BoardColumn> findByBoardId(Long boardId);
}
