import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

public class EjercicioDos {
    public static List<String> lecturaDelFitxer (String camiFont) throws IOException {
        Path cami = Paths.get(camiFont);
        return Files.readAllLines(cami);
    }

    public static void escripturaAlFitxer (String camiDesti, List<String> contingut) throws IOException {
        Path cami = Paths.get(camiDesti);
        if (Files.exists(cami)) {
            throw new FileAlreadyExistsException("El fitxer ja existeix.");
        }
        Files.write(cami, contingut);
    }

    public static void main (String[] args) {
        Scanner scan = new Scanner (System.in);

        try {
            System.out.println("Introdueix el camí del fitxer que vols llegir:");
            String camiFont = scan.nextLine();
            List<String> contingut = lecturaDelFitxer(camiFont);

            System.out.println("Introdueix el camí del fitxer on vols escriure:");
            String camiDesti = scan.nextLine();
            List<String> majuscules = contingut.stream().map(String::toUpperCase).toList();
            escripturaAlFitxer(camiDesti, majuscules);
            System.out.println("S'ha escrit al fitxer amb èxit.");
        } catch (NoSuchFileException e) {
            System.err.println("ERROR: El fitxer no existeix.");
        } catch (FileAlreadyExistsException e) {
            System.err.println("ERROR: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scan.close();
            System.out.println("Programa finalitzat.");
        }
    }
}
