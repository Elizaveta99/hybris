package org.training.service;

import org.training.model.ContactRequestModel;

public interface ContactRequestService
{
    ContactRequestModel getContactRequest(String sender);
}