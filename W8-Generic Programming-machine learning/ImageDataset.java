import java.util.List;

// Ini contoh Generic Inheritance
// ImageDataset<T> bisa digunakan untuk berbagai tipe, bukan cuma Image
public class ImageDataset<T> extends Dataset<T> {
    public ImageDataset(List<T> data) {
        super(data);
    }
}
