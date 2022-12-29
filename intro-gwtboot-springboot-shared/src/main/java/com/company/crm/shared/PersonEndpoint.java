package com.company.crm.shared;

public interface PersonEndpoint {

	String SERVER_CONTEXT_PATH = "http://localhost:9090/server";

	String PERSON_LIST = "/v1/persons";

	String PERSON_WITH_ERROR_LIST = "v1/persons/error";

	String DATE_FORMAT = "yyyy-MM-dd@HH:mm:ss.SSSZ";

}
