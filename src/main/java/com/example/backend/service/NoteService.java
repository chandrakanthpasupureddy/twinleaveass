package com.example.backend.service;

import com.example.backend.model.Note;
import com.example.backend.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getAllNotes(String category, String search) {
        if (category != null && !category.isEmpty()) {
            return noteRepository.findByCategory(category);
        } else if (search != null && !search.isEmpty()) {
            return noteRepository.findByTitleContainingIgnoreCase(search);
        }
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public Note updateNote(Long id, Note noteDetails) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        note.setTitle(noteDetails.getTitle());
        note.setDescription(noteDetails.getDescription());
        note.setCategory(noteDetails.getCategory());
        return noteRepository.save(note);
    }

    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }
}
