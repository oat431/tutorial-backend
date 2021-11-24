package se331.lab.rest.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.EventDTO;
import se331.lab.rest.repository.EventRepository;
import se331.lab.rest.service.EventService;

@Component
public class QueryQL implements GraphQLQueryResolver {
    @Autowired
    EventService eventService;

    @Autowired
    EventRepository eventRepository;

    public String helloWorld() {
        return "Hello World";
    }

    public EventDTO getEvent() {
        Event output = eventService.getEvent(1L);
        return EventDTO.builder()
                .id(output.getId())
                .title(output.getTitle())
                .category(output.getCategory())
                .description(output.getDescription())
                .build();
    }

    public Iterable<Event> getAllEvent(){
        return eventRepository.findAll();
    }

}
