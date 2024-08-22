package com.example.game.service;

import com.example.game.api.model.Figure;
import com.example.game.api.model.MoveResult;
import com.example.game.api.model.WhoWin;
import com.example.game.api.model.exception.GameCompletedException;
import com.example.game.api.model.exception.GameNotFoundException;
import com.example.game.api.model.exception.UnknownFigureException;
import com.example.game.mapper.MoveMapper;
import com.example.game.repository.GameRepository;
import com.example.game.repository.entity.Game;
import com.example.game.repository.entity.GameStatus;
import com.example.game.repository.entity.Move;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    public static final WhoWin[][] DECISION_MATRIX = {{WhoWin.TIE, WhoWin.ROBOT, WhoWin.HUMAN},
                                                      {WhoWin.HUMAN, WhoWin.TIE, WhoWin.ROBOT},
                                                      {WhoWin.ROBOT, WhoWin.HUMAN, WhoWin.TIE}};
    private GameRepository gameRepository;
    private MoveMapper moveMapper;
    private RobotService robotService;

    public GameServiceImpl(GameRepository gameRepository, MoveMapper moveMapper, RobotService robotService) {
        this.gameRepository = gameRepository;
        this.moveMapper = moveMapper;
        this.robotService = robotService;
    }

    @Override
    public Long createGame() {
        return gameRepository.save(new Game()).getId();
    }

    @Override
    public MoveResult makeMove(Long gameId, String humanInput) {
        Figure humanFigure;
        try {
            humanFigure = Figure.valueOf(humanInput);
        } catch (IllegalArgumentException ex) {
            throw new UnknownFigureException();
        }
        Game game = gameRepository.findById(gameId).orElseThrow(GameNotFoundException::new);
        if (GameStatus.CLOSED.equals(game.getStatus())) {
            throw new GameCompletedException();
        }
        //Robot make a move!
        Figure robotFigure = robotService.makeMove();
        Move result = new Move(humanFigure, robotFigure, whoWin(humanFigure, robotFigure));
        result.setGame(game);
        game.getMoves().add(result);
        gameRepository.save(game);
        return moveMapper.toModel(result);
    }

    private WhoWin whoWin(Figure humanFigure, Figure robotFigure) {
        return DECISION_MATRIX[robotFigure.ordinal()] [humanFigure.ordinal()];
    }

    @Override
    public void completeGame(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(GameNotFoundException::new);
        game.setStatus(GameStatus.CLOSED);
        gameRepository.save(game);
    }

    @Override
    public List<MoveResult> getStatistics(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(GameNotFoundException::new);
        return moveMapper.toModelList(game.getMoves());
    }
}
