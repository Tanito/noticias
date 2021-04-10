package com.henry.noticiero.controller;

import com.henry.noticiero.model.Writer;
import com.henry.noticiero.service.WriterService;
import com.henry.noticiero.utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/writer")
public class WriterController {

    @Autowired
    private WriterService writerService;

    @GetMapping("/{id}")
    public Writer getWriter(@PathVariable Integer id) {
        return writerService.getWriter(id);
    }

    @GetMapping
    public List<Writer> getAll(){
        return writerService.getAll();
    }

    @PostMapping
    public PostResponse addWriter(@RequestBody Writer writer) {
        return writerService.addWriter(writer);
    }

    @PutMapping("/{id}/notice/{noticiaID}")
    private void addNoticia(@PathVariable Integer id, @PathVariable Integer noticiaID) {
        writerService.addNoticia(id, noticiaID);
    }

}