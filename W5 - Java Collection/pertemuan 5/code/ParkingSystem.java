import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

// 2. Record untuk menyimpan informasi kendaraan secara immutable
record Vehicle(String licensePlate, String type, boolean isVIP) {}

public class ParkingSystem {
    // 1. List untuk menyimpan kendaraan yang sedang parkir
    private final List<Vehicle> parkedVehicles = new CopyOnWriteArrayList<>();

    // 1. Set untuk menyimpan daftar kendaraan VIP tanpa duplikasi
    private final Set<String> vipVehicles = ConcurrentHashMap.newKeySet();

    // 1. Map untuk menyimpan slot parkir berdasarkan nomor kendaraan
    private final Map<String, Integer> parkingSlots = new ConcurrentHashMap<>();

    // 4. Concurrent Queue untuk antrean kendaraan yang mencari parkir
    private final Queue<Vehicle> waitingQueue = new ConcurrentLinkedQueue<>();

    // 6. Immutable Collection untuk daftar jenis kendaraan yang diperbolehkan parkir
    private final Set<String> allowedVehicleTypes = Set.of("Car", "Motorcycle", "Truck");

    private final int maxSlots = 10;

    public void parkVehicle(String licensePlate, String type, boolean isVIP) {
        // 3. Optional untuk memeriksa apakah jenis kendaraan diperbolehkan
        Optional<String> validType = Optional.ofNullable(allowedVehicleTypes.contains(type) ? type : null);

        validType.ifPresentOrElse(v -> {
            Vehicle vehicle = new Vehicle(licensePlate, type, isVIP);
            if (parkedVehicles.size() < maxSlots) {
                parkedVehicles.add(vehicle);
                int slot = parkedVehicles.size();
                parkingSlots.put(licensePlate, slot);
                if (isVIP) vipVehicles.add(licensePlate);
                System.out.println("Kendaraan diparkir di slot " + slot);
            } else {
                waitingQueue.offer(vehicle); // 5. Menambahkan kendaraan ke antrean jika penuh
                System.out.println("Parkiran penuh! Kendaraan masuk antrean.");
            }
        }, () -> System.out.println("Jenis kendaraan tidak diperbolehkan!"));
    }

    public void exitVehicle(String licensePlate) {
        parkedVehicles.removeIf(v -> v.licensePlate().equals(licensePlate));
        parkingSlots.remove(licensePlate);
        vipVehicles.remove(licensePlate);

        // 5. Jika ada kendaraan dalam antrean, masukkan ke slot yang kosong
        if (!waitingQueue.isEmpty()) {
            Vehicle nextVehicle = waitingQueue.poll();
            parkedVehicles.add(nextVehicle);
            int slot = parkedVehicles.size();
            parkingSlots.put(nextVehicle.licensePlate(), slot);
            System.out.println("Kendaraan dari antrean masuk ke slot " + slot);
        }
    }

    public void printParkedVehicles() {
        parkedVehicles.forEach(System.out::println);
    }
}
