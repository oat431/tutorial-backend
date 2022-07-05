package se331.lab.rest.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.EventDTO;
import se331.lab.rest.entity.EventQuery;
import se331.lab.rest.service.EventService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QueryQL2 implements GraphQLQueryResolver {
    @Autowired
    EventService eventService;
    List<EventDTO> getEvents() {
        List<Event> events = eventService.getEvents(10, 1).getContent();
        return events.stream().map(event -> EventDTO.builder()
                .id(event.getId())
                .category(event.getCategory())
                .time(event.getTime())
                .title(event.getTitle())
                .description(event.getDescription())
                .build()).collect(Collectors.toList());
    }

    EventDTO getEventById(Long id){
       Event event = eventService.getEvent(id);
       return EventDTO.builder()
               .id(event.getId())
               .category(event.getCategory())
               .time(event.getTime())
               .title(event.getTitle())
               .description(event.getDescription())
               .build();
    }

    List<EventDTO> getEventByTitleAndCat(EventQuery query){
        List<Event> events = eventService.getEventByTitleAndCat(query);
        return events.stream().map(event -> EventDTO.builder()
                .id(event.getId())
                .category(event.getCategory())
                .time(event.getTime())
                .title(event.getTitle())
                .description(event.getDescription())
                .build()).collect(Collectors.toList());
    }
}
