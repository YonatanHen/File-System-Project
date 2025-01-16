import service.FileSystem;

public class Main {
    public static void main(String[] args) {
        FileSystem fs = new FileSystem();

        fs.addFile("root", "1.txt", 20);
        fs.addFile("root", "2.txt", 500);
        fs.addDir("root", "home");
        fs.addFile("home", "3.txt", 90);
        fs.addFile("root", "5.txt", 500);
        fs.addFile("home", "7.txt", 100);
        fs.addDir("home", "photos");
        fs.showFileSystem();
    }
}