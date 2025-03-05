package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void test_01_NomVide() {
        Item[] items = new Item[] { new Item("", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void test_02_NomVideQualiteNonNull() {
        Item[] items = new Item[] { new Item("", 0, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void test_03_NomVideQualiteSellInPos() {
        Item[] items = new Item[] { new Item("", 2, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("", app.items[0].name);
        assertEquals(1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void test_04_AgedBrie(){
        Item[] items = new Item[] { new Item("Aged Brie", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(2, app.items[0].quality);
    }

    @Test
    void test_05_AgedBrie49(){
        Item[] items = new Item[] { new Item("Aged Brie", 1, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void test_06_AgedBrie50(){
        Item[] items = new Item[] { new Item("Aged Brie", 0, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void test_07_TAFKAL80ETC(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert",
                0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void test_08_TAFKAL80ETCSellIn12(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert",
                12, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(11, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }

    @Test
    void test_09_TAFKAL80ETCSellIn10Qual49(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert",
                10, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void test_10_TAFKAL80ETCSellIn5Qual49(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert",
                5, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void test_11_TAFKAL80ETCQual60(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert",
                0, 60) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void test_12_Sulfura(){
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros",
                -1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Sulfuras, Hand of Ragnaros", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }
}

