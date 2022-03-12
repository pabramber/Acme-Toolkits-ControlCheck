package acme.entities.tools;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tool extends AbstractEntity {

	// Serialisation identifier ----------------------------------------
	
	protected static final long serialVersionUID		= 1L;
	
	// Atributes -------------------------------------------------------
	
	@NotBlank
	@Length(min = 0, max = 100)
	protected String			name;
	
	@NotNull
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@Column(unique = true)
	protected String			code;
	
	@NotBlank
	@Length(min = 0, max = 100)
	protected String			technology;
	
	@NotBlank
	@Length(min = 0, max = 255)
	protected String			description;
	
	// Need custom validator to check that the amount of Money is >= 0
	@NotNull
	@Valid
	protected Money				retailPrice;
	
	@URL
	protected String			link; 
	
	// Derived attributes ----------------------------------------------
	
	// Relationships ---------------------------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Inventor				inventor;
	
}
