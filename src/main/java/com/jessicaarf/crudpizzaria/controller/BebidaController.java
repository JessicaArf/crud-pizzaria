package com.jessicaarf.crudpizzaria.controller;

import com.jessicaarf.crudpizzaria.dtos.BebidaDTO;
import com.jessicaarf.crudpizzaria.service.BebidaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bebidas")
@RequiredArgsConstructor
public class BebidaController {

    private final BebidaService bebidaService;

    @PostMapping
    public ResponseEntity<BebidaDTO> criarBebida(@RequestBody @Valid BebidaDTO bebidaDTO){
        BebidaDTO bebidaDb = bebidaService.criarBebida(bebidaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bebidaDb);
    }

    @GetMapping
    public ResponseEntity<List<BebidaDTO>> exibirBebidas(){
        List<BebidaDTO> bebidas = bebidaService.exibirBebidas();
        return ResponseEntity.status(HttpStatus.OK).body(bebidas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BebidaDTO> exibirBebidaPorId(@PathVariable Long id){
        BebidaDTO bebidaDb = bebidaService.exibirBebidaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(bebidaDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BebidaDTO> atualizarBebida(@PathVariable Long id, @RequestBody BebidaDTO bebidaDto){
        BebidaDTO bebidaDb = bebidaService.atualizarBebida(id, bebidaDto);
        return ResponseEntity.status(HttpStatus.OK).body(bebidaDb);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarBebida(@PathVariable Long id){
        bebidaService.deletarBebida(id);
        return ResponseEntity.status(HttpStatus.OK).body("Bebida deletada com sucesso!");
    }

}
