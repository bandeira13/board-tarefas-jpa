package br.com.dio.board_tarefas_jpa.repository;

import br.com.dio.board_tarefas_jpa.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {


    List<Card> findByColumnId(Long columnId);

}