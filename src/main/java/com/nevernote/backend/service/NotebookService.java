package com.nevernote.backend.service;

import com.nevernote.backend.exception.ResourceNotFoundException;
import com.nevernote.backend.model.Note;
import com.nevernote.backend.model.Notebook;
import com.nevernote.backend.repository.NotebookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NotebookService {

    private NotebookRepository notebookRepository;
    private NoteService noteService;

    @Autowired
    public NotebookService(NotebookRepository notebookRepository, NoteService noteService) {
        Assert.notNull(notebookRepository, "NotebookRepository must not be null!");
        this.notebookRepository = notebookRepository;
        this.noteService = noteService;
    }

    public  ResponseEntity<List<Notebook>> getAllNotebooks() {
        List<Notebook> notebooks = notebookRepository.findAll();
        return new ResponseEntity<>(notebooks, HttpStatus.OK);
    }

    public  ResponseEntity<Notebook> createNotebook(Notebook notebook) {
        if(null != notebook.getName() && notebook.getName().length() > 0) {
            notebook.setCreationDate(new Date());
            Notebook newNotebook = notebookRepository.saveAndFlush(notebook);
            return new ResponseEntity<>(newNotebook, HttpStatus.CREATED);
        } else {
            throw new ResourceNotFoundException("Notebook", "id", notebook.getId());
        }
    }

    public ResponseEntity<Notebook> updateNotebook(Long id, Notebook notebookEdit) {
        Notebook existingNotebook = findNotebookById(id);

        if(null != notebookEdit.getName() && notebookEdit.getName().length() > 0) {
            BeanUtils.copyProperties(notebookEdit, existingNotebook);

            // Ensure ID remains unchanged
            existingNotebook.setId(id);
            existingNotebook.setLastModificationDate(new Date());

            Notebook updatedNotebook = notebookRepository.saveAndFlush(existingNotebook);
            return new ResponseEntity<>(updatedNotebook, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Notebook", "id", id);
        }
    }

    public ResponseEntity<Notebook> deleteNotebook(Long id) {
        notebookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notebook", "id", id));
        notebookRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Notebook> getNotebookById(Long id) {
        Notebook getNotebook = findNotebookById(id);
        return new ResponseEntity<>(getNotebook, HttpStatus.OK);
    }

    private Notebook findNotebookById(Long id) {
        return notebookRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Notebook", "id", id));
    }


    public ResponseEntity<Notebook> addNoteToNotebook(Long noteId, Long notebookId) {
        Note note = noteService.findNoteById(noteId);
        Notebook notebook = findNotebookById(notebookId);
        Set<Note> notes = new HashSet<>();
        notes.add(note);
        notebook.setNotes(notes);
        updateNotebook(notebook.getId(), notebook);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
