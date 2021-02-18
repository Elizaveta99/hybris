package concerttours.service.impl;

import concerttours.daos.ConcertDao;
import concerttours.model.ConcertModel;
import concerttours.service.ConcertService;

import java.util.List;

public class DefaultConcertService implements ConcertService {

    private ConcertDao concertDAO;

    public void setConcertDAO(ConcertDao concertDAO) {
        this.concertDAO = concertDAO;
    }

    @Override
    public List<ConcertModel> getConcerts() {
        return concertDAO.findConcerts();
    }
}
