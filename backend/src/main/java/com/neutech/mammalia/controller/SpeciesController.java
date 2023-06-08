package com.neutech.mammalia.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neutech.mammalia.bean.Response;
import com.neutech.mammalia.bean.Species;
import com.neutech.mammalia.service.SpeciesService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/species")
public class SpeciesController {
    @Resource
    private SpeciesService speciesService;

    @PostMapping
    public Response addSpecies(@RequestBody Species species) {
        Response response = new Response();
        if (speciesService.addSpecies(species) == 1) {
            response.setCode(HttpStatus.CREATED.value());
            response.setMessage("添加成功");
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("添加失败");
        }
        return response;
    }

    @DeleteMapping(value = "/{id}")
    public Response deleteSpeciesById(@PathVariable Integer id) {
        Response response = new Response();
        if (speciesService.deleteSpeciesById(id) == 1) {
            response.setCode(HttpStatus.NO_CONTENT.value());
            response.setMessage(HttpStatus.NO_CONTENT.getReasonPhrase());
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }

    @PutMapping(value = "/{speciesId}")
    public Response updateSpeciesById(@PathVariable Integer speciesId, @RequestBody Species species) {
        Response response = new Response();
        species.setId(speciesId);
        if (speciesService.updateSpeciesById(species) == 1) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return response;
    }


    @GetMapping(value = "/{id}")
    public Response inquireSpeciesById(@PathVariable Integer id) {
        Response response = new Response();
        Species species = speciesService.inquireSpeciesById(id);
        if (species != null) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setData(species);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }

    @GetMapping
    public Response inquireAllSpecies(Page<Integer> page) {
        Response response = new Response();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Species> species = speciesService.inquireAllSpecies();
        PageInfo<Species> speciesPageInfo = new PageInfo<>(species);
        if (species.size() > 0) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setData(speciesPageInfo);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }

    @GetMapping(value = "/blur")
    public Response inquireSpeciesByBlur(String keyword, String inheritance, Page<Integer> page) {
        Response response = new Response();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Species> species = speciesService.inquireSpeciesByKeyword(keyword, inheritance);
        PageInfo<Species> speciesPageInfo = new PageInfo<>(species);
        if (species.size() > 0) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setData(speciesPageInfo);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        return response;
    }
}
