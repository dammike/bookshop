package com.dammike.bookstore.graemelee.webservice;

import com.dammike.bookstore.graemelee.model.Book;
import com.dammike.bookstore.graemelee.model.Consumer;
import com.dammike.bookstore.graemelee.model.HoldingRequest;
import com.dammike.bookstore.graemelee.service.BookService;
import com.dammike.bookstore.graemelee.service.ConsumerService;
import com.dammike.bookstore.graemelee.service.HoldingRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class HoldingRequestRestController {

    private HoldingRequestService service;
    private ConsumerService consumerService;
    private BookService bookService;

    @Autowired
    public HoldingRequestRestController(HoldingRequestService service,
                                        ConsumerService consumerService,
                                        BookService bookService) {
        this.service = service;
        this.consumerService = consumerService;
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET,
            value = {"/holdingrequests",
                    "/holdingrequests/member{memberId}/book{bookId}"},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<HoldingRequest> getAll(
            @PathVariable(name = "memberId", required = false) Long memberId,
            @PathVariable(name = "bookId", required = false) Long bookId) {
        List<HoldingRequest> holdingRequests = new ArrayList<>();

        if (memberId == null || bookId == null) {
            service.findAll().forEach(holdingRequests::add);

        } else {
            Consumer member = consumerService.getConsumerById(memberId);
            Book book = bookService.getBookById(bookId);
            List<HoldingRequest> results = service.getHoldingRequestsForMemberWithBook(member, book);
            results.forEach(holdingRequests::add);
        }
        return holdingRequests;
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/holdingrequests")
    public void create(@RequestBody HoldingRequest request) {
        log.debug("saving holding request");
        service.save(request);
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/holdingrequests",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional(
            isolation = Isolation.SERIALIZABLE,
            propagation = Propagation.REQUIRED)
    public ResponseEntity<HoldingRequest> update(@Valid @RequestBody HoldingRequest newHoldingRequest, BindingResult bindingResult) {
        HoldingRequest result = service.getHoldingRequestById(newHoldingRequest.getId());
        result.setAdmin(newHoldingRequest.getAdmin());
        result.setExpired(newHoldingRequest.isExpired());
        result.setOnlineRequest(newHoldingRequest.isOnlineRequest());

        log.debug("updating holding request");
        if (bindingResult.hasErrors()) {
            log.debug("Found Error: " + bindingResult.toString());
            return new ResponseEntity<HoldingRequest>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //service.save(newHoldingRequest);
        service.save(result);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/holdingrequests",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> delete(@RequestBody HoldingRequest holdingRequest) {
        log.debug("Deleting HoldingRequest: " + holdingRequest);
        service.delete(holdingRequest);
        return new ResponseEntity<>("Deleted Successfully!", HttpStatus.OK);
    }
}
