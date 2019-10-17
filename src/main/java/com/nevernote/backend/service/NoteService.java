package com.nevernote.backend.service;

import com.nevernote.backend.exception.ResourceNotFoundException;
import com.nevernote.backend.model.Note;
import com.nevernote.backend.model.Notebook;
import com.nevernote.backend.repository.NoteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
public class NoteService {

    private NoteRepository noteRepository;
    private NotebookService notebookService;


    @Autowired
    public NoteService(NoteRepository noteRepository, NotebookService notebookService){
        Assert.notNull(noteRepository, "NoteRepository must not be null!");
        this.noteRepository = noteRepository;
        this.notebookService = notebookService;
    }

    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    public ResponseEntity<List<Note>> getNotesByNotebookId(Long notebookId) {
        List<Note> notes = findNotesByNotebookId(notebookId);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    public ResponseEntity<Note> createNote(Note note) {
        if(null != note.getTitle() && note.getTitle().length() > 0) {
            note.setCreationDate(new Date());
            Note newNote = noteRepository.saveAndFlush(note);
            return new ResponseEntity<>(newNote, HttpStatus.CREATED);
        } else {
            throw new ResourceNotFoundException("Note", "id", note.getId());
        }
    }

    public ResponseEntity<Note> deleteNote(Long id) throws ResourceNotFoundException {
        noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        noteRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Note> updateNote(Long id, Note noteEdit) {
        Note existingNote = findNoteById(id);

        if(null != noteEdit.getTitle() && noteEdit.getTitle().length() > 0) {
            BeanUtils.copyProperties(noteEdit, existingNote);

            // Ensure ID remains unchanged
            existingNote.setId(id);
            existingNote.setLastModificationDate(new Date());

            Note updatedNote = noteRepository.saveAndFlush(existingNote);
            return new ResponseEntity<>(updatedNote, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Note", "id", id);
        }
    }

    public ResponseEntity<Note> getNoteById(Long id) {
        Note getNote = findNoteById(id);
        return new ResponseEntity<>(getNote, HttpStatus.OK);
    }

    private Note findNoteById(Long id) {
        return noteRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
    }

    public ResponseEntity<Note> addNoteToNotebook(Long noteId, Long notebookId) {
        Note note = findNoteById(noteId);
        Notebook notebook = notebookService.findNotebookById(notebookId);

        note.setNotebook(notebook);
        notebook.setLastModificationDate(new Date());
        updateNote(note.getId(), note);

        return new ResponseEntity<Note>(note, HttpStatus.OK);
    }

    private List<Note> findNotesByNotebookId(Long id) {
        return noteRepository.findByNotebookId(id);
    }
}
