package concerttours.daos;

import concerttours.model.ConcertModel;

import java.util.List;

public interface ConcertDao {
    List<ConcertModel> findConcerts();
}
