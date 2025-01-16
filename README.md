# File System Project

### Description
This program handles and manage a file system structure.

The file system contains the following entities and operations:
A File has the following attributes:

  ● name - up to 32 characters 
  
  ● size - positive long integer
  
  ● creation date (date type)

A Directory has the following attributes:

  ● name - up to 32 characters long
  
  ● creation date (date type)
  
  ● A directory can contain directories or files
  

### Development Process
This project was developed with OpenJDK-23 on IntelliJ community IDE. 
Each feature developed on a different branch and then merged into the main branch.

In addition, tests were created. In order to run them, please make sure JUnit is installed on this project.

### Output example
Attached below is an output example for the following code snippet:
```
FileSystem fs = new FileSystem();

fs.addFile("root", "1.txt", 20);
fs.addFile("root", "2.txt", 500);
fs.addDir("root", "home");
fs.addFile("home", "3.txt", 90);
fs.addFile("root", "5.txt", 499);
fs.addFile("home", "7.txt", 100);
fs.addDir("home", "photos");
fs.addDir("photos", "music");
fs.showFileSystem();
System.out.println("Biggest file in the system is: " + fs.getBiggestFile());
System.out.println("The size of file 1.txt is: " + fs.getFileSize("1.txt"));
fs.delete("home");
fs.delete("2.txt");

fs.showFileSystem();
```
Output:
```
Directory | root | Thu Jan 16 13:16:30 IST 2025
  File | 1.txt | Thu Jan 16 13:16:30 IST 2025 | 20
  File | 2.txt | Thu Jan 16 13:16:30 IST 2025 | 500
  Directory | home | Thu Jan 16 13:16:30 IST 2025
    File | 3.txt | Thu Jan 16 13:16:30 IST 2025 | 90
    File | 7.txt | Thu Jan 16 13:16:30 IST 2025 | 100
    Directory | photos | Thu Jan 16 13:16:30 IST 2025
      Directory | music | Thu Jan 16 13:16:30 IST 2025
  File | 5.txt | Thu Jan 16 13:16:30 IST 2025 | 499
Biggest file in the system is: 2.txt
The size of file 1.txt is: 20
Item "home" has been deleted.
Item "2.txt" has been deleted.
Directory | root | Thu Jan 16 13:16:30 IST 2025
  File | 1.txt | Thu Jan 16 13:16:30 IST 2025 | 20
  File | 5.txt | Thu Jan 16 13:16:30 IST 2025 | 499
```
