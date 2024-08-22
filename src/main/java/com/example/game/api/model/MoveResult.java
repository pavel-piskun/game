package com.example.game.api.model;

public class MoveResult {
    private Figure humanMove;
    private Figure robotMove;
    private WhoWin whoWin;

    public MoveResult() {
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
}
