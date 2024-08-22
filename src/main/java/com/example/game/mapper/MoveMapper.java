package com.example.game.mapper;

import com.example.game.api.model.MoveResult;
import com.example.game.repository.entity.Move;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MoveMapper {
    @Mapping(source = "humanMove", target = "humanMove")

    MoveResult toModel(Move entity);
    List<MoveResult> toModelList(List<Move> moves);
}
