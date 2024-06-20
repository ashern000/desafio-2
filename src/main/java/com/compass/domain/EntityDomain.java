package com.compass.domain;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class EntityDomain {
    public Map<String, ?> toDict() {
        Map<String, Object> map = new HashMap<>();
        Field[] campos = this.getClass().getDeclaredFields();

        for (Field campo : campos) {
            campo.setAccessible(true);
            try {
                map.put(campo.getName(), campo.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return map;
    }
}
