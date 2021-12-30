package si.fri.rsoteam.services.mappers;

import si.fri.rsoteam.entities.StatsEntity;
import si.fri.rsoteam.lib.dtos.StatsDto;

import java.util.stream.Collectors;

public class StatsMapper {
    public static StatsDto entityToDto(StatsEntity et) {
        StatsDto statsDto = new StatsDto();
        statsDto.id = et.getId();
        statsDto.userId = et.getUserId();
        statsDto.value= et.getValue();
        statsDto.category= et.getCategory();

        return statsDto;
    }

    public static StatsEntity dtoToEntity(StatsDto statsDto) {
        StatsEntity statsEntity = new StatsEntity();
        statsEntity.setCategory(statsDto.category);
        statsEntity.setValue(statsDto.value);
        statsEntity.setUserId(statsDto.userId);
        return  statsEntity;
    }
}
