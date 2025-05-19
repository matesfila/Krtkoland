package com.kbsystems.zadanie.matusfila.krtkoland.core.graphs;

import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.interfaces.GraphObject;

import java.util.Objects;

public class ID implements Comparable<ID>, GraphObject {

    private final String value;

    public ID(String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(ID o) {
        return this.value.compareTo(o.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return value.equals(((ID)obj).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ID{" + "value='" + value + '\'' + '}';
    }
}
