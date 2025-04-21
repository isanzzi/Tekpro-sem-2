// Ini adalah contoh dari Generic Interface
// <T> menunjukkan bahwa setiap implementasi interface ini akan bekerja dengan tipe data tertentu
// Contohnya: Model<Image>, Model<Text>, Model<Video>
public interface Model<T> {
    void train(Dataset<T> dataset); // method pelatihan untuk dataset bertipe T
    double predict(T input);        // prediksi berdasarkan input bertipe T
}