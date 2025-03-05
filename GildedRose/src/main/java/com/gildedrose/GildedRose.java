package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            switch (items[i].name) {
                case "Aged Brie":
                    HandleBrie(items[i]);
                    break;
                case "Backstage passes to a TAFKAL80ETC concert":
                    HandleTAKFAL80ETC(items[i]);
                    break;
                    case "Sulfuras, Hand of Ragnaros":
                        break;
                default:
                    if (items[i].quality > 0) {
                        items[i].quality = items[i].quality - 1;
                    }
                    items[i].sellIn = items[i].sellIn - 1;

                    if (items[i].sellIn < 0) {
                        if (items[i].quality > 0) {
                            items[i].quality = items[i].quality - 1;
                        }
                    }
                    break;
            }
        }
    }

    public void HandleBrie(Item item){
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;
            }
        }
    }

    public void HandleTAKFAL80ETC(Item item){
        if (item.quality < 50) {
            item.quality = item.quality + 1;

            if (item.sellIn < 11) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }

            if (item.sellIn < 6) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            item.quality = item.quality - item.quality;
        }
    }
}
