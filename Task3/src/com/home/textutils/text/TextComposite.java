package com.home.textutils.text;

import java.util.ArrayList;
import java.util.List;

public class TextComposite {
    private String value;
    private List<TextComposite> subValues;

    public TextComposite(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void add(TextComposite composite) {
        if (null == subValues) {
            subValues = new ArrayList<>();
        }
        subValues.add(composite);
    }

    public boolean remove(TextComposite composite) {
        if(!subValues.remove(composite)){
            for(TextComposite subComposite:subValues){
                if(subComposite.remove(composite)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj){
            return true;
        }
        if(obj instanceof TextComposite){
            TextComposite composite = (TextComposite)obj;
            return value.equals(composite.getValue());
        }
        return false;
    }
}
