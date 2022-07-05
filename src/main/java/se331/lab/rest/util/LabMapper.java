package se331.lab.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import se331.lab.rest.entity.*;
import java.util.stream.Collectors;
import java.util.List;

@Mapper(imports = Collectors.class)
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);
    EventDTO getEventDto(Event event);
    List<EventDTO> getEventDto(List<Event> events);

    OrganizerDTO getOrganizerDTO(Organizer organizer);
    List<OrganizerDTO> getOrganizerDTO(List<Organizer> organizers);

    @Mapping(target = "authorities",
            expression = "java(organizer.getUser()" +
                    ".getAuthorities()" +
                    ".stream()" +
                    ".map(auth -> auth.getName().name())" +
                    ".collect(Collectors.toList()))")
    OrganizerAuthDTO getOrganizerAuthDTO(Organizer organizer);

    AccessTokenDTO getAccessToken(AccessTokenDTO accessToken);
}
