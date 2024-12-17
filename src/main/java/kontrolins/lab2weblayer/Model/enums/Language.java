package kontrolins.lab2weblayer.Model.enums;

public enum Language {
    ENGLISH,
    GERMAN,
    FRENCH,
    JAPANESE,
    KOREAN,
    CHINESE,
    LITHUANIAN;

    @Override
    public String toString() {
        String name = name().toLowerCase();
        return Character.toUpperCase(name.charAt(0)) + name.substring(1).replace("_", " ");
    }
}