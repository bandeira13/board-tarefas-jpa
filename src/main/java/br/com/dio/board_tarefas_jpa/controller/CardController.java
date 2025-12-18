package br.com.dio.board_tarefas_jpa.controller;

import br.com.dio.board_tarefas_jpa.dto.CardDTO;
import br.com.dio.board_tarefas_jpa.model.Card;
import br.com.dio.board_tarefas_jpa.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<Card> create(@RequestBody CardDTO dto){
        Card card = cardService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(card);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        cardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}