package validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import util.FormInput;

public class FormInputValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return FormInput.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FormInput forminput = (FormInput) target;
		
		if(forminput.getVoetbalCode1() > forminput.getVoetbalCode2()) {
			errors.rejectValue("voetbalCode1", "voetbalCode.formInput.voetbalCode1", "voetbalCode1 < voetbalcode2");
		}
	
	}

	
}
