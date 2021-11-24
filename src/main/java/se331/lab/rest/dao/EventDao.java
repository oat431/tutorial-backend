package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Event;
import se331.lab.rest.graphql.EventQuery;

import java.util.List;

public interface EventDao {
    Integer getEventSize();
    Page<Event> getEvents(Integer pageSize, Integer page);
    Event getEvent(Long id);

    Event save(Event event);
    Page<Event> getEvent(String name, Pageable page);

    List<Event> getEventByTitleAndCat(EventQuery eventQuery);
}
