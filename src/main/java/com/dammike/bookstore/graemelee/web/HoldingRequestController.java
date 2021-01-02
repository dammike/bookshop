package com.dammike.bookstore.graemelee.web;

import com.dammike.bookstore.graemelee.model.HoldingRequest;
import com.dammike.bookstore.graemelee.service.HoldingRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HoldingRequestController {
    @Autowired
    private HoldingRequestService service;

    @RequestMapping("/holdingrequests")
    public String getAll(Model model) {
        List<HoldingRequest> holdingRequests = service.findAll();
        model.addAttribute(holdingRequests);
        return "holding_requests";
    }
}
