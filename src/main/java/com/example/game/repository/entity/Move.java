package com.example.game.repository.entity;

import com.example.game.api.model.Figure;
import com.example.game.api.model.WhoWin;
import jakarta.persistence.*;

@Entity(name = "move")
public class Move {
    @Id
    @GeneratedValue
    Long id;
    @Column(name = "human_move")
    Figure humanMove;
    @Column(name = "robot_move")
    Figure robotMove;
    WhoWin whoWin;
    @ManyToOne
    @JoinColumn(name="game_id", nullable=false)
    private Game game;

    public Move(Figure humanMove, Figure robotMove, WhoWin whoWin) {
        this.humanMove = humanMove;
        this.robotMove = robotMove;
        this.whoWin = whoWin;
    }

    public Move() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Figure getHumanMove() {
        return humanMove;
    }

    public void setHumanMove(Figure humanMove) {
        this.humanMove = humanMove;
    }

    public Figure getRobotMove() {
        return robotMove;
    }

    public void setRobotMove(Figure robotMove) {
        this.robotMove = robotMove;
    }

    public WhoWin getWhoWin() {
        return whoWin;
    }

    public void setWhoWin(WhoWin whoWin) {
        this.whoWin = whoWin;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
