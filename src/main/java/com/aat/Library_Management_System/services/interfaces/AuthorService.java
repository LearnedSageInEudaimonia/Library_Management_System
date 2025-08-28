package com.aat.Library_Management_System.services.interfaces;

import com.aat.Library_Management_System.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {
    AuthorDTO create(AuthorDTO authorDTO);
    AuthorDTO getById(Long id);
    List<AuthorDTO> getAllAuthors();
    AuthorDTO update(Long id, AuthorDTO authorDTO);
    void delete(Long id);
    List<AuthorDTO> searchByName(String name);
    List<AuthorDTO> findTopAuthorsBasedOnBorrowCount(int limit);

}
