package data;

public enum FormKeys {
    NAME("Student Name"),
    EMAIL("Student Email"),
    GENDER("Gender"),
    MOBILE("Mobile"),
    BIRTH("Date of Birth"),
    SUBJECT("Subjects"),
    HOBBY("Hobbies"),
    PICTURE("Picture"),
    ADDRESS("Address"),
    STATEANDCITY("State and City");

    FormKeys(String key) {
        this.key = key;
    }

    private final String key;

    public String getKey() {
        return key;
    }
}