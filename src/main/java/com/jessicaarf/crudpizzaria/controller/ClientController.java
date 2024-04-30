package com.jessicaarf.crudpizzaria.controller;

import com.jessicaarf.crudpizzaria.model.Cliente;
import com.jessicaarf.crudpizzaria.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClientController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        clienteService.criarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);

    }

    @GetMapping
    public ResponseEntity<List<Cliente>> exibirClientes() {
        List<Cliente> clientes = clienteService.exibirClientes();
        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> exibirClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.exibirClientePorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteDb = clienteService.atualizarCliente(cliente);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");
    }
}
