public class VideoModel implements TrainableModel<Video> {
    @Override
    public void train(Dataset<Video> dataset) {
        for (Video v : dataset.getData()) {
            System.out.println("Belajar dari video: " + v.getTitle() + " durasi: " + v.getDurationSeconds() + " detik");
        }
    }

    @Override
    public double predict(Video input) {
        return 0.75;
    }

    @Override
    public void safeTrain(Dataset<?> dataset) {
        try {
            @SuppressWarnings("unchecked")
            Dataset<Video> casted = (Dataset<Video>) dataset;
            train(casted);
        } catch (ClassCastException e) {
            System.out.println("Dataset tidak cocok untuk VideoModel.");
        }
    }
}