package com.poalim.telegram.bankatim.app.logic.flow;

public enum Language {
	
	HEBROW("he"),
	ENGLISH("he")
	;
	private String code;
	
	Language(String code){
		this.code = (code);
	}

	public String getCode() {
		return code;
	}
	
	public static Language getLanguageByCode(String code){
		for(Language lan : Language.values())
			if(lan.getCode().equals(code))
				return lan;
		return null;
	}

}
