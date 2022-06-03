package acme.entities.fuppo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.inventions.Invention;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Fuppo extends AbstractEntity {

	// Serialisation identifier ----------------------------------------
	
	protected static final long serialVersionUID		= 1L;
	
	// Atributes -------------------------------------------------------
	
	@NotBlank
	@Pattern(regexp = "^\\w{3}-[0-9]{2}(0[1-9]|1[0-2])-\\w{2}/(0[1-9]|[12][0-9]|3[01])$")
	protected String			code;
	
	@NotBlank
	@Length(min = 1, max = 100)
	protected String			name;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String			statement;
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				creationMoment;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date 				periodStart;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date 				periodEnd;
	
	@NotNull
	@Valid
	protected Money				quantity;
	
	@URL
	protected String			moreInfo;
	
	// Derived attributes ----------------------------------------------
	
	// Relationships ---------------------------------------------------
	
	@NotNull
	@Valid
	@OneToOne(optional = false)
	protected Invention			component;
		
}
