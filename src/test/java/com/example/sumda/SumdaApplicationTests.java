package com.example.sumda;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SumdaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		// 애플리케이션 컨텍스트가 정상적으로 로드되는지 확인
	}

	@Test
	void healthCheckTest() throws Exception {
		// Health Check 엔드포인트 테스트
		mockMvc.perform(get("/api/health"))
				.andExpect(status().isOk())
				.andExpect(content().json("{\"status\":\"ok\"}"));
	}

	@Test
	void healthCheckResponseTest() throws Exception {
		// Health Check 응답 내용 검증
		mockMvc.perform(get("/api/health"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.status").value("ok"));
	}
}