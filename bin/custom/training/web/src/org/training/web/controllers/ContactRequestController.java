package org.training.web.controllers;

import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.training.service.ContactRequestService;
import org.training.model.ContactRequestModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ContactRequestController implements Controller
{
    private ContactRequestService contactRequestService;
    private ModelService modelService;

    @Override
    public ModelAndView handleRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception
    {
        final String sender = request.getParameter("sender");
        ContactRequestModel contactRequest = null;
        if (sender != null)
        {
            try
            {
                contactRequest = contactRequestService.getContactRequest(sender);
            }
            catch (final UnknownIdentifierException e)
            {
                // OK, nothing found
            }
        }

        if (request.getMethod().equalsIgnoreCase("POST"))
        {
            if (contactRequest == null)
            {
                contactRequest = new ContactRequestModel();
                modelService.attach(contactRequest);
            }
            final String newSender = request.getParameter("newSender");
            final String newMessage = request.getParameter("newMessage");
            if (newSender != null)
            {
                contactRequest.setSender(newSender);
            }
            if (newMessage != null)
            {
                contactRequest.setMessage(newMessage);
            }
            modelService.save(contactRequest);
        }

        final Map<String, Object> model = new HashMap<String, Object>();
        model.put("contactRequest", contactRequest);
        return new ModelAndView("contactRequest.jsp", model);
    }

    public void setContactRequestService(final ContactRequestService contactRequestService)
    {
        this.contactRequestService = contactRequestService;
    }

    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }
}
