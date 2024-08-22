package com.example.game.service;

import com.example.game.api.model.Figure;
import org.springframework.stereotype.Service;

@Service
public class RobotServiceImpl implements RobotService {
    @Override
    public Figure makeMove() {
        return Figure.randomFigure();
    }
}
