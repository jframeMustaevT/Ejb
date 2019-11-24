package part01.lesson12.task12;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.util.List;

@WebServlet(urlPatterns = "/ejbservlet")
public class EjbBeanServlet  extends HttpServlet {

    @EJB
    FileRecursion fileRecursion;
    private int post;

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Path path= Paths.get("/Users/mustaevtt/apps/");
        post=getLevel(path);
        SimpleFileVisitor fileRecursion=new FileRecursion();
        FileRecursion.level=getLevel(path);
        Files.walkFileTree(path,fileRecursion);
        List<String> strings=FileRecursion.arrList;

        for (String string : strings) {
            httpServletResponse.getWriter().write(string + "\n");
        }
    }

    private int getLevel(Path dir) {
        int lev=0;
        while (true) {
            if (dir.getParent()==null)
                break;
            dir=dir.getParent();
            lev++;
        }
        return lev;
    }
}



