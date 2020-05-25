package lab9.service;

import lab9.model.Deed;

import java.util.List;

public interface DeedService {
    /**
     * Создает новый пункт (дело)
     * @param deed - пункт (дело) для создания
     */
    void create(Deed deed);

    /**
     * Возвращает список всех имеющихся дел
     * @return список дел
     */
    List<Deed> readAll();

    /**
     * Возвращает пункт (дело) по его ID
     * @param id - ID дела
     * @return - объект дела с заданным ID
     */
    Deed read(int id);

    /**
     * Обновляет дело с заданным ID,
     * в соответствии с переданным делом
     * @param deed - дело в соответсвии с которым нужно обновить данные
     * @param id - id дела которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(Deed deed, int id);

    /**
     * Удаляет дело с заданным ID
     * @param id - id дела, которое нужно удалить
     * @return - true если дело было удалено, иначе false
     */
    boolean delete(int id);
}
