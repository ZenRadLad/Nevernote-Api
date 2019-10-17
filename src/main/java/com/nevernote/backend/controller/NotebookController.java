package com.nevernote.backend.controller;

import com.nevernote.backend.model.Note;
import com.nevernote.backend.model.Notebook;
import com.nevernote.backend.service.NoteService;
import com.nevernote.backend.service.NotebookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notebooks")
public class NotebookController {

    private NotebookService notebookService;
    private NoteService noteService;

    public NotebookController(NotebookService notebookService, NoteService noteService){
        this.notebookService = notebookService;
        this.noteService = noteService;
    }

    @GetMapping()
    public ResponseEntity<List<Notebook>> getAllNotebooks(){ return notebookService.getAllNotebooks(); }

    @PostMapping()
    public ResponseEntity<Notebook> createNotebook(@RequestBody Notebook notebook){
        return notebookService.createNotebook(notebook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notebook> updateNotebook(@PathVariable Long id, @RequestBody Notebook notebook){
        return notebookService.updateNotebook(id, notebook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Notebook> deleteNotebook(@PathVariable Long id){
        return notebookService.deleteNotebook(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notebook> getNotebookById(@PathVariable Long id){
        return notebookService.getNotebookById(id);
    }

    @GetMapping("/{id}/notes")
    public ResponseEntity<List<Note>> getNotesByNotebookId(@PathVariable Long id){
        return noteService.getNotesByNotebookId(id);
    }
}
