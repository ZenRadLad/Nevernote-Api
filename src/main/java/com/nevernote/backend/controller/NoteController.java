package com.nevernote.backend.controller;

import com.nevernote.backend.model.Note;
import com.nevernote.backend.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotes(){
        return noteService.getAllNotes();
    }

    @PostMapping("/notes")
    public ResponseEntity<Note> createNote(@RequestBody  Note note){
        return noteService.createNote(note);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody  Note note){
        return noteService.updateNote(id, note);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable Long id){
        return noteService.deleteNote(id);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id){
        return noteService.getNoteById(id);
    }
}
