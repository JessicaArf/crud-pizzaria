package com.jessicaarf.crudpizzaria.controller;

import com.jessicaarf.crudpizzaria.dtos.ClienteDTO;
import com.jessicaarf.crudpizzaria.dtos.ClienteRespostaDTO;
import com.jessicaarf.crudpizzaria.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteRespostaDTO> criarCliente(@RequestBody @Valid ClienteDTO clienteDto) {
        ClienteRespostaDTO cliente = clienteService.criarCliente(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteRespostaDTO>> exibirClientes() {
        List<ClienteRespostaDTO> clientes = clienteService.exibirClientes();
        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteRespostaDTO> exibirClientePorId(@PathVariable Long id) {
        ClienteRespostaDTO cliente = clienteService.exibirClientePorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteRespostaDTO> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDto) {
        ClienteRespostaDTO clienteDb = clienteService.atualizarCliente(id, clienteDto);
        return ResponseEntity.status(HttpStatus.OK).body(clienteDb);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");
    }
}
