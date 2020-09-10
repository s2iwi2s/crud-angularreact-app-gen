package com.myapp.crud.appgen.XYclassYX;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Date;
import java.util.Set;

import com.myapp.crud.appgen.codeGroups.CodeGroups;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "XYclassYX", catalog="case")
public class XYCLASSYX {
	@Id
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "XYCLASSYX_ID_GEN")
    @SequenceGenerator(name = "XYCLASSYX_ID_GEN", sequenceName = "XYCLASSYX_ID_SEQ", allocationSize = 1)
    private Long id;

XYfields-tmplYX
}
