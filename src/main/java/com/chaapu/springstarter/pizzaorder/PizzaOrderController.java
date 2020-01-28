package com.chaapu.springstarter.pizzaorder;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Api(tags = "Pizza Orders", description = "CRUD Operations for a Pizza Order")
@RestController
public class PizzaOrderController {

    @Autowired
    PizzaOrderService pizzaOrderService;

    @GetMapping(value = "/pizzaOrders", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
    @ApiOperation(value = "Get a list of all the Pizza Orders")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", required = false, dataType = "int", paramType = "query", example = "size=2", value = "number of records to return for Pagination"),
            @ApiImplicitParam(name = "page", required = false, dataType = "int", paramType = "query", defaultValue = "0", example = "page=1", value = "page number for Pagination"),
            @ApiImplicitParam(name = "sortBy", required = false, dataType = "String", paramType = "query", value = "Parameter to sort result against", examples = @Example(@ExampleProperty(value = "sort?id"))) })
    public PizzaOrdersList getAllOrders(
            @RequestParam @ApiParam(name = "pizzaName", value = "Filter result by Name of Pizza", type = "query", required = false) Optional<String> pizzaName,
            Pageable pageable) {
        return pizzaOrderService.getAllOrders(pizzaName, pageable);
    }

    @GetMapping(value = "/pizzaOrders/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found") })
    @ApiOperation(value = "Find order instance identified by ID")
    public PizzaOrder getOrder(@PathVariable int id) {
        return pizzaOrderService.getOrder(id);

    }

    @PostMapping(value = "/pizzaOrders", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 409, message = "Conflict") })
    @ApiOperation(value = "Create a new order instance", consumes = "application/json, application/xml")
    public ResponseEntity<Object> addOrder(@RequestBody @Valid PizzaOrder pizzaOrder) {
        return pizzaOrderService.addOrder(pizzaOrder);
    }

    @PutMapping(value = "pizzaOrders/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not found") })
    @ApiOperation(value = "Update Order identified by ID", consumes = "application/json, application/xml")
    public ResponseEntity<PizzaOrder> updateOrder(@PathVariable int id, @RequestBody @Valid PizzaOrder pizzaOrder) {
        pizzaOrder.setId(id);
        return pizzaOrderService.updateOrder(pizzaOrder);

    }

    @DeleteMapping(value = "pizzaOrders/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found") })
    @ApiOperation(value = "Delete an existing Customer identified by ID")
    public void deleteOrder(@PathVariable int id) {
        pizzaOrderService.deleteOrder(id);
    }
}
