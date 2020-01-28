package com.chaapu.springstarter.pizzaorder;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;

@Entity
@JacksonXmlRootElement
@ApiModel(value = "Pizza Order", description = "All the pizza order related information")
public class PizzaOrder {

    @Id
    //    @NotNull
    @ApiModelProperty(value = "Unique ID for each order")
    @Min(value = 1)
    private int id;

    @NotNull
    @ApiModelProperty(value = "Name of the pizza to order e.g veggie lovers")
    private String pizzaName;

    @ApiModelProperty(value = "Type of curst e.g. thin, cheese burst etc.")
    private String crust;

    @JacksonXmlElementWrapper(useWrapping = false)
    @ApiModelProperty(value = "List of the toppings to include")
    private LinkedList<String> toppings = new LinkedList<>();

    public PizzaOrder() {
    }

    public PizzaOrder(int id, String pizzaName, String crust, LinkedList<String> toppings) {
        this.id = id;
        this.pizzaName = pizzaName;
        this.crust = crust;
        this.toppings = toppings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public LinkedList<String> getToppings() {
        return toppings;
    }

    public void setToppings(LinkedList<String> toppings) {
        this.toppings = toppings;
    }
}
