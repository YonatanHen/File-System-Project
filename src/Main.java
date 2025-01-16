import service.FileSystem;

public class Main {
    public static void main(String[] args) {
        FileSystem fs = new FileSystem();

        fs.addFile("root", "1.txt", 20);
        fs.addFile("root", "2.txt", 500);
        fs.addDir("root", "home");
        fs.addFile("home", "3.txt", 90);
        fs.addFile("root", "5.txt", 499);
        fs.addFile("home", "7.txt", 100);
        fs.addDir("home", "photos");
        fs.showFileSystem();
        System.out.println("Biggest file in the system is: " + fs.getBiggestFile());
        System.out.println("The size of file 1.txt is: " + fs.getFileSize("1.txt"));
        fs.delete("home");
        fs.delete("2.txt");
        fs.delete("root");
        fs.showFileSystem();
    }
}