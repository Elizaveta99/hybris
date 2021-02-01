package org.training.daos;

import de.hybris.platform.servicelayer.internal.dao.Dao;
import org.training.model.ContactRequestModel;

import java.util.List;

public interface ContactRequestDao extends Dao
{
    public List<ContactRequestModel> findBySender(String sender);
}
