package lab9.controller;

import lab9.model.Deed;
import lab9.service.DeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeedController {
    private final DeedService deedService;

    @Autowired
    public DeedController(DeedService deedService) {
        this.deedService = deedService;
    }

    //создание нового экземпляра
    @PostMapping(value = "/deeds")
    public ResponseEntity<?> create(@RequestBody Deed deed) {
        deedService.create(deed);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //получение списка всех экземпляров
    @GetMapping(value = "/deeds")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Deed>>read() {
        final List<Deed> deeds = deedService.readAll();
        return deeds != null &&  !deeds.isEmpty()
                ? new ResponseEntity<>(deeds, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //получение экземпляра по его id
    @GetMapping(value = "/deeds/{id}")
    public ResponseEntity<Deed> read(@PathVariable(name = "id") int id) {
        final Deed deed = deedService.read(id);

        return deed != null
                ? new ResponseEntity<>(deed, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //изменение (обновление) экземпляра
    @PutMapping(value = "/deeds/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Deed deed) {
        final boolean updated = deedService.update(deed, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    //удаление экземпляра
    @DeleteMapping(value = "/deeds/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = deedService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
