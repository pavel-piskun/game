package com.example.game.api;

import com.example.game.api.model.MoveResult;
import com.example.game.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    ResponseEntity<Long> createGame() {
        return ResponseEntity.ok(gameService.createGame());
    }

    @PostMapping(value = "/{id}/play", consumes = "text/plain", produces = "application/json")
    ResponseEntity<MoveResult> makeMove(@PathVariable Long id, @RequestBody String humanFigure) {
        return ResponseEntity.ok(gameService.makeMove(id, humanFigure));
    }

    @PostMapping("/{id}/complete")
    ResponseEntity<java.lang.String> completeGame(@PathVariable Long id) {
        gameService.completeGame(id);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/{id}")
    ResponseEntity<List<MoveResult>> getGameInfo(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.getStatistics(id));
    }
}
