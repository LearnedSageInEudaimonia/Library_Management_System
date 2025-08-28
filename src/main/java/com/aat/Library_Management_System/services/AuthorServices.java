package com.aat.Library_Management_System.services;

import com.aat.Library_Management_System.dto.AuthorDTO;
import com.aat.Library_Management_System.entities.*;
import com.aat.Library_Management_System.exceptions.NotFoundException;
import com.aat.Library_Management_System.mappers.AuthorMapper;
import com.aat.Library_Management_System.repository.AuthorRepository;
import com.aat.Library_Management_System.services.interfaces.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorServices implements AuthorService {

    private final AuthorRepository authorRepository;


    @Override
    public AuthorDTO create(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setBiography(authorDTO.getBiography());
        author.setBirthYear(authorDTO.getBirthYear());
        Author savedAuthor = authorRepository.save(author);
        return authorDTO;
    }

    @Override
    public AuthorDTO getById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(STR."Author not found with id: \{id}"));
        return AuthorMapper.toDTO(author);
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(AuthorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTO update(Long id, AuthorDTO authorDTO) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(STR."Author not found with id: \{id}"));
        
        existingAuthor.setName(authorDTO.getName());
        existingAuthor.setBiography(authorDTO.getBiography());
        existingAuthor.setBirthYear(authorDTO.getBirthYear());
        
        Author updatedAuthor = authorRepository.save(existingAuthor);
        return AuthorMapper.toDTO(updatedAuthor);
    }

    @Override
    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new NotFoundException(STR."Author not found with id: \{id}");
        }
        authorRepository.deleteById(id);
    }

    @Override
    public List<AuthorDTO> searchByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name).stream()
                .map(AuthorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuthorDTO> findTopAuthorsBasedOnBorrowCount(int limit){
        return authorRepository.findTopAuthorsByBorrowCount().stream().limit(limit).map(AuthorMapper::toDTO).collect(Collectors.toList());
    }

}
