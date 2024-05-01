package com.jessicaarf.crudpizzaria.controller;

import com.jessicaarf.crudpizzaria.dtos.PedidoDTO;
import com.jessicaarf.crudpizzaria.dtos.PedidoRespostaDTO;
import com.jessicaarf.crudpizzaria.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<PedidoRespostaDTO> criarPedido(@RequestBody @Valid PedidoDTO pedidoDto){
        PedidoRespostaDTO pedidoRespostaDto = pedidoService.criarPedido(pedidoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoRespostaDto);
    }

    @GetMapping
    public ResponseEntity<List<PedidoRespostaDTO>> exibirPedidos(){
        List<PedidoRespostaDTO> pedidos = pedidoService.exibirPedidos();
        return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoRespostaDTO> exibirPedidoPorId(@PathVariable Long id){
        PedidoRespostaDTO pedidoDb = pedidoService.exibirPedidoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoRespostaDTO> atualizarPedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDto){
        PedidoRespostaDTO pedidoDb = pedidoService.editarPedido(id, pedidoDto);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoDb);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPedido(@PathVariable Long id){
        pedidoService.deletarPedido(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pedido deletado com sucesso!");
    }
}
