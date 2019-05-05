package br.com.plantier.loucademia.domain.application.util;

public class Validation {

	// Valida��o de obrigatoriadade de informa��o (Garanta que n�o est� vazio)
	public static void assertNotEmpty(Object obj) {
		if (obj instanceof String) {
			String s = (String) obj;
			if (StringUtils.isEmpty(s)) {
				throw new ValidationException("O texto n�o pode ser vazio.");
			}
		}
		
		if (obj == null) {
			throw new ValidationException("O valor n�o pode ser vazio.");
		}
			
			
	}
}
