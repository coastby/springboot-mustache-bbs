package com.mustache.bbs.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.List;

@Getter
@Builder
public class HospitalListDto {
    private String address;
    private List<String> types;

    public String getStringOfTypes(){
        return String.join(",", types);
    }
    public HospitalListDto toNotNull() {
        for (Field f : this.getClass().getFields()) {
            f.setAccessible(true);
            try {
                if (f.get(this) == null) {
                    f.set(this, "");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return this;
    }
}
