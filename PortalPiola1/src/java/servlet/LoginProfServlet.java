/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProfessorDAO;
import entidades.Professor;

/**
 *
 */
@WebServlet(name = "LoginProfServlet", urlPatterns = {"/LoginProfServlet"})
public class LoginProfServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("LoginProfessor/loginProf.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String emailInformado = request.getParameter("EmailUser");
        String ssenha = request.getParameter("SenhaUser");

        Professor prof = new Professor();
        prof.setEmail(emailInformado);
        prof.setSenha(ssenha);
        ProfessorDAO loginsenha = new ProfessorDAO();
        int tamanhosenhas;
        String emailPego = "";
        String senhapegada = null;
        boolean sucessoLogin = false;
        System.out.println(emailInformado);
        System.out.println(ssenha);
        try {
            tamanhosenhas = loginsenha.selecionaSenha().size();
            //System.out.println(tamanhosenhas);
            for (int i = 0; i < tamanhosenhas; i++) {
                boolean senhaSucesso = false;
                //System.out.println(loginsenha.selecionaSenha().get(i).equals(adm.getSenha()));
                senhapegada = loginsenha.selecionaSenha().get(i).toString();
                //	System.out.println(senhapegada);
                if (senhapegada.equals(ssenha)) {
                    senhaSucesso = true;
                }
                if (senhaSucesso == true) {
                    // System.out.println(loginsenha.selecionarIdSenha(senhapegada));
                    emailPego = loginsenha.selecionarProfessorPorId(loginsenha.selecionarIdSenha(senhapegada));
                    //      System.out.println(emailPego);
                    if (emailPego.equals(emailInformado)) {
                        sucessoLogin = true;
                    }
                }
            }
            if (sucessoLogin == true) {
                prof = loginsenha.procurarProfessorPorEmail(emailPego);
                HttpSession session = request.getSession();
                session.setAttribute("UsuarioOnn", prof);
                request.setAttribute("professor", prof);
                RequestDispatcher rd = request.getRequestDispatcher("TelaDoProfessorServlet");
                rd.forward(request, response);

            } else //Informar o erro do login.
            {
                request.setAttribute("Erro", "Email ou Senha inválido");

                RequestDispatcher rd = request.getRequestDispatcher("LoginProfessor/loginProf.jsp");
                rd.forward(request, response);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
