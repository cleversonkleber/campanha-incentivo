package com.campanha_incentivo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Disabled("Desabilitado temporariamente até que a configuração do DB seja corrigida.")
@SpringBootTest
@ActiveProfiles("test")
class CampanhaIncentivoApplicationTests {

	@Test
	void contextLoads() {
	}

}
