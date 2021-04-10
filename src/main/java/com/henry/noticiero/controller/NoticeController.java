package com.henry.noticiero.controller;

import com.henry.noticiero.model.Notice;
import com.henry.noticiero.service.NoticeService;
import com.henry.noticiero.utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping
    public PostResponse addNotice(@RequestBody Notice notice) {
        return noticeService.addNotice(notice);
    }

    @GetMapping
    public List<Notice> getAll(){
        return noticeService.getAll();
    }

    @GetMapping("/{id}")
    public Notice findById(@PathVariable Integer id){
        return noticeService.findById(id);
    }

    @PutMapping()
    public String editNotice(@RequestBody Notice notice){
        return noticeService.editNotice(notice);
    }

    @DeleteMapping("/{id}")
    public Notice deleteById(@PathVariable Integer id){
        return noticeService.deleteById(id);
    }

    @GetMapping("/writer/{id}")
    public List<Notice> findByWriter(@PathVariable Integer id) { return noticeService.findByWriter(id);}
}
