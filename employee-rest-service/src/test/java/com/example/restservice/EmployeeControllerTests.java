package com.example.restservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.json.JsonCompareMode;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTests {

    private static final String EXPECTED_EMPLOYEES = """
            {
              "Employees": [
                {
                  "employee_id": "1",
                  "first_name": "Alex",
                  "last_name": "Morgan",
                  "email": "alex.morgan@example.com",
                  "title": "Software Engineer"
                },
                {
                  "employee_id": "2",
                  "first_name": "Priya",
                  "last_name": "Shah",
                  "email": "priya.shah@example.com",
                  "title": "Project Manager"
                },
                {
                  "employee_id": "3",
                  "first_name": "Daniel",
                  "last_name": "Reed",
                  "email": "daniel.reed@example.com",
                  "title": "Business Analyst"
                },
                {
                  "employee_id": "4",
                  "first_name": "Sofia",
                  "last_name": "Chen",
                  "email": "sofia.chen@example.com",
                  "title": "Quality Assurance Engineer"
                }
              ]
            }
            """;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsAllEmployeesInTheRequiredJsonFormat() throws Exception {
        assertFullEmployeeList();
    }

    @Test
    void repeatedRequestsReturnTheSameFullList() throws Exception {
        assertFullEmployeeList();
        assertFullEmployeeList();
    }

    @Test
    void rejectsAddingEmployeesAndKeepsTheOriginalList() throws Exception {
        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "employee_id": "5",
                                  "first_name": "Taylor",
                                  "last_name": "Wilson",
                                  "email": "taylor.wilson@example.com",
                                  "title": "Developer"
                                }
                                """))
                .andExpect(status().isMethodNotAllowed());

        assertFullEmployeeList();
    }

    private void assertFullEmployeeList() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(EXPECTED_EMPLOYEES, JsonCompareMode.STRICT));
    }
}
