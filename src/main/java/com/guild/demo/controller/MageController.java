package com.guild.demo.controller;

import com.guild.demo.dto.MageDto;
import com.guild.demo.entity.Mage;
import com.guild.demo.repository.MageRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mages")
public class MageController {

    private final MageRepository repository;

    public MageController(MageRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Mage> getAllMages() {
        return repository.findAll();
    }

    @PostMapping
    public Mage createMage(@Valid @RequestBody MageDto dto) {
        Mage mage = new Mage();
        mage.setName(dto.getName());
        mage.setRank(dto.getRank());
        mage.setManaPool(dto.getManaPool());
        
        return repository.save(mage);
    }

    @PutMapping("/{id}")
    public Mage updateMage(@PathVariable Integer id, @Valid @RequestBody MageDto dto) {
        return repository.findById(id).map(mage -> {
            mage.setName(dto.getName());
            mage.setRank(dto.getRank());
            mage.setManaPool(dto.getManaPool());
            return repository.save(mage); 
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteMage(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}