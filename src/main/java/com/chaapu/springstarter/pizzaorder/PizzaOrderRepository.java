package com.chaapu.springstarter.pizzaorder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface PizzaOrderRepository extends JpaRepository<PizzaOrder, Integer> {
    public List<PizzaOrder> findByPizzaNameIgnoreCase(String name, Pageable pageable);
    public Optional<PizzaOrder> findById(int id);
    public Page<PizzaOrder> findAll(Pageable pageable);
}
