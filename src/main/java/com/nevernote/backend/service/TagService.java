package com.nevernote.backend.service;

import com.nevernote.backend.exception.ResourceNotFoundException;
import com.nevernote.backend.model.Tag;
import com.nevernote.backend.repository.TagRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class TagService {

    private TagRepository tagRepository;


    @Autowired
    public TagService(TagRepository tagRepository){
        Assert.notNull(tagRepository, "TagRepository must not be null!");
        this.tagRepository = tagRepository;
    }

    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    public ResponseEntity<Tag> createTag(Tag tag) {
        if(null != tag.getName() && tag.getName().length() > 0) {
            Tag newTag = tagRepository.saveAndFlush(tag);
            return new ResponseEntity<>(newTag, HttpStatus.CREATED);
        } else {
            throw new ResourceNotFoundException("Tag", "id", tag.getId());
        }
    }

    public ResponseEntity<Tag> deleteTag(Long id) throws ResourceNotFoundException {
        tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "id", id));
        tagRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Tag> updateTag(Long id, Tag tagEdit) {
        Tag existingTag = findTagById(id);

        if(null != tagEdit.getName() && tagEdit.getName().length() > 0) {
            BeanUtils.copyProperties(tagEdit, existingTag);

            // Ensure ID remains unchanged
            existingTag.setId(id);

            Tag updatedTag = tagRepository.saveAndFlush(existingTag);
            return new ResponseEntity<>(updatedTag, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Tag", "id", id);
        }
    }

    public ResponseEntity<Tag> getTagById(Long id) {
        Tag getTag = findTagById(id);
        return new ResponseEntity<>(getTag, HttpStatus.OK);
    }

    private Tag findTagById(Long id) {
        return tagRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Tag", "id", id));
    }

}
