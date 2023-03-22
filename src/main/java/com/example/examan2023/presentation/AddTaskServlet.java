package com.example.examan2023.presentation;

import com.example.examan2023.beans.Task;
import com.example.examan2023.business.DefaultServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet {
    private String title;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/addTask.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        title=request.getParameter("title");
        if(title.isEmpty())
        {
            request.setAttribute("message", "all field are required");
            request.getRequestDispatcher("/WEB-INF/addTask.jsp").forward(request, response);
        }
        else
        {
            Task task=new Task();
            task.setTitle(title);
            task.setOwner(request.getSession().getId());
            task = DefaultServices.getInstance().addTask(task);
            if(task==null)
            {
                request.setAttribute("message", "please try again later");
                request.getRequestDispatcher("/WEB-INF/addTask.jsp").forward(request, response);
            }
            else
                response.sendRedirect("listTasks");
        }
    }
}

