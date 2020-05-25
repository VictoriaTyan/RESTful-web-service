package lab9.service;

import lab9.model.Deed;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DeedServiceImpl implements DeedService {
    // Хранилище дел
    private static final Map<Integer, Deed> DEED_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID клиента
    private static final AtomicInteger DEED_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Deed deed) {
        final int deedId = DEED_ID_HOLDER.incrementAndGet();
        deed.setId(deedId);
        DEED_REPOSITORY_MAP.put(deedId, deed);
    }

    @Override
    public List<Deed> readAll() {
        return new ArrayList<>(DEED_REPOSITORY_MAP.values());
    }

    @Override
    public Deed read(int id) {
        return DEED_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Deed deed, int id) {
        if (DEED_REPOSITORY_MAP.containsKey(id)) {
            deed.setId(id);
            DEED_REPOSITORY_MAP.put(id, deed);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return DEED_REPOSITORY_MAP.remove(id) != null;
    }
}
