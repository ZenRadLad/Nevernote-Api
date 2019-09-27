package com.nevernote.backend.service;

import com.nevernote.backend.exception.ResourceNotFoundException;
import com.nevernote.backend.model.Note;
import com.nevernote.backend.model.Notebook;
import com.nevernote.backend.repository.NotebookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NotebookService {

    private final Logger logger = LoggerFactory.getLogger(NoteService.class);

    private NotebookRepository notebookRepository;
    private NoteService noteService;

    public NotebookService(NotebookRepository notebookRepository, NoteService noteService) {
        this.notebookRepository = notebookRepository;
        this.noteService = noteService;
    }

    public List<Notebook> getAllNotebooks() { return notebookRepository.findAll(); }

    public void createNotebook(Notebook notebook) {
        notebook.setCreationDate(new Date());
        notebookRepository.save(notebook);
    }

    public void updateNotebook(Long id, Notebook notebookDetails) {
        Notebook notebook = notebookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notebook", "id", id));
        notebook.setName(notebookDetails.getName());
        notebook.setLastModificationDate(new Date());
        notebookRepository.save(notebook);
        logger.info("Notebook updated successfully.");
    }

    public void deleteNotebook(Long id) {
        Notebook notebook = notebookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notebook", "id", id));
        notebookRepository.deleteById(id);
        logger.info("Notebook deleted successfully.");
    }

    public Notebook getNotebookById(Long id) {
        return notebookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notebook", "id", id));
    }

    public void addNoteToNotebook(Long noteId, Long notebookId) {
        Note note = noteService.getNoteById(noteId);
        Notebook notebook = getNotebookById(notebookId);
        Set<Note> notes = new HashSet<>();
        notes.add(note);
        notebook.setNotes(notes);
        updateNotebook(notebook.getId(), notebook);
        logger.info("Note {} added to Notebook {} successfully.", note.toString(), notebook.toString());
    }
}
