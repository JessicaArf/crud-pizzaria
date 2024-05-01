package com.jessicaarf.crudpizzaria.service;

import com.jessicaarf.crudpizzaria.dtos.BebidaDTO;
import com.jessicaarf.crudpizzaria.dtos.ClienteDTO;
import com.jessicaarf.crudpizzaria.dtos.ClienteRespostaDTO;
import com.jessicaarf.crudpizzaria.dtos.PizzaDTO;
import com.jessicaarf.crudpizzaria.model.Bebida;
import com.jessicaarf.crudpizzaria.model.Cliente;
import com.jessicaarf.crudpizzaria.model.Pizza;
import com.jessicaarf.crudpizzaria.repositories.BebidaRepository;
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
public class BebidaService {

    private final BebidaRepository bebidaRepository;
    private final ModelMapper modelMapper;

    public BebidaDTO criarBebida(BebidaDTO bebidaDto) {
        Optional<Bebida> bebidaExistente = bebidaRepository.findByNome(bebidaDto.getNome());
        if (bebidaExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma bebida com esse nome.");
        }
        Bebida bebida = modelMapper.map(bebidaDto, Bebida.class);
        bebidaRepository.save(bebida);
        return modelMapper.map(bebida, BebidaDTO.class);
    }

    public List<BebidaDTO> exibirBebidas() {
        List<Bebida> bebidas = bebidaRepository.findAll();

        if (bebidas.isEmpty()) {
            return Collections.emptyList();
        }

        return bebidas.stream()
                .map(bebida -> modelMapper.map(bebida, BebidaDTO.class))
                .collect(Collectors.toList());
    }

    public BebidaDTO exibirBebidaPorId(Long id) {
        Bebida bebida = bebidaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bebida não encontrada."));
        return modelMapper.map(bebida, BebidaDTO.class);
    }

    public BebidaDTO atualizarBebida(Long id, BebidaDTO bebidaDto) {
        Bebida bebidaDb = bebidaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bebida não encontrada."));

        bebidaDb.setNome(bebidaDto.getNome());
        bebidaDb.setPreco(bebidaDto.getPreco());

        bebidaDb = bebidaRepository.save(bebidaDb);

        return modelMapper.map(bebidaDb, BebidaDTO.class);
    }

    public void deletarBebida(Long id) {
        Bebida bebidaDb = bebidaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bebida não encontrada."));
        bebidaRepository.delete(bebidaDb);
    }

}
