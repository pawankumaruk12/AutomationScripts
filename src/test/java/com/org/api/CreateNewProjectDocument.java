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

import java.io.InputStream;
import java.nio.charset.Charset;

import static io.restassured.RestAssured.given;

public class CreateNewProjectDocument extends CommonLogin {

    @Test
    public void CreatesNewProjectDocuments() throws Exception {

        String projectId = (String) Repository.getValue("projectId");
        String documentTypeId = (String) Repository.getValue("documentTypeId");
        String base64;

        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("base64");
        try {
            base64 = org.apache.commons.io.IOUtils.toString(in, Charset.defaultCharset());


        } finally {
            org.apache.commons.io.IOUtils.closeQuietly(in);
        }

        //ProjectDocument
        ProjectDocument projectDocument = new ProjectDocument();
        projectDocument.setProjectId(projectId);
        projectDocument.setDocumentTypeId(documentTypeId);
        projectDocument.setRequiredByPayroll(true);
        //Document
        Document document = new Document();
        document.setName("TestingAPI");
        document.setFileName("SD CrewStart DirectHire.pdf");
        document.setFileType("application/pdf");
        document.setFileLength(151393);
        //Main
        DocumentWithProjectDocument documentWithProjectDocument = new DocumentWithProjectDocument();
        documentWithProjectDocument.setBase64(base64);
        documentWithProjectDocument.setDocument(document);
        documentWithProjectDocument.setProjectDocument(projectDocument);

        Gson gson = new Gson();
        String json = gson.toJson(documentWithProjectDocument);

        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");

        response = given().
                body(json).
                when()
                .cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "projectdocument/create").then()
                .assertThat().statusCode(201).and().extract().response();

        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();
        //projectDocument
        String projectDocumentId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("projectDocument").get("id").getAsString();
        Repository.addData("projectDocumentId", projectDocumentId);
        String projectDocumentVersionId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("projectDocument").get("versionId").getAsString();
        Repository.addData("projectDocumentVersionId", projectDocumentVersionId);
        Boolean active = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("projectDocument").get("active").getAsBoolean();
        Repository.addData("active", active);
        Boolean requiredByPayroll = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("projectDocument").get("requiredByPayroll").getAsBoolean();
        Repository.addData("requiredByPayroll", requiredByPayroll);
        projectId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("projectDocument").get("projectId").getAsString();
        Repository.addData("projectId", projectId);
        //document
        String documentId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("document").get("id").getAsString();
        Repository.addData("documentId", documentId);
        String documentName = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("document").get("name").getAsString();
        Repository.addData("documentName", documentName);
        String fileName = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("document").get("fileName").getAsString();
        Repository.addData("fileName", fileName);
        String fileType = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("document").get("fileType").getAsString();
        Repository.addData("fileType", fileType);
        Integer fileLength = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("document").get("fileLength").getAsInt();
        Repository.addData("fileLength", fileLength);
        String documentVersionId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("document").get("versionId").getAsString();
        Repository.addData("documentVersionId", documentVersionId);

    }
}
