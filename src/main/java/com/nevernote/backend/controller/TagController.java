package com.nevernote.backend.controller;

import com.nevernote.backend.model.Tag;
import com.nevernote.backend.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping()
    public ResponseEntity<List<Tag>> getAllTags(){
        return tagService.getAllTags();
    }

    @PostMapping()
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag){
        return tagService.createTag(tag);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Long id, @RequestBody  Tag tag){
        return tagService.updateTag(id, tag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tag> deleteTag(@PathVariable Long id){
        return tagService.deleteTag(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id){
        return tagService.getTagById(id);
    }
}
