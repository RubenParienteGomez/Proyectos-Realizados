package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services;

import java.util.List;

import org.springframework.stereotype.Service;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories.MarcaRepository;

@Service
public class MarcaServiceImpl implements MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaServiceImpl(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @Override
    public List<Marca> buscarTodos() {
        return marcaRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Marca buscarId(Long id) {
        return marcaRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Marca guardar(Marca marca) {

        // Validacion nombre obligatorio
        if (marca.getNombre() == null || marca.getNombre().isBlank()) {
            throw new RuntimeException("El nombre de la marca es obligatorio");
        }

        Marca existente = marcaRepository.findByNombre(marca.getNombre()).orElse(null);

        if (existente != null) {
            if (marca.getId() == null || !existente.getId().equals(marca.getId())) {
                throw new RuntimeException("Ya existe una marca con ese nombre");
            }
        }

        return marcaRepository.save(marca);
    }

    @Override
    public void eliminar(Long id) {
        marcaRepository.deleteById(id);
    }
}