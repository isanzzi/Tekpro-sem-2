// Model untuk tipe Image
public class KNNImageModel implements TrainableModel<Image> {

    @Override
    public void train(Dataset<Image> dataset) {
        for (Image img : dataset.getData()) {
            System.out.println("Belajar dari gambar: " + img.getPath());
        }
    }

    @Override
    public double predict(Image input) {
        return 0.9;
    }

    @Override
    public void safeTrain(Dataset<?> dataset) {
        try {
            // Cast ke Dataset<Image>, jika gagal maka catch akan jalan
            @SuppressWarnings("unchecked")
            Dataset<Image> casted = (Dataset<Image>) dataset;
            train(casted);
        } catch (ClassCastException e) {
            System.out.println("Dataset tidak cocok untuk KNNImageModel.");
        }
    }
}
