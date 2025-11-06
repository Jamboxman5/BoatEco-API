package me.jamboxman5.boateco.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jakarta.servlet.http.HttpServletRequest;
import me.jamboxman5.boateco.model.Entry;
import me.jamboxman5.boateco.repository.EntryRepository;
import me.jamboxman5.boateco.dao.EntryDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/entry")
public class EntryController {

    private final EntryDAO entryDAO;

    public EntryController(EntryRepository entryDao) {
        this.entryDAO = entryDao.getDao();
    }

    @GetMapping("/get/{plate}")
    public ResponseEntity<String> getEntriesForPlate(HttpServletRequest request, @PathVariable("plate") String plate) throws JsonProcessingException {

        List<Entry> entries = entryDAO.findByPlate(plate);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ResponseEntity.ok(ow.writeValueAsString(entries));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEntry(@RequestBody Entry entry) throws JsonProcessingException {

        entryDAO.add(
                entry.getMonth(),
                entry.getDay(),
                entry.getYear(),
                entry.getMiles(),
                entry.getGallons(),
                entry.getCost(),
                entry.getPlate()
                );

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ResponseEntity.ok(ow.writeValueAsString(entryDAO.findByPlate(entry.getPlate())));
    }

    @DeleteMapping("/delete/{plate}/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable("plate") String plate, @PathVariable("id") String id) throws JsonProcessingException {

        entryDAO.delete(UUID.fromString(id));

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ResponseEntity.ok(ow.writeValueAsString(entryDAO.findByPlate(plate)));
    }

    @PutMapping("/edit")
    public ResponseEntity<String> updateEntry(@RequestBody Entry entry) throws JsonProcessingException {

        entry.updateEntry(entryDAO);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ResponseEntity.ok(ow.writeValueAsString(entryDAO.findByPlate(entry.getPlate())));
    }
}


