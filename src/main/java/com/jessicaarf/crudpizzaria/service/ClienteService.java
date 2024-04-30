package com.jessicaarf.crudpizzaria.service;

import com.jessicaarf.crudpizzaria.model.Cliente;
import com.jessicaarf.crudpizzaria.repositories.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> exibirClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) {
            return Collections.emptyList();
        }
        return clientes;
    }

    public Cliente exibirClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }

    public Cliente atualizarCliente(Cliente cliente) {
        Cliente clienteDb = clienteRepository.findById(cliente.getIdCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        clienteDb.setTelefone(clienteDb.getTelefone());
        clienteDb.setEndereco(clienteDb.getEndereco());
        return clienteDb;
    }

    public void deletarCliente(Long id) {
        Cliente clienteDb = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        clienteRepository.delete(clienteDb);
    }

}
