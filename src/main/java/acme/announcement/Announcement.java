
package acme.announcement;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Announcement extends AbstractEntity {

	protected static final long	serialVersionUID	= 1L;

	@NotNull
	@Past
	private Date creationMoment;
	@NotNull
	@NotBlank(message = "The title must be mandatory")
	@Length(min = 0, max = 101)
	private String title;
	@NotNull
	@NotBlank(message = "The body must be mandatory")
	@Length(min = 0, max = 256)
	private String body;
	@NotNull
	private Boolean	critic;
	@URL
	private String link;

}
