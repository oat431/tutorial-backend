package se331.lab.rest.graphql;

import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.EventDTO;
import se331.lab.rest.service.EventService;

@Component
public class MutationQL implements GraphQLMutationResolver {
    @Autowired
    EventService eventService;

    EventDTO saveEvent(Event event){
        Event output = eventService.save(event);
        return EventDTO.builder()
                .id(output.getId())
                .title(output.getTitle())
                .category(output.getCategory())
                .description(output.getDescription())
                .build();
    }
}
