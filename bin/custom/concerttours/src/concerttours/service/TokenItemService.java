package concerttours.service;

import concerttours.jalo.TokenItem;
import concerttours.model.TokenItemModel;

import java.util.List;

public interface TokenItemService {
    List<TokenItemModel> getTokenItems();
}
