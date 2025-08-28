package com.aat.Library_Management_System.services.interfaces;

import com.aat.Library_Management_System.dto.InventoryCopyDTO;
import java.util.List;

public interface InventoryCopyService {
    InventoryCopyDTO createInventoryCopy(InventoryCopyDTO dto);
    InventoryCopyDTO getInventoryCopyById(Long id);
    List<InventoryCopyDTO> getAllInventoryCopies();
    InventoryCopyDTO updateInventoryCopy(Long id, InventoryCopyDTO dto);
    void deleteInventoryCopy(Long id);
}
