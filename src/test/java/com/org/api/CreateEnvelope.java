package com.org.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Envelopes;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CreateEnvelope extends CommonLogin {
    @Test public void testCreateEnvelope() throws Exception {

        String projectMemberId = (String) Repository.getValue("projectMemberId");
        String name = (String) Repository.getValue("envelopeTemplateName");
        String envelopeTemplateId =(String) Repository.getValue("envelopeTemplateId");
        String parentId = (String ) Repository.getValue("departmentEnvelopeTemplateParentId");

        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);

        //Envelopes
        Envelopes envelopes = new Envelopes();
        envelopes.setJsonmodel("{\\\"productionTitle\\\":\\\"test\\\",\\\"productionCompany\\\":\\\"test\\\",\\\"firstName\\\":\\\"AutoPerson\\\",\\\"lastName\\\":\\\"Name01\\\",\\\"fullName\\\":\\\"AutoPerson Name01\\\",\\\"gender\\\":null,\\\"dob\\\":null,\\\"addressLine1\\\":null,\\\"addressLine2\\\":null,\\\"town\\\":null,\\\"postCode\\\":null,\\\"addressLine1and2\\\":\\\" \\\",\\\"addressLine3and4\\\":\\\"  \\\",\\\"countyState\\\":null,\\\"country\\\":null,\\\"mobile\\\":\\\"07783238923901\\\",\\\"email\\\":\\\"AutoPerson0@gmail.com\\\",\\\"niNumber\\\":null,\\\"niNumberExemption\\\":false,\\\"equityPensionNumber\\\":null,\\\"socialSecurityNumber\\\":null,\\\"passportNumber\\\":null,\\\"countryOfCitizenship\\\":null,\\\"countryOfOrdinaryResidence\\\":null,\\\"nextOfKinName\\\":null,\\\"nextOfKinTelephone\\\":null,\\\"position\\\":\\\"Production Manager\\\",\\\"startDate\\\":\\\"16/10/2017\\\",\\\"endDate\\\":null,\\\"agreementDate\\\":\\\"16/10/2017\\\",\\\"department\\\":\\\"AutoAccounts\\\",\\\"vatRegistrationNumber\\\":null,\\\"scheduleDNumber\\\":null,\\\"agentExists\\\":false,\\\"agent\\\":{\\\"Name\\\":null,\\\"AddressLine1\\\":null,\\\"AddressLine2\\\":null,\\\"Town\\\":null,\\\"PostCode\\\":null,\\\"countyState\\\":null,\\\"Country\\\":null,\\\"Email\\\":null,\\\"Mobile\\\":null,\\\"AddressLine1and2\\\":null,\\\"AddressLine3and4\\\":null},\\\"payMethod\\\":null,\\\"accountName\\\":null,\\\"bankName\\\":null,\\\"bankAddress1\\\":null,\\\"bankAddress2\\\":null,\\\"ibanNumber\\\":null,\\\"sortCode\\\":null,\\\"accountNumber\\\":null,\\\"additionalReference\\\":null,\\\"abaRouting\\\":null,\\\"swiftCode\\\":null,\\\"employmentstatement\\\":null,\\\"specialConditions\\\":null,\\\"salaryCalculationRate\\\":10.77,\\\"salaryHolidayEnt\\\":\\\"5000.00\\\",\\\"salary\\\":\\\"4513.86\\\",\\\"holidayEntitlement\\\":\\\"486.14\\\",\\\"dailyPerDiem\\\":null,\\\"boxRentalClaimed\\\":false,\\\"boxRental\\\":{\\\"Items\\\":null,\\\"DailyRate\\\":null,\\\"Cap\\\":null,\\\"Commencing\\\":null,\\\"Value\\\":null,\\\"BoxRentalSchedule\\\":null},\\\"equipmentRentalClaimed\\\":false,\\\"equipmentRental\\\":{\\\"Items\\\":null,\\\"DailyRate\\\":null,\\\"Cap\\\":null,\\\"Commencing\\\":null,\\\"Value\\\":null,\\\"EquipmentRentalSchedule\\\":null},\\\"carAllowanceClaimed\\\":false,\\\"carAllowance\\\":{\\\"DailyRate\\\":null,\\\"VehicleYear\\\":null,\\\"VehicleReg\\\":null,\\\"Licence\\\":null,\\\"MotExp\\\":null,\\\"InsuranceComp\\\":null,\\\"InsuranceDate\\\":null,\\\"DrivingLicenseAttachment\\\":null,\\\"MotorInsuranceAttachment\\\":null},\\\"payFrequency\\\":\\\"Weekly\\\",\\\"payFreqWeeks\\\":\\\"0\\\",\\\"days\\\":null,\\\"hours\\\":null,\\\"hourlyRate\\\":\\\"\\\",\\\"productionCredit\\\":null,\\\"dailyRate\\\":\\\"\\\",\\\"hasStudentLoan\\\":false,\\\"studentLoan\\\":{\\\"noDeduction\\\":null,\\\"planOne\\\":null,\\\"planTwo\\\":null,\\\"beforeLastSixApril\\\":null,\\\"planType\\\":null},\\\"documentType\\\":null,\\\"EligibilityToWorkInTheUk\\\":null,\\\"documents\\\":[{\\\"name\\\":\\\"TestingAPI\\\",\\\"documentid\\\":\\\"GjAAPXF7T1U7j27A3TRstA\\\",\\\"numberOfPages\\\":1,\\\"ticked\\\":false,\\\"tickedByAgent\\\":false,\\\"required\\\":true,\\\"mappings\\\":[]}],\\\"HOLIDAY_CREDIT\\\":12.07,\\\"officeUseOnly\\\":{\\\"salaryAllocation\\\":null,\\\"holidayEntitlementAllocation\\\":null,\\\"boxRentalAmountAllocation\\\":null,\\\"equipmentRentalAmountAllocation\\\":null,\\\"carAllowanceAmountAllocation\\\":null,\\\"taxCode\\\":null,\\\"wk1Cum\\\":null,\\\"excFromRTI\\\":false,\\\"excFromAE\\\":false,\\\"documentApproved\\\":false}}");
        envelopes.setDueDate("1508108400000");
        envelopes.setEnvelopeTemplateId(envelopeTemplateId);
        envelopes.setName(name);
        envelopes.setOfficeOnlyJSONModel("{}");
        envelopes.setProjectMemberId(projectMemberId);
        envelopes.setTemplateParentId(parentId);
        List<String> userManualInput = new ArrayList<>();
        userManualInput.add("productionTitle");
        userManualInput.add("productionCompany");
        userManualInput.add("salaryHolidayEnt");
        envelopes.setTemplateParentType("DepartmentEnvelopeTemplate");
        envelopes.setUserManualInput(userManualInput);
        Gson gson = new Gson();
        String json = gson.toJson(envelopes);

        response  = given().
                body(json).
                when()
                .cookie(JSESSIONID,jsessionId)
                .cookie(XSRF_TOKEN,xsrfToken)
                .contentType(ContentType.JSON).
                post(API_PATH + "envelope/create");
        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();
        response.getBody().print();
    }
}
