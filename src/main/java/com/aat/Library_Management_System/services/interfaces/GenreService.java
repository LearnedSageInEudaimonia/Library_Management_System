package com.aat.Library_Management_System.services.interfaces;

import com.aat.Library_Management_System.dto.GenreDTO;

import java.util.List;

public interface GenreService {
    GenreDTO create(GenreDTO dto);
    GenreDTO update(Long id, GenreDTO dto);
    void delete(Long id);
    GenreDTO getById(Long id);
    List<GenreDTO> getAll();
}
