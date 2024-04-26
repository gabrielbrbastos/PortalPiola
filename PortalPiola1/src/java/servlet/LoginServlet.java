/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;
import entidades.Administrador;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("EmailUser");
        String senha = request.getParameter("SenhaUser");
        
        Administrador adm = new Administrador();
        adm.setEmail(email);
        adm.setSenha(senha);
                 
        boolean loginSucesso = false;
        if (adm.getSenha().equals(senha)){
         loginSucesso = true;
            }
        if(loginSucesso == true){
            HttpSession session = request.getSession();
            session.setAttribute("UsuarioOn", adm);
         RequestDispatcher rd = request.getRequestDispatcher("/InterfaceADM.jsp");
         rd.forward(request, response);
        
        }else{
            //Informar o erro do login.
            if(loginSucesso == false);
            request.setAttribute("Erro", "Email/Senha estao incorretos.");
            
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
                }
        }
    }
