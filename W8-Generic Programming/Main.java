// Main.java
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Image> images = List.of(new Image("img1.png"), new Image("img2.png"));
        Dataset<Image> imageDataset = new ImageDataset(images);
        KNNImageModel imageModel = new KNNImageModel();
        Experiment<KNNImageModel, Image> imageExperiment = new Experiment<>(imageModel, imageDataset);
        imageExperiment.run();

        List<Text> texts = List.of(new Text("Satu"), new Text("Dua"));
        Dataset<Text> textDataset = new Dataset<>(texts);
        SVMTextModel textModel = new SVMTextModel();
        Experiment<SVMTextModel, Text> textExperiment = new Experiment<>(textModel, textDataset);
        textExperiment.run();

        List<ImageText> imageTexts = List.of(new ImageText("imgA.png", "Kucing"), new ImageText("imgB.png", "Anjing"));
        Dataset<ImageText> multiDataset = new Dataset<>(imageTexts);
        MultiModalModel multiModel = new MultiModalModel();
        Experiment<MultiModalModel, ImageText> multiExperiment = new Experiment<>(multiModel, multiDataset);
        multiExperiment.run();

        List<Video> videos = List.of(new Video("Tutorial Java", 300), new Video("Intro ML", 420));
        Dataset<Video> videoDataset = new Dataset<>(videos);
        VideoModel videoModel = new VideoModel();
        Experiment<VideoModel, Video> videoExperiment = new Experiment<>(videoModel, videoDataset);
        videoExperiment.run();

        System.out.println("--- Latihan Massal ---");
        List<TrainableModel<?>> allModels = List.of(imageModel, textModel, multiModel, videoModel);
        Trainer.trainAll(allModels, textDataset); // contoh pelatihan massal dengan wildcard
    }
}
