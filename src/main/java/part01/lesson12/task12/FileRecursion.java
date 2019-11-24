package part01.lesson12.task12;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import javax.ejb.Stateless;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;


@Stateless
public class FileRecursion extends SimpleFileVisitor<Path> {
    static List<String> arrList = new ArrayList<>();
    private static final String dirPre = "+==";
    private static final String filePre = "+---";
    private String limit = "";
    static int level;

    public static List<String> getArrList() {
        return arrList;
    }

    public static String getDirPre() {
        return dirPre;
    }

    public static String getFilePre() {
        return filePre;
    }


    public static int getLevel() {
        return level;
    }

    @Override
    public FileVisitResult visitFile(Path dir, BasicFileAttributes attrs) throws IOException {
        limit = getlimit(dir);
        arrList.add(limit + filePre + dir.getFileName());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        limit = getlimit(dir);
        arrList.add(limit + dirPre + dir.getFileName());
        return FileVisitResult.CONTINUE;
    }

    private String getlimit(Path dir) {
        int count = 0;
        if (dir.getParent() == null)
            return "";
        while (true) {
            count++;
            dir = dir.getParent();
            if (dir.getParent() == null)
                break;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < count - level; i++) {
            stringBuilder.append(" ");

        }
        return stringBuilder.toString();
    }
}








