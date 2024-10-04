package ru.demo.tests.ui.objects;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import ru.demo.data.dictionaries.Districts;
import ru.demo.data.dictionaries.ObjectTypes;
import ru.demo.helpers.WithLogin;
import ru.demo.tests.ui.BaseWeb;
import ru.demo.data.randomData.RandomData;

@DisplayName("Create object tests")
public class CreateObjectTests extends BaseWeb {
    private RandomData randomValues = new RandomData();

    @Test
    @WithLogin
    @DisplayName("Open 'New object' form from Objects page")
    @Tags({@Tag("smoke"), @Tag("regress")})
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Yulia Azovtseva")
    void successfulNewObjectFormOpenTest() {
        objectsListPage.openPage();
        objectsListPage.clickNewObjectButton();
        createObjectPage.newObjectFormIsDisplayed();
    }

    @Test
    @WithLogin
    @DisplayName("Create a new object with all required fields properly filled")
    @Tags({@Tag("smoke"), @Tag("regress")})
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Yulia Azovtseva")
    void successfulNewObjectCreationTest() {
        Districts district = randomValues.getRandomDistrict();
        ObjectTypes objectType = randomValues.getRandomObjectType();
        String objectAddress = randomValues.generateRandomAddress();
        String objectName = "Object Name";

        createObjectPage.openPage();
        createObjectPage.setObjectName(objectName);
        createObjectPage.setAddress(objectAddress);
        createObjectPage.setObjectType(objectType.getObjectTypeName());
        createObjectPage.setParentCode(district.getDistrictName());
        String objectCode = createObjectPage.getObjectCode();
        createObjectPage.clickSubmitButton();
        objectViewPage.objectWithRequiredFieldsisDisplayed(objectCode, district.getDistrictName(), objectType.getObjectTypeName(), objectAddress);

        objectsApi.deleteObject(objectCode);
    }

    @Test
    @WithLogin
    @DisplayName("Create a new object with incomplete required fields")
    @Tag("regress")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Yulia Azovtseva")
    void newObjectCreationWithIncompleteRequiredFieldsTest() {
        String objectAddress = randomValues.generateRandomAddress();
        String objectName = "Object Name";

        createObjectPage.openPage();
        createObjectPage.setObjectName(objectName);
        createObjectPage.setAddress(objectAddress);
        createObjectPage.clickSubmitButton();
        createObjectPage.requiredFieldErrorMessageIsDisplayed();
    }
}
