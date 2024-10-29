import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.google.gson.Gson;

@WebServlet("/GradeCalculatorServlet")
public class GradeCalculatorServlet extends HttpServlet {

    private static class StudentData {
        String name;
        int math;
        int science;
        int english;
    }

    private static class GradeResponse {
        String grade;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        
        // Parse JSON data
        Gson gson = new Gson();
        StudentData studentData = gson.fromJson(request.getReader(), StudentData.class);

        // Calculate average and grade
        int average = (studentData.math + studentData.science + studentData.english) / 3;
        String grade = calculateGrade(average);

        // Send JSON response
        GradeResponse gradeResponse = new GradeResponse();
        gradeResponse.grade = grade;
        response.getWriter().write(gson.toJson(gradeResponse));
    }

    private String calculateGrade(int average) {
        if (average >= 90) return "A";
        else if (average >= 80) return "B";
        else if (average >= 70) return "C";
        else if (average >= 60) return "D";
        else return "F";
    }
}
