// Ini contoh penggunaan multiple type parameter generik
// M adalah Model<D>, artinya model bekerja dengan data bertipe D
// D adalah tipe data (Image, Text, dst)
public class Experiment<M extends Model<D>, D> {
    private M model;
    private Dataset<D> dataset;

    public Experiment(M model, Dataset<D> dataset) {
        this.model = model;
        this.dataset = dataset;
    }

    public void run() {
        model.train(dataset);
        System.out.println("Experiment done.");
    }
}
