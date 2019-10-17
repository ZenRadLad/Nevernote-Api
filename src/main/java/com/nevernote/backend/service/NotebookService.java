package com.nevernote.backend.service;

import com.nevernote.backend.exception.ResourceNotFoundException;
import com.nevernote.backend.model.Notebook;
import com.nevernote.backend.repository.NotebookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
public class NotebookService {

    private NotebookRepository notebookRepository;

    @Autowired
    public NotebookService(NotebookRepository notebookRepository) {
        Assert.notNull(notebookRepository, "NotebookRepository must not be null!");
        this.notebookRepository = notebookRepository;
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

    public Notebook findNotebookById(Long id) {
        return notebookRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Notebook", "id", id));
    }
}
