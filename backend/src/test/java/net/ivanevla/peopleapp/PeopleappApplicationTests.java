package net.ivanevla.peopleapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-person-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-person-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class PeopleappApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoad() throws Exception {
        this.mockMvc
                .perform(get("/api/person/list"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(12)))
                .andExpect(status().isOk());
    }

    @Test
    public void findPersonWithoutParams() throws Exception {
        this.mockMvc
                .perform(get("/api/person/list")
                        .param("personalId", "")
                        .param("dateOfBirth", ""))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(12)))
                .andExpect(status().isOk());
    }

    @Test
    public void findPersonByPersonalIdOutputNoResult() throws Exception {
        this.mockMvc
                .perform(get("/api/person/list")
                        .param("personalId", "12345678901")
                        .param("dateOfBirth", ""))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void findPersonByPersonalIdOutputOneResult() throws Exception {
        this.mockMvc
                .perform(get("/api/person/list")
                        .param("personalId", "39011121234")
                        .param("dateOfBirth", ""))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].personalId", is("39011121234")))
                .andExpect(jsonPath("$[0].firstName", is("Nicolas")))
                .andExpect(jsonPath("$[0].lastName", is("Steggals")))
                .andExpect(jsonPath("$[0].gender", is("MALE")))
                .andExpect(jsonPath("$[0].dateOfBirth", is("1990-11-12")));
    }

    @Test
    public void invalidPersonDateOfBirth() throws Exception {
        this.mockMvc
                .perform(get("/api/person/list")
                        .param("personalId", "")
                        .param("dateOfBirth", "1990-25-30"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Invalid date")));
    }

    @Test
    public void findPersonByDateOfBirthOutputNoResult() throws Exception {
        this.mockMvc
                .perform(get("/api/person/list")
                        .param("personalId", "")
                        .param("dateOfBirth", "2020-02-02"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void findPersonByDateOfBirthOutputOneResult() throws Exception {
        this.mockMvc
                .perform(get("/api/person/list")
                        .param("personalId", "")
                        .param("dateOfBirth", "1990-11-12"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].personalId", is("39011121234")))
                .andExpect(jsonPath("$[0].firstName", is("Nicolas")))
                .andExpect(jsonPath("$[0].lastName", is("Steggals")))
                .andExpect(jsonPath("$[0].gender", is("MALE")))
                .andExpect(jsonPath("$[0].dateOfBirth", is("1990-11-12")));
    }

    @Test
    public void findPersonByDateOfBirthOutputTwoResults() throws Exception {
        this.mockMvc
                .perform(get("/api/person/list")
                        .param("personalId", "")
                        .param("dateOfBirth", "1990-06-05"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].personalId", is("39006051234")))
                .andExpect(jsonPath("$[0].firstName", is("Filberte")))
                .andExpect(jsonPath("$[0].lastName", is("Lippingwell")))
                .andExpect(jsonPath("$[0].gender", is("MALE")))
                .andExpect(jsonPath("$[0].dateOfBirth", is("1990-06-05")))
                .andExpect(jsonPath("$[1].personalId", is("49006051234")))
                .andExpect(jsonPath("$[1].firstName", is("Joela")))
                .andExpect(jsonPath("$[1].lastName", is("Lippingwell")))
                .andExpect(jsonPath("$[1].gender", is("FEMALE")))
                .andExpect(jsonPath("$[1].dateOfBirth", is("1990-06-05")));
    }
}
