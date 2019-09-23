package com.nevernote.backend.controller;

import com.nevernote.backend.model.Note;
import com.nevernote.backend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("/notes")
    public List<Note> getAllNotes(){
        return noteService.getAllNotes();
    }

    @PostMapping("/notes")
    public void createNote(@RequestBody  Note note){
        noteService.createNote(note);
    }

    @PutMapping("/notes/{id}")
    public void updateNote(@PathVariable Long id, @RequestBody  Note note){
        noteService.updateNote(id, note);
    }

    @DeleteMapping("/notes/{id}")
    public void deleteNote(@PathVariable Long id){
        noteService.deleteNote(id);
    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable Long id){
        return noteService.getNoteById(id);
    }
}
