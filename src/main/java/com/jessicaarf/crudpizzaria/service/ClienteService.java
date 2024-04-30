package com.jessicaarf.crudpizzaria.service;

import com.jessicaarf.crudpizzaria.dtos.ClienteDTO;
import com.jessicaarf.crudpizzaria.dtos.ClienteRespostaDTO;
import com.jessicaarf.crudpizzaria.model.Cliente;
import com.jessicaarf.crudpizzaria.repositories.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    public ClienteRespostaDTO criarCliente(ClienteDTO clienteDto) {
        Optional<Cliente> clienteExistente = clienteRepository.findByLogin(clienteDto.getLogin());
        if (clienteExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um cliente com esse login.");
        }
        Cliente cliente = modelMapper.map(clienteDto, Cliente.class);
        clienteRepository.save(cliente);
        return modelMapper.map(cliente, ClienteRespostaDTO.class);
    }

    public List<ClienteRespostaDTO> exibirClientes() {
        // Recupera todos os clientes do banco de dados
        List<Cliente> clientes = clienteRepository.findAll();

        // Verifica se a lista de clientes está vazia (
        if (clientes.isEmpty()) {
            return Collections.emptyList(); // Retorna uma lista vazia se não houver clientes
        }

        // Processa a lista de clientes utilizando Stream
        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteRespostaDTO.class))
                .collect(Collectors.toList());
    }

    public ClienteRespostaDTO exibirClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        return modelMapper.map(cliente, ClienteRespostaDTO.class);
    }

    public ClienteRespostaDTO atualizarCliente(Long id, ClienteDTO clienteDto) {
        // Busca o cliente no banco de dados pelo id
        Cliente clienteDb = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        clienteDb.setTelefone(clienteDto.getTelefone());
        clienteDb.setEndereco(clienteDto.getEndereco());
        clienteDb.setSenha(clienteDto.getSenha());

        clienteDb = clienteRepository.save(clienteDb);

        // Retorna o DTO de resposta com as informações atualizadas
        return modelMapper.map(clienteDb, ClienteRespostaDTO.class);
    }

    public void deletarCliente(Long id) {
        Cliente clienteDb = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        clienteRepository.delete(clienteDb);
    }

}
