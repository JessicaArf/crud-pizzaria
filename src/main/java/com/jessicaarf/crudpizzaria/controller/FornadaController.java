package com.jessicaarf.crudpizzaria.controller;

import com.jessicaarf.crudpizzaria.dtos.FornadaDTO;
import com.jessicaarf.crudpizzaria.service.FornadaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/fornadas")
@RequiredArgsConstructor
public class FornadaController {

    private final FornadaService fornadaService;

    @PostMapping
    public ResponseEntity<FornadaDTO> criarFornada(@RequestBody @Valid FornadaDTO fornadaDTO){
        FornadaDTO fornadaaDb = fornadaService.criarFornada(fornadaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(fornadaaDb);
    }

    @GetMapping
    public ResponseEntity<List<FornadaDTO>> exibirFornadas(){
        List<FornadaDTO> fornadas = fornadaService.exibirFornadas();
        return ResponseEntity.status(HttpStatus.OK).body(fornadas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornadaDTO> exibirFornadaPorId(@PathVariable Long id){
        FornadaDTO fornadaDb = fornadaService.exibirFornadaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(fornadaDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornadaDTO> atualizarFornada(@PathVariable Long id, @RequestBody FornadaDTO fornadaDto){
        FornadaDTO fornadaDb = fornadaService.atualizarFornada(id, fornadaDto);
        return ResponseEntity.status(HttpStatus.OK).body(fornadaDb);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarFornada(@PathVariable Long id){
        fornadaService.deletarFornada(id);
        return ResponseEntity.status(HttpStatus.OK).body("Fornada deletada com sucesso!");
    }
}
