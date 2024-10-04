package ru.demo.tests.ui.objects;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.demo.data.app.MenuItems;
import ru.demo.data.dictionaries.ObjectLevels;
import ru.demo.helpers.WithLogin;
import ru.demo.models.objects.CreateObjectRequestModel;
import ru.demo.models.objects.CreateObjectResponseModel;
import ru.demo.tests.ui.BaseWeb;
import ru.demo.data.randomData.RandomData;

@DisplayName("Objects list tests")
public class ObjectsListTests extends BaseWeb {
    private RandomData randomValues = new RandomData();
    private CreateObjectRequestModel randomDataObject = randomValues.allRequiredParams();

    @Test
    @WithLogin
    @DisplayName("Navigate to 'Objects' page from Main menu")
    @Tags({@Tag("smoke"), @Tag("regress")})
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Yulia Azovtseva")
    void successfulOpenObjectsPageFromMainMenuTest() {
        mainPage.openPage();
        header.navigateTo(MenuItems.OBJECTS);
        objectsListPage.objectsListPageIsDisplayed();
    }

    @ParameterizedTest
    @EnumSource(ObjectLevels.class)
    @WithLogin
    @DisplayName("Filter objects by level using 'Level' dropdown")
    @Tag("regress")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Yulia Azovtseva")
    void successfulFilteringByLevelUsingUpperLevelFilterTest(ObjectLevels level) {
        objectsListPage.openPage();
        objectsListPage.setLevelFilterValue(level.getLevelName());
        objectsListPage.clickFirstElementInList();
        objectViewPage.objectWithRequiredFieldsisDisplayed(level.getLevelName());
    }

    @Test
    @WithLogin
    @DisplayName("Search by existing address using the 'Sidebar Filter'")
    @Tag("smoke")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Yulia Azovtseva")
    void successfulSearchByAddressUsingSidebarFilterTest() {
        String objectCode = objectsApi.createObject(randomDataObject).as(CreateObjectResponseModel.class).getCode();

        objectsListPage.openPage();
        objectsListPage.expandSidebarFilter();
        sidebarFilter.setAddress(randomDataObject.getAddress());
        sidebarFilter.clickSubmitButton();
        objectsListPage.valueIsDisplayedInObjectsList(randomDataObject.getAddress());

        objectsApi.deleteObject(objectCode);
    }

    @Test
    @WithLogin
    @DisplayName("Search by existing address using the upper 'Main Search' field")
    @Tag("regress")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Yulia Azovtseva")
    void successfulSearchByAddressUsingTheMainSearchInputTest() {
        String objectCode = objectsApi.createObject(randomDataObject).as(CreateObjectResponseModel.class).getCode();
        String address = randomDataObject.getAddress();

        objectsListPage.openPage();
        objectsListPage.fillTheMainSearchField(address);
        objectsListPage.valueIsDisplayedInObjectsList(address);

        objectsApi.deleteObject(objectCode);
    }

    @Test
    @WithLogin
    @DisplayName("Search by a non-existing address using the upper 'Main Search' field")
    @Tag("regress")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Yulia Azovtseva")
    void unsuccessfulSearchByAddressUsingTheMainSearchTest() {
        objectsListPage.openPage();
        objectsListPage.fillTheMainSearchField("some address");
        objectsListPage.noObjectsFoundMessageIsDisplayed();
    }
}