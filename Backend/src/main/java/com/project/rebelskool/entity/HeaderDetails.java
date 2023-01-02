package com.project.rebelskool.entity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//@Entity
@Getter
@Setter
@NoArgsConstructor
public class HeaderDetails implements Serializable {

    public static final long serialVersionUID = 748914851690305531L;

    public String header;
    public List<FieldDetails> fields;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<FieldDetails> getFields() {
        return fields;
    }

    public void setFields(List<FieldDetails> fields) {
        this.fields = fields;
    }

//    public void List<FieldDetails> getFieldDetails(){
//        return FieldDetails;
//    }
}
