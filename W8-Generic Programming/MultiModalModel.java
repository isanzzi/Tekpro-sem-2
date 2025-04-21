public class MultiModalModel implements TrainableModel<ImageText> {
    @Override
    public void train(Dataset<ImageText> dataset) {
        for (ImageText data : dataset.getData()) {
            System.out.println("Belajar dari gambar: " + data.getImagePath() + " dengan label: " + data.getTextLabel());
        }
    }

    @Override
    public double predict(ImageText input) {
        return 0.85;
    }

    @Override
    public void safeTrain(Dataset<?> dataset) {
        try {
            @SuppressWarnings("unchecked")
            Dataset<ImageText> casted = (Dataset<ImageText>) dataset;
            train(casted);
        } catch (ClassCastException e) {
            System.out.println("Dataset tidak cocok untuk MultiModalModel.");
        }
    }
}