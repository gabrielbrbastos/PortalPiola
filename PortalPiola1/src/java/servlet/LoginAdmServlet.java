/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entidades.Administrador;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdmDAO;

/**
 *
 */
@WebServlet(name = "LoginAdmServlet", urlPatterns = {"/LoginAdmServlet"})
public class LoginAdmServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("LoginAdministrador/login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String emailInformado = request.getParameter("EmailUser");
        String senha = request.getParameter("SenhaUser");

        Administrador adm = new Administrador();
        adm.setEmail(emailInformado);
        adm.setSenha(senha);
        AdmDAO loginsenha = new AdmDAO();
        int tamanhosenhas;
        String emailPego = "";
        String senhapegada = null;
        boolean sucessoLogin = false;
        System.out.println(emailInformado);
        try {
            tamanhosenhas = loginsenha.selecionaSenha().size();
            //System.out.println(tamanhosenhas);
            for (int i = 0; i < tamanhosenhas; i++) {
                boolean senhaSucesso = false;
                //System.out.println(loginsenha.selecionaSenha().get(i).equals(adm.getSenha()));
                senhapegada = loginsenha.selecionaSenha().get(i).toString();
                //	System.out.println(senhapegada);
                if (senhapegada.equals(senha)) {
                    senhaSucesso = true;
                }
                if (senhaSucesso == true) {
                    // System.out.println(loginsenha.selecionarIdSenha(senhapegada));
                    emailPego = loginsenha.selecionarAdmnistradorPorId(loginsenha.selecionarIdSenha(senhapegada));
                    //      System.out.println(emailPego);
                    if (emailPego.equals(emailInformado)) {
                        sucessoLogin = true;
                    }
                }
            }
            if (sucessoLogin == true) {
                adm = loginsenha.procurarAdmPorEmail(emailPego);
                HttpSession session = request.getSession();
                session.setAttribute("UsuarioOn", adm);
                request.setAttribute("administrador", adm);
                RequestDispatcher rd = request.getRequestDispatcher("TelaDoAdmServlet");
                rd.forward(request, response);

            } else //Informar o erro do login.
            {
                request.setAttribute("Erro", "Email ou Senha inválido");

                RequestDispatcher rd = request.getRequestDispatcher("LoginAdministrador/login.jsp");
                rd.forward(request, response);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
