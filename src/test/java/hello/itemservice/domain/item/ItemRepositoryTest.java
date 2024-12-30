package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Item item = new Item("ItemA",10000,10);

        // when
        Item savedItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(savedItem.getId());
        Assertions.assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        // given
        Item item1 = new Item("ItemA",10000,10);
        Item item2 = new Item("Itemb",20000,20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        // when
        List<Item> result = itemRepository.findAll();

        // then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(item1,item2);
    }

    @Test
    void updateItem() {
        // given
        Item item = new Item("ItemA",10000,10);
        Item savedItem = itemRepository.save(item);

        // when
        Item updatedItem = new Item("ItemA",30000, 30);
        itemRepository.update(savedItem.getId(),updatedItem);

        Item findItem = itemRepository.findById(savedItem.getId());

        // then
        Assertions.assertThat(findItem.getItemName()).isEqualTo(updatedItem.getItemName());
        Assertions.assertThat(findItem.getPrice()).isEqualTo(updatedItem.getPrice());
        Assertions.assertThat(findItem.getQuantity()).isEqualTo(updatedItem.getQuantity());
    }
}