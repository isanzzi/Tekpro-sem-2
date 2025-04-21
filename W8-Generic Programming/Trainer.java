import java.util.List;

// Ini contoh penggunaan wildcard: ? extends TrainableModel<?>
// Artinya kita bisa menerima list model dari jenis apapun selama mereka turunan TrainableModel
// Contoh: TrainableModel<Image>, TrainableModel<Text>, dst
public class Trainer {
    public static void trainAll(List<? extends TrainableModel<?>> models, Dataset<?> dataset) {
        for (TrainableModel<?> model : models) {
            System.out.println("Training model...");
            model.safeTrain(dataset); // safeTrain digunakan karena dataset tipe-nya tidak pasti
        }
    }
}
