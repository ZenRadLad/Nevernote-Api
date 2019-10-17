package com.nevernote.backend.controller;

import com.nevernote.backend.model.Note;
import com.nevernote.backend.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping()
    public ResponseEntity<List<Note>> getAllNotes(){
        return noteService.getAllNotes();
    }

    @PostMapping()
    public ResponseEntity<Note> createNote(@RequestBody  Note note){
        return noteService.createNote(note);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody  Note note){
        return noteService.updateNote(id, note);
    }

    @PostMapping("/{id}/notebooks/{notebookId}")
    public ResponseEntity<Note> addNoteToNotebook(@PathVariable Long id, @PathVariable Long notebookId){
        return noteService.addNoteToNotebook(id, notebookId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable Long id){
        return noteService.deleteNote(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id){
        return noteService.getNoteById(id);
    }
}
