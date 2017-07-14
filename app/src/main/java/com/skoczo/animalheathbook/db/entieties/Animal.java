package com.skoczo.animalheathbook.db.entieties;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by skoczo on 07.07.17.
 */

@DatabaseTable(tableName = "animals")
public class Animal {

    @DatabaseField(columnName = "ID", canBeNull = false, generatedId = true)
    private int entityId;

    // imie
    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Breed breed;
    // rasa

    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private byte[] picture;

    @ForeignCollectionField
    private Collection<Weigth> weigthList;

    public Animal() {
        weigthList = new ArrayList<>();
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Collection<Weigth> getWeigthList() {
        return weigthList;
    }

    public void setWeigthList(Collection<Weigth> weigthList) {
        this.weigthList = weigthList;
    }
}
