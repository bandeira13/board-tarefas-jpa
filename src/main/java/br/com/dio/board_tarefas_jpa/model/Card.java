package br.com.dio.board_tarefas_jpa.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne // Várias tarefas pertencem a uma Coluna
    @JoinColumn(name = "column_id") // Cria a chave estrangeira no banco
    private BoardColumn column;

}