package com.nevernote.backend.controller;

import com.nevernote.backend.model.Notebook;
import com.nevernote.backend.service.NotebookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotebookController {

    private NotebookService notebookService;

    public NotebookController(NotebookService notebookService){
        this.notebookService = notebookService;
    }

    @GetMapping("/notebooks")
    public List<Notebook> getAllNotebooks(){ return notebookService.getAllNotebooks(); }

    @PostMapping("/notebooks")
    public void createNotebook(@RequestBody Notebook notebook){
        notebookService.createNotebook(notebook);
    }

    @PostMapping("/notebooks/{notebookId}/{noteId}")
    public void addNoteToNotebook(@PathVariable Long noteId, @PathVariable Long notebookId){
        notebookService.addNoteToNotebook(noteId, notebookId);
    }

    @PutMapping("/notebooks/{id}")
    public void updateNotebook(@PathVariable Long id, @RequestBody  Notebook notebook){
        notebookService.updateNotebook(id, notebook);
    }

    @DeleteMapping("/notebooks/{id}")
    public void deleteNotebook(@PathVariable Long id){
        notebookService.deleteNotebook(id);
    }

    @GetMapping("/notebooks/{id}")
    public Notebook getNotebookById(@PathVariable Long id){
        return notebookService.getNotebookById(id);
    }
}
