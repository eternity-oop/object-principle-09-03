package org.eternity.adventure.game;

import org.eternity.adventure.game.item.Inventory;
import org.eternity.adventure.game.item.Item;
import org.eternity.adventure.game.worldmap.Position;
import org.eternity.adventure.game.worldmap.Room;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DestroyTest {
    @Test
    public void destroy_is_possible() {
        assertThat(
            new Destroy(
                new Room(Position.of(0,0), "", "", new Inventory(new Item("item"))),
                new Room(Position.of(0,0), "", "", new Inventory(new Item("item"))),
                "item")
                .isPossible())
            .isTrue();

        assertThat(
                new Destroy(
                        new Room(Position.of(0,0), "", "", new Inventory(new Item("item"))),
                        new Room(Position.of(0,0), "", ""),
                        "item")
                        .isPossible())
                .isTrue();

        assertThat(
                new Destroy(
                        new Room(Position.of(0,0), "", ""),
                        new Room(Position.of(0,0), "", "", new Inventory(new Item("item"))),
                        "item")
                        .isPossible())
                .isTrue();

        assertThat(
                new Destroy(
                        new Room(Position.of(0,0), "", ""),
                        new Room(Position.of(0,0), "", ""),
                        "item")
                        .isPossible())
                .isFalse();
    }

    @Test
    public void destroy() {
        Room first = new Room(Position.of(0,0), "", "", new Inventory(new Item("item")));
        Room second = new Room(Position.of(0,0), "", "");

        Destroy destroy = new Destroy(first, second, "item");
        destroy.perform();

        assertThat(first.find("item")).isEmpty();
        assertThat(second.find("item")).isEmpty();
    }
}
