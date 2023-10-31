package com.ufpr.apiweb.rest;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.ufpr.apiweb.model.Pedido;
import com.ufpr.apiweb.model.Customer;

@CrossOrigin
@RestController
public class PedidoREST {
    public static List<Pedido> listaPedidos = new ArrayList<>();

    // Lista estática de clientes
    public static List<Customer> listaClientes = new ArrayList<>();

    static {
        listaClientes.add(new Customer(1, "Joao", "Silva"));
        listaClientes.add(new Customer(2, "Maria", "Santos"));
        listaClientes.add(new Customer(3, "Juliana", "Silva"));

        // Criar um pedido para cada cliente na lista de clientes
        for (Customer customer : listaClientes) {
            Pedido pedido = new Pedido(listaPedidos.size() + 1, customer);
            listaPedidos.add(pedido);
        }
    }

    @GetMapping("/pedido")
    public List<Pedido> obterPedidos() {
        return listaPedidos;
    }

    @GetMapping("/pedido/{id}")
    public Pedido obterPedidoPorId(@PathVariable("id") int id) {
        return listaPedidos.stream().filter(pedido -> pedido.getId_pedido() == id).findAny().orElse(null);
    }

    @PostMapping("/pedido")
    public Pedido inserirPedido(@RequestBody Pedido pedido) {
        Pedido newPedido = listaPedidos.stream().max(Comparator.comparing(Pedido::getId_pedido)).orElse(null);
        if (newPedido == null)
            pedido.setId_pedido(1);
        else
            pedido.setId_pedido(newPedido.getId_pedido() + 1);
        listaPedidos.add(pedido);
        return pedido;
    }

    @PutMapping("/pedido/{id}")
    public Pedido alterarPedido(@PathVariable("id") int id, @RequestBody Pedido pedido) {
        Pedido existingPedido = listaPedidos.stream().filter(p -> p.getId_pedido() == id).findAny().orElse(null);
        if (existingPedido != null) {
            existingPedido.setCustomer(pedido.getCustomer());
        }
        return existingPedido;
    }

    @DeleteMapping("/pedido/{id}")
    public Pedido removerPedido(@PathVariable("id") int id) {
        Pedido pedido = listaPedidos.stream().filter(p -> p.getId_pedido() == id).findAny().orElse(null);
        if (pedido != null)
            listaPedidos.removeIf(p -> p.getId_pedido() == id);
        return pedido;
    }
}
