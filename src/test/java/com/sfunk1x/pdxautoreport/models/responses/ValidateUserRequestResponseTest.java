package com.sfunk1x.pdxautoreport.models.responses;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.beanrunner.BeanRunner;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ValidateUserRequestResponseTest {

    private static final String authResponseJsonFilePath = "src/test/java/com/sfunk1x/pdxautoreport/resources/sampleResponses/authResponse.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final BeanRunner beanRunner = new BeanRunner();

    @Test
    public void authResponseTest() throws Exception {
        beanRunner.testBean(new ValidateUserResponse());
    }

    @Test
    public void loadAuthResponseJson() throws IOException {
        File authResponseJsonFile = new File(authResponseJsonFilePath);
        ValidateUserResponse validateUserResponse = objectMapper.readValue(authResponseJsonFile, ValidateUserResponse.class);
        assertNotNull(validateUserResponse);
        assertEquals("000-000-0001", validateUserResponse.getPhoneNumber());
        assertEquals("July, 25 2005 12:32:28", validateUserResponse.getCreatedDate());
        assertEquals("000-000-0002", validateUserResponse.getFaxNumber());
        assertEquals("A", validateUserResponse.getMiddleInitial());
        assertEquals(97201, validateUserResponse.getZipcode());
        assertTrue(validateUserResponse.isLoginValid());
        assertEquals("OR", validateUserResponse.getState());
        assertEquals("pdxReportUserName", validateUserResponse.getUserName());
        assertEquals("useremail@somedomain.com", validateUserResponse.getEmail());
        assertEquals("Last", validateUserResponse.getLastName());
        assertEquals("First", validateUserResponse.getFirstName());
        assertEquals("success", validateUserResponse.getStatus());
        assertEquals("Portland", validateUserResponse.getCity());
        assertEquals("50 NW Everett St", validateUserResponse.getAddress());
        assertEquals("June, 16 2021 14:05:12", validateUserResponse.getUpdatedDate());
        assertEquals(123456, validateUserResponse.getContactId());
        assertEquals("000-000-0003", validateUserResponse.getMobileNumber());
    }

    @Test
    public void toStringTest() {
        String expectedJson = "ValidateUserResponse{\"phone_num\":\"phone\", \"created_date\":\"date\", \"fax_num=\":\"fax\", \"middle_initial\":\"a\", \"zipcode\":4877, \"loginvalid\":true, \"state\":\"state\", \"user_name\":\"userName\", \"email\":\"email\", \"last_name\":\"lastName\", \"first_name\":\"firstName\", \"status\":\"status\", \"city\":\"city\", \"address\":\"address\", \"contact_id\":123, \"mobile_num\":\"mobile\"}";
        ValidateUserResponse validateUserResponse = new ValidateUserResponse("phone", "date", "fax", "a", 12345-7468, true, "state", "userName", "email", "lastName", "firstName", "status", "city", "address", "updatedDate", 123, "mobile", null);
        assertNotNull(validateUserResponse);
        assertEquals(expectedJson, validateUserResponse.toString());
    }
}
