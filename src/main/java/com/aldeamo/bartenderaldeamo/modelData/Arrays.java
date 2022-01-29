package com.aldeamo.bartenderaldeamo.modelData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="arrays")
public class Arrays {
    int id;
    String input_array;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "input_array")
    public String getInput_array() {
        return input_array;
    }

    public void setInput_array(String input_array) {
        this.input_array = input_array;
    }
}