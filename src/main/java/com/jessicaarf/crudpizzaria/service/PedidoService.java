package com.jessicaarf.crudpizzaria.service;

import com.jessicaarf.crudpizzaria.dtos.BebidaDTO;
import com.jessicaarf.crudpizzaria.dtos.ClienteRespostaDTO;
import com.jessicaarf.crudpizzaria.dtos.PedidoDTO;
import com.jessicaarf.crudpizzaria.dtos.PedidoRespostaDTO;
import com.jessicaarf.crudpizzaria.model.*;
import com.jessicaarf.crudpizzaria.repositories.BebidaRepository;
import com.jessicaarf.crudpizzaria.repositories.ClienteRepository;
import com.jessicaarf.crudpizzaria.repositories.PedidoRepository;
import com.jessicaarf.crudpizzaria.repositories.PizzaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
   // private final FornadaRepository fornadaRepository;
    private final PizzaRepository pizzaRepository;
    private final BebidaRepository bebidaRepository;
    private final ModelMapper modelMapper;

    public PedidoRespostaDTO criarPedido(PedidoDTO pedidoDto) {

        if (!validarEntidades(pedidoDto)) {
            throw new EntityNotFoundException("Uma ou mais entidades não foram encontradas.");
        }

        Pedido pedido = modelMapper.map(pedidoDto, Pedido.class);

        for (PizzaPedida pizzaPedida : pedido.getPizzaPedidos()) {
            pizzaPedida.setPedido(pedido);
        }
        for (BebidaPedida bebidaPedida : pedido.getBebidaPedidos()) {
            bebidaPedida.setPedido(pedido);
        }
        pedidoRepository.save(pedido);

        return modelMapper.map(pedido, PedidoRespostaDTO.class);
    }


    public List<PedidoRespostaDTO> exibirPedidos() {

        List<Pedido> pedidos = pedidoRepository.findAll();

        if (pedidos.isEmpty()) {
            return Collections.emptyList();
        }

        return pedidos.stream()
                .map(pedido -> modelMapper.map(pedido, PedidoRespostaDTO.class))
                .collect(Collectors.toList());
    }

    public PedidoRespostaDTO exibirPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado."));
        return modelMapper.map(pedido, PedidoRespostaDTO.class);
    }

    public PedidoRespostaDTO editarPedido(Long id, PedidoDTO pedidoDto) {
        Pedido pedidoDb = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado."));

        pedidoDb.setFornada(pedidoDto.getFornada());
        pedidoDb.setPizzaPedidos(pedidoDto.getPizzaPedidos());
        pedidoDb.setBebidaPedidos(pedidoDto.getBebidaPedidos());
        pedidoRepository.save(pedidoDb);

        return modelMapper.map(pedidoDb, PedidoRespostaDTO.class);
    }

    public void deletarPedido(Long id) {
        Pedido pedidoDb = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado."));
        pedidoRepository.delete(pedidoDb);
    }

    public boolean validarEntidades(PedidoDTO pedidoDto){

        Cliente cliente = clienteRepository.findById(pedidoDto.getCliente().getIdCliente())
                .orElse(null);
        if (cliente == null) {
            return false;
        }

        // Check if Fornada exists
       /* Fornada fornada = fornadaRepository.findById(pedidoDto.getFornada().getId())
                .orElse(null);
        if (fornada == null) {
            return false;
        }
        */

        for (PizzaPedida pizzaPedida : pedidoDto.getPizzaPedidos()) {
            Pizza pizza = pizzaRepository.findById(pizzaPedida.getPizza().getIdPizza())
                    .orElse(null);
            if (pizza == null) {
                return false;
            }
        }

        for (BebidaPedida bebidaPedida : pedidoDto.getBebidaPedidos()) {
            Bebida bebida = bebidaRepository.findById(bebidaPedida.getBebida().getIdBebida())
                    .orElse(null);
            if (bebida == null) {
                return false;
            }
        }

        return true;
    }

}
