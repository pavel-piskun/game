package com.example.game.repository.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity(name = "game")
public class Game {
    @Id
    @GeneratedValue
    Long id;
    GameStatus status = GameStatus.OPEN;
    @OneToMany(mappedBy="game", cascade= CascadeType.ALL)
    List<Move> moves = new ArrayList<>();

    public Game() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
}
