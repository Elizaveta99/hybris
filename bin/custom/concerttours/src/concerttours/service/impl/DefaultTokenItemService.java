package concerttours.service.impl;

import concerttours.daos.TokenItemDAO;
import concerttours.model.TokenItemModel;
import concerttours.service.TokenItemService;

import java.util.List;

public class DefaultTokenItemService implements TokenItemService {

    private TokenItemDAO tokenItemDAO;

    public void setTokenItemDAO(TokenItemDAO tokenItemDAO) {
        this.tokenItemDAO = tokenItemDAO;
    }

    @Override
    public List<TokenItemModel> getTokenItems() {
        return tokenItemDAO.findTokenItems();
    }
}
