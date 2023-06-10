package com.neutech.mammalia.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neutech.mammalia.bean.Response;
import com.neutech.mammalia.bean.Species;
import com.neutech.mammalia.service.SpeciesService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/species")
public class SpeciesController {
    @Resource
    private SpeciesService speciesService;

    @PostMapping
    public ResponseEntity<Response> addSpecies(@RequestBody Species species) {
        if (speciesService.addSpecies(species) == 1)
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response(HttpStatus.CREATED, "添加成功"));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(HttpStatus.BAD_REQUEST, "添加失败"));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deleteSpeciesById(@PathVariable Integer id) {
        if (speciesService.deleteSpeciesById(id) == 1)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{speciesId}")
    public ResponseEntity<Response> updateSpeciesById(@PathVariable Integer speciesId, @RequestBody Species species) {
        species.setId(speciesId);
        if (speciesService.updateSpeciesById(species) == 1)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(HttpStatus.BAD_REQUEST));
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Response> inquireSpeciesById(@PathVariable Integer id) {
        Species species = speciesService.inquireSpeciesById(id);
        if (species != null)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, species));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));

    }

    @GetMapping
    public ResponseEntity<Response> inquireAllSpecies(Page<Integer> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Species> species = speciesService.inquireAllSpecies();
        PageInfo<Species> speciesPageInfo = new PageInfo<>(species);
        if (species.size() > 0)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, speciesPageInfo));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/blur")
    public ResponseEntity<Response> inquireSpeciesByBlur(String keyword, String inheritance, Page<Integer> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Species> species = speciesService.inquireSpeciesByKeyword(keyword, inheritance);
        PageInfo<Species> speciesPageInfo = new PageInfo<>(species);
        if (species.size() > 0)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, speciesPageInfo));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/recommends")
    public ResponseEntity<Response> inquireSomeSpecies() {
        Set<Species> species = speciesService.inquireSomeSpecies();
        if (species.size() > 0)
            return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, species));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(HttpStatus.NOT_FOUND));
    }

}
