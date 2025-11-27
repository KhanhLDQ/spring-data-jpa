package org.tommap.springdatajpacourse.service;

import org.tommap.springdatajpacourse.entity.Ticket;

public interface ITicketService {
    void bookTicket(Ticket ticket);
    void doSomeTimeConsumingTasks() throws InterruptedException;
}
