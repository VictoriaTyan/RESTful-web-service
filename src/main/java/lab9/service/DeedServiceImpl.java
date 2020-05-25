package lab9.service;

import lab9.model.Deed;
import lab9.repository.DeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeedServiceImpl implements DeedService {
    @Autowired
    private DeedRepository deedRepository;

    public void create(Deed deed) {
        deedRepository.save(deed);
    }

    @Override
    public List<Deed> readAll() {
        return deedRepository.findAll();
    }

    @Override
    public Deed read(int id) {
        if (deedRepository.existsById(id)) {
            return deedRepository.getOne(id);
        }
        return null;
    }

    @Override
    public boolean update(Deed task, int id) {
        if (deedRepository.existsById(id))
        {
            task.setId(id);
            deedRepository.save(task);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (deedRepository.existsById(id)) {
            deedRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
