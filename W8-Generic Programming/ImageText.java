// ImageText.java
public class ImageText {
    String imagePath;
    String textLabel;

    public ImageText(String imagePath, String textLabel) {
        this.imagePath = imagePath;
        this.textLabel = textLabel;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getTextLabel() {
        return textLabel;
    }
}