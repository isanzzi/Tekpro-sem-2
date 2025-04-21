// Generic inheritance, generic interface
// Membantu wildcard
// Merupakan turunan (extends) dari interface Model<T>
// Penambahan method safeTrain digunakan untuk menangani Dataset<?> yang tipe datanya bisa bermacam-macam
public interface TrainableModel<T> extends Model<T> {
    void safeTrain(Dataset<?> dataset); // menggunakan wildcard <?> artinya bisa dataset apapun
}