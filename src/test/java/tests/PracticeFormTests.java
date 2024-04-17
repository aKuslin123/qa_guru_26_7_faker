package tests;

import data.FormKeys;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static java.lang.String.format;
import static utils.RandomUtils.*;

public class PracticeFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    String firstName = getRandomFirstName(),
            lastName = getRandomLastName(),
            fullName = format("%s %s", firstName, lastName),
            userEmail = getRandomEmail(),
            userGender = getRandomGender(),
            userNumber = getRandomPhoneNumber(),
            birthDay = getRandomDay(),
            birthMonth = getRandomMonth(),
            birthYear = getRandomYear(),
            birthDate = format("%s %s,%s", birthDay, birthMonth, birthYear),
            userSubject = getRandomSubject(),
            userHobby = getRandomHobby(),
            userPictureDisplayed = getRandomPicture(),
            userPictureUpload = "images/" + userPictureDisplayed,
            userAddress = getRandomAddress(),
            state = getRandomState(),
            city = getRandomCity(state),
            stateAndCity = format("%s %s", state, city),
            emptyField = " ";

    @Test
    void fillFormTest() {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(userGender)
                .setUserNumber(userNumber)
                .setBirthDate(birthDay, birthMonth, birthYear)
                .setSubject(userSubject)
                .setHobby(userHobby)
                .uploadPicture(userPictureUpload)
                .setAddress(userAddress)
                .setState(state)
                .setCity(city)
                .pressSubmit();

        registrationPage.checkResult(FormKeys.Name.getKey(), fullName)
                .checkResult(FormKeys.Email.getKey(), userEmail)
                .checkResult(FormKeys.Gender.getKey(), userGender)
                .checkResult(FormKeys.Mobile.getKey(), userNumber)
                .checkResult(FormKeys.Birth.getKey(), birthDate)
                .checkResult(FormKeys.Subject.getKey(), userSubject)
                .checkResult(FormKeys.Hobby.getKey(), userHobby)
                .checkResult(FormKeys.Picture.getKey(), userPictureDisplayed)
                .checkResult(FormKeys.Address.getKey(), userAddress)
                .checkResult(FormKeys.StateAndCity.getKey(), stateAndCity);
    }

    @Test
    void minSuccessfulFillFormTest() {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(userGender)
                .setUserNumber(userNumber)
                .pressSubmit();

        registrationPage.checkResult(FormKeys.Name.getKey(), fullName)
                .checkResult(FormKeys.Email.getKey(), emptyField)
                .checkResult(FormKeys.Gender.getKey(), userGender)
                .checkResult(FormKeys.Mobile.getKey(), userNumber)
                .checkResult(FormKeys.Birth.getKey(), emptyField)
                .checkResult(FormKeys.Subject.getKey(), emptyField)
                .checkResult(FormKeys.Hobby.getKey(), emptyField)
                .checkResult(FormKeys.Picture.getKey(), emptyField)
                .checkResult(FormKeys.Address.getKey(), emptyField)
                .checkResult(FormKeys.StateAndCity.getKey(), emptyField);
    }

    @Test
    void validationFormTest() {
        registrationPage.openPage()
                .pressSubmit();

        registrationPage.checkGenderValidation("Male")
                .checkGenderValidation("Female")
                .checkGenderValidation("Other")
                .checkInputValidation("firstName")
                .checkInputValidation("lastName")
                .checkInputValidation("userNumber");

        registrationPage.checkFormNotDisplayed();
    }
}