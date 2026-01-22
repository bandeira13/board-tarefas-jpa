package br.com.dio.board_tarefas_jpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "board_columns")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BoardColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "column_order")
    private int order;

    private String kind;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @JsonIgnore
    @ToString.Exclude
    private Board board;

    @OneToMany(mappedBy = "column", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Card> cards = new ArrayList<>();

    public BoardColumn(String name, int order, String kind, Board board) {
        this.name = name;
        this.order = order;
        this.kind = kind;
        this.board = board;
    }
}