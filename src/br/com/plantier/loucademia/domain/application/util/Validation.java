package br.com.plantier.loucademia.domain.application.util;

public class Validation {

	// Validação de obrigatoriadade de informação (Garanta que não está vazio)
	public static void assertNotEmpty(Object obj) {
		if (obj instanceof String) {
			String s = (String) obj;
			if (StringUtils.isEmpty(s)) {
				throw new ValidationException("O texto não pode ser vazio.");
			}
		}
		
		if (obj == null) {
			throw new ValidationException("O valor não pode ser vazio.");
		}
			
			
	}
}
