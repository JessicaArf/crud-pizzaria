package com.jessicaarf.crudpizzaria.controller;

import com.jessicaarf.crudpizzaria.dtos.PizzaDTO;
import com.jessicaarf.crudpizzaria.service.PizzaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
@RequiredArgsConstructor
public class PizzaController {

    private final PizzaService pizzaService;

    @PostMapping
    public ResponseEntity<PizzaDTO> criarPizza(@RequestBody @Valid PizzaDTO pizzaDto){
        PizzaDTO pizza = pizzaService.criarPizza(pizzaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pizza);
    }

    @GetMapping
    public ResponseEntity<List<PizzaDTO>> exibirPizzas(){
       List<PizzaDTO> pizzas = pizzaService.exibirPizzas();
       return ResponseEntity.status(HttpStatus.OK).body(pizzas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaDTO> exibirPizzaPorId(@PathVariable Long id){
       PizzaDTO pizza = pizzaService.exibirPizzaPorId(id);
       return ResponseEntity.status(HttpStatus.OK).body(pizza);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PizzaDTO> atualizarPizza(@PathVariable Long id, @RequestBody PizzaDTO pizzaDto){
        PizzaDTO pizza = pizzaService.atualizarPizza(id, pizzaDto);
        return ResponseEntity.status(HttpStatus.OK).body(pizza);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPizza(@PathVariable Long id){
        pizzaService.deletarPizza(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pizza deletada com sucesso!");
    }

}
