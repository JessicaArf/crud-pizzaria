package com.jessicaarf.crudpizzaria.service;

import com.jessicaarf.crudpizzaria.dtos.PizzaDTO;
import com.jessicaarf.crudpizzaria.model.Pizza;
import com.jessicaarf.crudpizzaria.repositories.PizzaRepository;
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
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final ModelMapper modelMapper;

    public PizzaDTO criarPizza(PizzaDTO pizzaDto) {
        Optional<Pizza> pizzaExistente = pizzaRepository.findByNome(pizzaDto.getNome());
        if (pizzaExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma pizza com esse nome.");
        }
        Pizza pizza = modelMapper.map(pizzaDto, Pizza.class);
        pizzaRepository.save(pizza);
        return modelMapper.map(pizza, PizzaDTO.class);
    }

    public List<PizzaDTO> exibirPizzas() {
        List<Pizza> pizzas = pizzaRepository.findAll();

        if (pizzas.isEmpty()) {
            return Collections.emptyList();
        }

        return pizzas.stream()
                .map(pizza -> modelMapper.map(pizza, PizzaDTO.class))
                .collect(Collectors.toList());
    }

    public PizzaDTO exibirPizzaPorId(Long id) {
        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pizza não encontrada."));
        return modelMapper.map(pizza, PizzaDTO.class);
    }

    public PizzaDTO atualizarPizza(Long id, PizzaDTO pizzaDto) {
        Pizza pizzaDb = pizzaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pizza não encontrada."));

        pizzaDb.setNome(pizzaDto.getNome());
        pizzaDb.setPrecoBase(pizzaDto.getPrecoBase());
        pizzaDb.setPersonalizada(pizzaDto.isPersonalizada());
        pizzaDb.setIngredientes(pizzaDto.getIngredientes());

        pizzaRepository.save(pizzaDb);

        return modelMapper.map(pizzaDb, PizzaDTO.class);
    }

    public void deletarPizza(Long id) {
        Pizza pizzaDb = pizzaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pizza não encontrada."));
        pizzaRepository.delete(pizzaDb);
    }

}
