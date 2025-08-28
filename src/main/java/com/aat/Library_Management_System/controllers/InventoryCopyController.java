package com.aat.Library_Management_System.controllers;

import com.aat.Library_Management_System.dto.InventoryCopyDTO;
import com.aat.Library_Management_System.services.InventoryCopyServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory-copies")
@RequiredArgsConstructor
public class InventoryCopyController {

    private final InventoryCopyServices inventoryCopyService;

    @PostMapping
    public ResponseEntity<InventoryCopyDTO> create(@RequestBody InventoryCopyDTO dto) {
        return ResponseEntity.ok(inventoryCopyService.createInventoryCopy(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryCopyDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryCopyService.getInventoryCopyById(id));
    }

    @GetMapping
    public ResponseEntity<List<InventoryCopyDTO>> getAll() {
        return ResponseEntity.ok(inventoryCopyService.getAllInventoryCopies());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryCopyDTO> update(@PathVariable Long id, @RequestBody InventoryCopyDTO dto) {
        return ResponseEntity.ok(inventoryCopyService.updateInventoryCopy(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventoryCopyService.deleteInventoryCopy(id);
        return ResponseEntity.noContent().build();
    }
}
