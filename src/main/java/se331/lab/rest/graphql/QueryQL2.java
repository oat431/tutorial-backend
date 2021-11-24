package se331.lab.rest.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.EventDTO;
import se331.lab.rest.service.EventService;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QueryQL2 implements GraphQLQueryResolver {
    @Autowired
    EventService eventService;

    public List<EventDTO> getEvents(){
        List<Event> events = eventService.getEvents(10,1).getContent();
        return events.stream().map(
                output -> EventDTO.builder()
                        .id(output.getId())
                        .title(output.getTitle())
                        .category(output.getCategory())
                        .description(output.getDescription())
                        .build()
        ).collect(Collectors.toList());
    }

    public EventDTO getEventById(Long id){
        Event output = eventService.getEvent(id);
        return EventDTO.builder()
                .id(output.getId())
                .title(output.getTitle())
                .category(output.getCategory())
                .description(output.getDescription())
                .build();
    }

    public List<EventDTO> getEventByTitleAndCat(EventQuery eventQuery){
        List<Event> events = eventService.getEventByTitleAndCat(eventQuery);
        return events.stream().map(
                output -> EventDTO.builder()
                        .id(output.getId())
                        .title(output.getTitle())
                        .category(output.getCategory())
                        .description(output.getDescription())
                        .build()
        ).collect(Collectors.toList());
    }

}
