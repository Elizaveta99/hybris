package concerttours.daos;

import concerttours.model.TokenItemModel;

import java.util.List;

public interface TokenItemDAO {
    List<TokenItemModel> findTokenItems();
}
