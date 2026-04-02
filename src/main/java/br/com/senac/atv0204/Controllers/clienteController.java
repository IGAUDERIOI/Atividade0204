package br.com.senac.atv0204.Controllers;

import br.com.senac.atv0204.DTOS.clienteDTO;
import br.com.senac.atv0204.Entidades.Clientes;
import br.com.senac.atv0204.Repositorio.clienteRepositorio;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/cliente")
public class clienteController {

    private clienteRepositorio clienteRepositorio;

    public clienteController(clienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Clientes>> listar(){
        List<Clientes> clientesList = clienteRepositorio.findAll();
        if (clientesList.isEmpty()){
            return ResponseEntity.status(204).body(null);
        }
        return  ResponseEntity.ok(clientesList);
    }

    @PostMapping("/criar")
    public ResponseEntity<Clientes> criar(
            @RequestBody clienteDTO clientes){

        Clientes clientesPersist = new Clientes();
        clientesPersist.setNome(clientes.getNome());
        clientesPersist.setEmail(clientes.getEmail());
        clientesPersist.setIdade(clientes.getIdade());
        clientesPersist.setDocumento(clientes.getDocumento());

        Clientes retorno = clienteRepositorio.save(clientesPersist);

        return ResponseEntity.status(201).body(retorno);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Clientes> atualizar(
            @RequestBody clienteDTO clientes, @PathVariable Long id){

        if (clienteRepositorio.existsById(id)){
            Clientes clientesPersist = new Clientes();
            clientesPersist.setNome(clientes.getNome());
            clientesPersist.setEmail(clientes.getEmail());
            clientesPersist.setIdade(clientes.getIdade());
            clientesPersist.setDocumento(clientes.getDocumento());
            clientesPersist.setId(id);

            Clientes retorno = clienteRepositorio.save(clientesPersist);

            return ResponseEntity.ok(retorno);
        }
        return  ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Clientes> deletar(
            @PathVariable Long id){
        if (clienteRepositorio.existsById(id)){
            clienteRepositorio.deleteById(id);

            return ResponseEntity.ok(null);
        }
        return  ResponseEntity.badRequest().body(null);
    }

}

