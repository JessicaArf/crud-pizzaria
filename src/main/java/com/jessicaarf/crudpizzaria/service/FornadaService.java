package com.jessicaarf.crudpizzaria.service;


import com.jessicaarf.crudpizzaria.dtos.FornadaDTO;
import com.jessicaarf.crudpizzaria.model.Fornada;
import com.jessicaarf.crudpizzaria.repositories.FornadaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FornadaService {

    private final FornadaRepository fornadaRepository;
    private final ModelMapper modelMapper;

    public FornadaDTO criarFornada(FornadaDTO fornadaDto) {
        Fornada fornada = modelMapper.map(fornadaDto, Fornada.class);
        fornadaRepository.save(fornada);
        return modelMapper.map(fornada, FornadaDTO.class);
    }

    public List<FornadaDTO> exibirFornadas() {
        List<Fornada> fornadas = fornadaRepository.findAll();

        if (fornadas.isEmpty()) {
            return Collections.emptyList();
        }

        return fornadas.stream()
                .map(fornada -> modelMapper.map(fornada, FornadaDTO.class))
                .collect(Collectors.toList());
    }

    public FornadaDTO exibirFornadaPorId(Long id) {
        Fornada fornada = fornadaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fornada não encontrada."));
        return modelMapper.map(fornada, FornadaDTO.class);
    }

    public FornadaDTO atualizarFornada(Long id, FornadaDTO fornadaDto) {
        Fornada fornadaDb = fornadaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fornada não encontrada."));

        fornadaDb.setNumFornada(fornadaDto.getNumFornada());
        fornadaDb.setQtdPizzas(fornadaDto.getQtdPizzas());
        fornadaRepository.save(fornadaDb);

        return modelMapper.map(fornadaDb, FornadaDTO.class);
    }

    public void deletarFornada(Long id) {
        Fornada fornadaDb = fornadaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fornada não encontrada."));
        fornadaRepository.delete(fornadaDb);
    }

}
