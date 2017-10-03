package com.org.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Document;
import com.org.api.model.DocumentWithProjectDocument;
import com.org.api.model.ProjectDocument;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdatesProjectDocument extends CommonLogin {
    @Test
    public void testUpdatesProjectDocuments() throws Exception {
        //getting values for projectDocument
        String projectDocumentId = (String) Repository.getValue("projectDocumentId");
        String projectDocumentVersionId = (String) Repository.getValue("projectDocumentVersionId");
        Boolean active = (Boolean) Repository.getValue("active");
        String projectId = (String) Repository.getValue("projectId");
        String documentTypeId = (String) Repository.getValue("documentTypeId") ;
        Boolean requiredByPayroll = (Boolean) Repository.getValue("requiredByPayroll");

        //getting values for document
        String documentId = (String ) Repository.getValue("documentId");
        String documentName = (String) Repository.getValue("documentName");
        String fileName = (String) Repository.getValue("fileName");
        String  fileType = (String) Repository.getValue("fileType");
        Integer fileLength = (Integer) Repository.getValue("fileLength");
        String parentId = (String) Repository.getValue("parentId");
        String lastUpdated = (String) Repository.getValue("lastUpdated");
        String documentVersionId = (String) Repository.getValue("documentVersionId");
        String pageNumber = (String) Repository.getValue("pageNumber");

        //setting values in projectDocument
        ProjectDocument projectDocument = new ProjectDocument();
        projectDocument.setId(projectDocumentId);
        projectDocument.setVersionId(projectDocumentVersionId);
        projectDocument.setActive(active);
        projectDocument.setProjectId(projectId);
        projectDocument.setDocumentTypeId(documentTypeId);
        projectDocument.setRequiredByPayroll(requiredByPayroll);

        //setting values in document
        Document document = new Document();
        document.setId(documentId);
        document.setName(documentName + "1");
        document.setFileName(fileName);
        document.setFileType(fileType);
        document.setFileLength(fileLength);
        document.setParentId(null);
        document.setLastUpdated(null);
        document.setPageNumber(null);

        //Main
        DocumentWithProjectDocument documentWithProjectDocument = new DocumentWithProjectDocument();
        documentWithProjectDocument.setProjectDocument(projectDocument);
        documentWithProjectDocument.setDocument(document);
        Gson gson = new Gson();
        String json = gson.toJson(documentWithProjectDocument);

        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);

        response = given()
                .body(json)
                .when().cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).contentType(ContentType.JSON)
                .post(API_PATH + "projectdocument/update").then()
                .assertThat().statusCode(200).and().extract().response();

        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();
        //projectDocument
         projectDocumentId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("projectDocument").get("id").getAsString();
        Repository.addData("projectDocumentId", projectDocumentId);
        projectDocumentVersionId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("projectDocument").get("versionId").getAsString();
        Repository.addData("projectDocumentVersionId", projectDocumentVersionId);
        active = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("projectDocument").get("active").getAsBoolean();
        Repository.addData("active", active);
        requiredByPayroll = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("projectDocument").get("requiredByPayroll").getAsBoolean();
        Repository.addData("requiredByPayroll", requiredByPayroll);
        projectId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("projectDocument").get("projectId").getAsString();
        Repository.addData("projectId", projectId);
        //document
        documentId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("document").get("id").getAsString();
        Repository.addData("documentId", documentId);
        documentName = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("document").get("name").getAsString();
        Repository.addData("documentName", documentName);
        fileName = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("document").get("fileName").getAsString();
        Repository.addData("fileName", fileName);
        fileType = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("document").get("fileType").getAsString();
        Repository.addData("fileType", fileType);
        fileLength = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("document").get("fileLength").getAsInt();
        Repository.addData("fileLength", fileLength);
        documentVersionId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("document").get("versionId").getAsString();
        Repository.addData("documentVersionId", documentVersionId);
    }
}
