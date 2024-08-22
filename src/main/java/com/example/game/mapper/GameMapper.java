package com.example.game.mapper;

import com.example.game.api.model.MoveResult;
import com.example.game.repository.entity.Move;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {
    MoveResult toModel(Move entity);
    Move toEntity(MoveResult moveResult);
}
