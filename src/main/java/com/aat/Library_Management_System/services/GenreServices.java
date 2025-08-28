package com.aat.Library_Management_System.services;

import com.aat.Library_Management_System.dto.GenreDTO;
import com.aat.Library_Management_System.entities.Book;
import com.aat.Library_Management_System.entities.Genre;
import com.aat.Library_Management_System.exceptions.NotFoundException;
import com.aat.Library_Management_System.mappers.GenreMapper;
import com.aat.Library_Management_System.repository.BookRepository;
import com.aat.Library_Management_System.repository.GenreRepository;
import com.aat.Library_Management_System.services.interfaces.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServices implements GenreService {

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @Override
    public GenreDTO create(GenreDTO dto) {
        Genre genre = Genre.builder()
                .name(dto.getName())
                .build();

        Genre saved = genreRepository.save(genre);
        return GenreMapper.toDTO(saved);
    }

    @Override
    public GenreDTO update(Long id, GenreDTO dto) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(STR."Genre not found with id \{id}"));
        genre.setName(dto.getName());
        Genre updated = genreRepository.save(genre);
        return GenreMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        if (!genreRepository.existsById(id)) {
            throw new NotFoundException(STR."Genre not found with id \{id}");
        }
        genreRepository.deleteById(id);
    }

    @Override
    public GenreDTO getById(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(STR."Genre not found with id \{id}"));
        return GenreMapper.toDTO(genre);
    }

    @Override
    public List<GenreDTO> getAll() {
        return genreRepository.findAll().stream()
                .map(GenreMapper::toDTO)
                .collect(Collectors.toList());
    }
}
