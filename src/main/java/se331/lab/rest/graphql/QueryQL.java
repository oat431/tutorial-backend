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

import se331.lab.rest.dao.EventDao;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.EventDTO;
import se331.lab.rest.service.EventService;
import se331.lab.rest.util.LabMapper;

@Component
public class QueryQL implements GraphQLQueryResolver {
    String helloWorld() {
        return "Hello World";
    }

    @Autowired
    EventDao eventDao;
    @Autowired
    EventService eventService;
    EventDTO getEvent(){
        Event event = (eventService.getEvent(1l));
        return EventDTO.builder()
                .id(event.getId())
                .category(event.getCategory())
                .time(event.getTime())
                .title(event.getTitle())
                .description(event.getDescription())
                .build();
//        return LabMapper.INSTANCE.getEventDto(eventDao.getEvent(1l));
    }
}
