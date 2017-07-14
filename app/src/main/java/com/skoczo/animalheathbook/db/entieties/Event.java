package com.skoczo.animalheathbook.db.entieties;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by skoczo on 11.07.17.
 */

@DatabaseTable(tableName = "events")
public class Event {

    @DatabaseField(columnName = "ID", canBeNull = false, generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false,foreign=true,foreignAutoRefresh=true)
    private EventName name;

    @DatabaseField
    private Float price;

    public Event() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EventName getName() {
        return name;
    }

    public void setName(EventName name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
