package br.com.dio.board_tarefas_jpa.controller;

import br.com.dio.board_tarefas_jpa.dto.BoardDTO;
import br.com.dio.board_tarefas_jpa.model.Board;
import br.com.dio.board_tarefas_jpa.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // <--- ESSA ANOTAÇÃO É OBRIGATÓRIA
@RequestMapping("/boards") // <--- ESSA TAMBÉM
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> findById(@PathVariable Long id){
        try {
            BoardDTO board = service.findById(id);
            return ResponseEntity.ok(board);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Board> create(@RequestBody Board board){
        Board createdBoard = service.save(board);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBoard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boolean deleted = service.delete(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}