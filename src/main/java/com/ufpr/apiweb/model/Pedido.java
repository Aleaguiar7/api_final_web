package com.ufpr.apiweb.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pedido;
    private Date data;

    @ManyToOne
    private Customer customer;

    public Pedido() {
        super();
        this.data = new Date(); // A data é gerada automaticamente na criação do pedido.
    }

    public Pedido(int id_pedido, Customer customer) {
        super();
        this.id_pedido = id_pedido;
        this.customer = customer;
        this.data = new Date(); // A data é gerada automaticamente na criação do pedido.
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
