package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    // 동시에 여러 쓰레드가 접근할 때는 HashMap 쓰면 안된다. ConcurrentHashMap 같은걸 써야한다.
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L; // 얘도 동시접근할 때는 long 쓰면 안된다.

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    // 정석: ItemDto 같은걸 만들어서 파라미터 세개만 만들어두는 것.
    public void update(Long itemId, Item updateParam) {
        Item item = store.get(itemId);
        item.setItemName(updateParam.getItemName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
 }
