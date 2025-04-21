import java.util.List;

// Ini adalah contoh dari Generic Class
// <T> menunjukkan bahwa class ini bersifat generic, artinya bisa digunakan untuk berbagai tipe data:
// Misalnya: Dataset<Image>, Dataset<Text>, Dataset<Video>, dst
    public class Dataset<T> {
    private List<T> data;

    public Dataset(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }
}