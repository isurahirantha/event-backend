package com.codeloon.ems.service;


import com.codeloon.ems.model.EventBean;
import com.codeloon.ems.util.ResponseBean;

import java.util.List;

public interface EventService {
    List<EventBean> getAllEvents();
    ResponseBean findEventById(Long eventId);
    ResponseBean createEvent(EventBean eventBean);
    ResponseBean updateEvent(Long eventId, EventBean eventBean);
    ResponseBean deleteEvent(Long eventId);
}

