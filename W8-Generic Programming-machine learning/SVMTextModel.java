public class SVMTextModel implements TrainableModel<Text> {
    @Override
    public void train(Dataset<Text> dataset) {
        for (Text txt : dataset.getData()) {
            System.out.println("Belajar dari teks: " + txt.getContent());
        }
    }

    @Override
    public double predict(Text input) {
        return 0.8;
    }

    @Override
    public void safeTrain(Dataset<?> dataset) {
        try {
            @SuppressWarnings("unchecked")
            Dataset<Text> casted = (Dataset<Text>) dataset;
            train(casted);
        } catch (ClassCastException e) {
            System.out.println("Dataset tidak cocok untuk SVMTextModel.");
        }
    }
}