package me.jamboxman5.boateco.repository;

import me.jamboxman5.boateco.dao.EntryDAO;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Component;

@Component
public class EntryRepository {

    private final EntryDAO dao;

    public EntryRepository(Jdbi jdbi) {
        this.dao = jdbi.onDemand(EntryDAO.class);
    }

    public EntryDAO getDao() {
        return dao;
    }

}
