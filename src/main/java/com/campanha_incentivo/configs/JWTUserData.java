package com.campanha_incentivo.configs;

import java.util.List;


public record JWTUserData(
		 Long userId,String email,List<String> roles
		 ) {

	

}
