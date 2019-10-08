package com.nevernote.backend.controller;

import com.nevernote.backend.model.Notebook;
import com.nevernote.backend.service.NotebookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotebookController {

    private NotebookService notebookService;

    public NotebookController(NotebookService notebookService){
        this.notebookService = notebookService;
    }

    @GetMapping("/notebooks")
    public ResponseEntity<List<Notebook>> getAllNotebooks(){ return notebookService.getAllNotebooks(); }

    @PostMapping("/notebooks")
    public ResponseEntity<Notebook> createNotebook(@RequestBody Notebook notebook){
        return notebookService.createNotebook(notebook);
    }

    @PostMapping("/notebooks/{notebookId}/{noteId}")
    public ResponseEntity<Notebook> addNoteToNotebook(@PathVariable Long noteId, @PathVariable Long notebookId){
        return notebookService.addNoteToNotebook(noteId, notebookId);
    }

    @PutMapping("/notebooks/{id}")
    public ResponseEntity<Notebook> updateNotebook(@PathVariable Long id, @RequestBody Notebook notebook){
        return notebookService.updateNotebook(id, notebook);
    }

    @DeleteMapping("/notebooks/{id}")
    public ResponseEntity<Notebook> deleteNotebook(@PathVariable Long id){
        return notebookService.deleteNotebook(id);
    }

    @GetMapping("/notebooks/{id}")
    public ResponseEntity<Notebook> getNotebookById(@PathVariable Long id){
        return notebookService.getNotebookById(id);
    }
}
