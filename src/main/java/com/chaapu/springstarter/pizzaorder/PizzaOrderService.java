package com.chaapu.springstarter.pizzaorder;

import com.chaapu.springstarter.exceptions.ResourceAlreadyExistsException;
import com.chaapu.springstarter.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service class PizzaOrderService {

    @Autowired
    PizzaOrderRepository pizzaOrderRepository;

    PizzaOrdersList getAllOrders(Optional<String> name, Pageable pageable) {

        ArrayList<PizzaOrder> pizzaOrders = new ArrayList<>();
        if (name.isPresent()) {
            pizzaOrders.addAll(pizzaOrderRepository.findByPizzaNameIgnoreCase((name.get()), pageable));
        } else {
            pizzaOrderRepository.findAll(pageable).forEach(pizzaOrders::add);
        }

        return new PizzaOrdersList(pizzaOrders);
    }

    PizzaOrder getOrder(int id) {
        return pizzaOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("PizzaOrder with ID %d not found", id)));
    }

    ResponseEntity<Object> addOrder(PizzaOrder pizzaOrder) {
        verifyThatOrderDoesNotAlreadyExist(pizzaOrder.getId());
        pizzaOrderRepository.save(pizzaOrder);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private void verifyThatOrderDoesNotAlreadyExist(int orderId) {
        if (pizzaOrderRepository.existsById(orderId)) {
            throw new ResourceAlreadyExistsException(
                    String.format("Cannot create PizzaOrder with ID %d as it already exists", orderId));
        }
    }

    ResponseEntity<PizzaOrder> updateOrder(PizzaOrder pizzaOrder) {
        verifyThatOrderExists(pizzaOrder.getId());
        pizzaOrderRepository.save(pizzaOrder);
        return ResponseEntity.ok().body(pizzaOrder);
    }

    void deleteOrder(int orderId) {
        verifyThatOrderExists(orderId);
        pizzaOrderRepository.deleteById(orderId);
    }

    private void verifyThatOrderExists(int orderId) {
        if (!pizzaOrderRepository.existsById(orderId)) {
            throw new ResourceNotFoundException(String.format("PizzaOrder with ID %d not found", orderId));
        }
    }
}
