package com.nevernote.backend.controller;

import com.nevernote.backend.model.Note;
import com.nevernote.backend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

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
