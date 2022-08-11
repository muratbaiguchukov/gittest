package kg.itacademy.stomservice.mappers;

import kg.itacademy.stomservice.entity.Card;
import kg.itacademy.stomservice.models.CardCreateModel;
import kg.itacademy.stomservice.models.CardModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CardMapper {

    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    CardModel toModel(Card card);

    Card toEntity(CardModel card);
    Card toEntity(CardCreateModel card);

    List<CardModel> toListModel(List<Card> card);
}
