package com.dammike.bookstore.graemelee.webservice;

import com.dammike.bookstore.graemelee.exception.ResourceNotFoundException;
import com.dammike.bookstore.graemelee.model.HoldingRequest;
import com.dammike.bookstore.graemelee.service.HoldingRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class HoldingRequestRestController {

    @Autowired
    private HoldingRequestService holdingRequestService;

    @RequestMapping(value = {"/holdingrequests", "/holdingrequests/{id}"},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<HoldingRequest> getAllHoldingRequests(@PathVariable(required = false) Long id) {
        List<HoldingRequest> admins = new ArrayList<>();
        if (id == null) {
            holdingRequestService.findAll().forEach(admins::add);
        } else {
            HoldingRequest holdingRequest = holdingRequestService.findById(id);
            admins.add(holdingRequest);
        }
        return admins;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/holdingrequests")
    public void createHoldingRequest(@RequestBody HoldingRequest request) {
        holdingRequestService.save(request);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/holdingrequests/{id}",
    consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public void updateHoldingRequest(@RequestBody HoldingRequest holdingRequest, @PathVariable Long id) {
       /* HoldingRequest result = holdingRequestService.findById(id);
        result.setMember(holdingRequest.getMember());
        result.setRequestedDate(holdingRequest.getRequestedDate());
        result.setBookOfInterest(holdingRequest.getBookOfInterest());*/
        //todo: more to implement here

        log.debug("Updating HoldingRequest: " + holdingRequest);
        try {
            holdingRequestService.update(holdingRequest);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/holdingrequests/{id}")
    public void deleteHoldingRequest(@PathVariable Long id) {
        holdingRequestService.delete(id);
    }

}
