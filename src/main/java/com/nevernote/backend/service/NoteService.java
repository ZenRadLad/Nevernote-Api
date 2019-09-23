package com.nevernote.backend.service;

import com.nevernote.backend.exception.ResourceNotFoundException;
import com.nevernote.backend.model.Note;
import com.nevernote.backend.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoteService {

    private final Logger logger = LoggerFactory.getLogger(NoteService.class);

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public void createNote(Note note) {
        note.setCreationDate(new Date());
        noteRepository.save(note);
        logger.info("Note created succesfuly : {}", note);
    }

    public void deleteNote(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        noteRepository.deleteById(id);
        logger.info("Note deleted succesfuly.");
    }

    public void updateNote(Long id, Note noteDetails) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        note.setLastModificationDate(new Date());
        noteRepository.save(note);
        logger.info("Note updated succesfuly.");
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
    }
}
