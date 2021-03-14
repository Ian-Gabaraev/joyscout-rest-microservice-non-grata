package com.joyscout.NonGrata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joyscout.NonGrata.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NonGrataApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void checkByNonexistentIDReturns404() throws Exception {

		String tg_id = "3131rr3";

		this.mockMvc.perform(get("/api/v1/banlist/" + tg_id))
				.andExpect(status().isNotFound());
	}

	@Test
	public void checkByExistingIDReturns200() throws Exception {

		String tg_id = "65g3113";

		User user = new User();
		user.setId(tg_id);
		user.setBanned(true);

		String user_as_json = mapper.writeValueAsString(user);

		this.mockMvc.perform(post("/api/v1/banlist")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user_as_json));

		this.mockMvc.perform(get("/api/v1/banlist/" + tg_id))
				.andExpect(status().isOk());
	}

	@Test
	public void banUserReturns204() throws Exception {

		String tg_id = "jh55j22";

		User user = new User();
		user.setId(tg_id);
		user.setBanned(true);

		String user_as_json = mapper.writeValueAsString(user);

		this.mockMvc.perform(post("/api/v1/banlist")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user_as_json))
		.andExpect(status().isNoContent());
	}

	@Test
	public void unbanBannedUserReturns204() throws Exception {

		String tg_id = "hghg313";

		User user = new User();
		user.setId(tg_id);
		user.setBanned(true);

		String user_as_json = mapper.writeValueAsString(user);

		this.mockMvc.perform(post("/api/v1/banlist")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user_as_json));

		this.mockMvc.perform(delete("/api/v1/banlist/" + tg_id))
				.andExpect(status().isNoContent());
	}

	@Test
	public void unbanNonBannedUserReturns404() throws Exception {

		String tg_id = "rwgty522";

		this.mockMvc.perform(delete("/api/v1/banlist/" + tg_id))
				.andExpect(status().isNotFound());
	}

}
