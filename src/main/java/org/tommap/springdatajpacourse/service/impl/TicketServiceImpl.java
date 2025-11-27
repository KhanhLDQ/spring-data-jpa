package org.tommap.springdatajpacourse.service.impl;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tommap.springdatajpacourse.entity.Ticket;
import org.tommap.springdatajpacourse.repository.TicketRepository;
import org.tommap.springdatajpacourse.service.ITicketService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements ITicketService {
    private final TicketRepository ticketRepository;
    private final HikariDataSource dataSource;

    @Override
    @Transactional
    public void bookTicket(Ticket ticket) {
        ticketRepository.save(ticket);
        throw new RuntimeException("Payment Failed!");
    }

    @Override
    @Transactional
    public void doSomeTimeConsumingTasks() throws InterruptedException {
        System.out.println("=== TRANSACTION STARTED: " + System.currentTimeMillis() + " ===");

        // Get actual pool metrics
        HikariPoolMXBean poolBean = dataSource.getHikariPoolMXBean();
        System.out.println("Pool stats before database operation - Total: " + poolBean.getTotalConnections() +
                ", Active: " + poolBean.getActiveConnections() +
                ", Idle: " + poolBean.getIdleConnections());

        System.out.println(">>> SLEEPING FOR 10 SECONDS (NO DB OPERATIONS) <<<");
        Thread.sleep(10000);

        System.out.println(">>> ABOUT TO EXECUTE DB OPERATION: " + System.currentTimeMillis() + " <<<");

        List<Ticket> tickets = ticketRepository.findAll();

        System.out.println("Pool stats after database operation - Total: " + poolBean.getTotalConnections() +
                ", Active: " + poolBean.getActiveConnections() +
                ", Idle: " + poolBean.getIdleConnections());

        System.out.println(">>> DB OPERATION COMPLETED: " + System.currentTimeMillis() + " <<<");
        System.out.println("Found " + tickets.size() + " tickets");

        System.out.println("=== TRANSACTION ENDING ===");
    }
}
