/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.CursoDAO;
import dao.DisciplinaDAO;
import dao.HorarioDAO;
import dao.ProfessorDAO;
import entidades.Administrador;
import entidades.Curso;
import entidades.Disciplina;
import entidades.Horario;
import entidades.Professor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rafael
 */
@WebServlet(name = "TelaDoAdmServlet", urlPatterns = {"/TelaDoAdmServlet", "/LogoutAdm", "/VoltarAdm",
    "/HorariosAdm", "/AddHorario", "/CriarHorario", "/ExcluirHorario", "/AdmBaixarHorario",
    "/ProfessoresAdm", "/AddProfessor", "/CriarProfessor", "/ExcluirProfessor",
    "/DisciplinasAdm", "/AddDisciplina", "/CriarDisciplina",
    "/CursosAdm", "/AddCurso", "/CriarCurso"})
public class TelaDoAdmServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String action = request.getServletPath();
        HttpSession session = request.getSession();

        if (session.getAttribute("UsuarioOn") == null) {
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.forward(request, response);
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("Estou no main");
            out.println(action);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String action = request.getServletPath();
        HttpSession session = request.getSession();

        if (session.getAttribute("UsuarioOn") == null) {
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.forward(request, response);
        }
        if (action.equals("/VoltarAdm")) {
            Administrador adm = (Administrador) session.getAttribute("UsuarioOn");
            request.setAttribute("administrador", adm);
            RequestDispatcher rd = request.getRequestDispatcher("TeladoAdministrador/Telaadm.jsp");
            rd.forward(request, response);
        }
        if (action.equals("/ProfessoresAdm")) {
            Administrador adm = (Administrador) session.getAttribute("UsuarioOn");
            ProfessorDAO dao = new ProfessorDAO();

            try {
                ArrayList<Professor> profs = (ArrayList<Professor>) dao.allProf();

                request.setAttribute("professores", profs);
                request.setAttribute("administrador", adm);
                RequestDispatcher rd = request.getRequestDispatcher("AdmAdicionarProfessor/Addprof.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (action.equals("/LogoutAdm")) {
            Administrador adm = (Administrador) session.getAttribute("UsuarioOn");
            try {
                RequestDispatcher rd = request.getRequestDispatcher("Sair/Sair.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                System.out.println("");
            }
        }
        if (action.equals("/AddProfessor")) {
            Administrador adm = (Administrador) session.getAttribute("UsuarioOn");
            try {
                RequestDispatcher rd = request.getRequestDispatcher("CadastrodeProfessor/Cadastroprof.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                System.out.println("");
            }
        }
        if (action.equals("/ExcluirProfessor")) {
            String idString = request.getParameter("id");
            int idInt = Integer.parseInt(idString);

            ProfessorDAO profdao = new ProfessorDAO();

            try {
                profdao.deleteProfessor(idInt);
            } catch (SQLException ex) {
                Logger.getLogger(TelaDoAdmServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestDispatcher rd = request.getRequestDispatcher("ProfessoresAdm");
            rd.forward(request, response);

        }

        if (action.equals("/HorariosAdm")) {
            Administrador adm = (Administrador) session.getAttribute("UsuarioOn");
            HorarioDAO dao = new HorarioDAO();

            try {
                ArrayList<Horario> horarios = dao.allHorario();

                request.setAttribute("horarios", horarios);
                request.setAttribute("administrador", adm);
                RequestDispatcher rd = request.getRequestDispatcher("AdmAdicionaHorario/Addhorario.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (action.equals("/AddHorario")) {
            Administrador adm = (Administrador) session.getAttribute("UsuarioOn");
            ProfessorDAO profdao = new ProfessorDAO();
            CursoDAO cursodao = new CursoDAO();
            DisciplinaDAO discdao = new DisciplinaDAO();

            try {
                ArrayList<Professor> profs = (ArrayList<Professor>) profdao.allProf();
                request.setAttribute("professores", profs);
                ArrayList<Curso> cursos = (ArrayList<Curso>) cursodao.allCurso();
                request.setAttribute("cursos", cursos);
                ArrayList<Disciplina> discs = (ArrayList<Disciplina>) discdao.allDisciplina();
                request.setAttribute("discs", discs);
                RequestDispatcher rd = request.getRequestDispatcher("portaldoadministrador/Portaladm.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                System.out.println("erro");
            }
        }
        if (action.equals("/ExcluirHorario")) {
            String idString = request.getParameter("id");

            HorarioDAO horariodao = new HorarioDAO();

            try {
                horariodao.deletarHorario(idString);
            } catch (SQLException ex) {
                Logger.getLogger(TelaDoAdmServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestDispatcher rd = request.getRequestDispatcher("HorariosAdm");
            rd.forward(request, response);

        }
        
        //SEU PDF AÍ PAIZÃO
        if (action.equals("/AdmBaixarHorario")) {
            Administrador adm = (Administrador) session.getAttribute("UsuarioOn");
            request.setAttribute("administrador", adm);
            
            
            HorarioDAO horadao = new HorarioDAO();
            Document doc = new Document();
            try{
                response.setContentType("application/pdf");
                response.addHeader("Content-Dispoition", "inline;filename" + "horarios.pdf");
                PdfWriter.getInstance(doc, response.getOutputStream());
                doc.open();
                doc.add(new Paragraph(""));
                PdfPTable tabela = new PdfPTable(6);
                PdfPCell coluna1 = new PdfPCell(new Paragraph("Professor"));
                PdfPCell coluna2 = new PdfPCell(new Paragraph("Disciplina"));
                PdfPCell coluna3 = new PdfPCell(new Paragraph("Inicio"));
                PdfPCell coluna4 = new PdfPCell(new Paragraph("Fim"));
                PdfPCell coluna5 = new PdfPCell(new Paragraph("Dia"));
                PdfPCell coluna6 = new PdfPCell(new Paragraph("Turno"));
                tabela.addCell(coluna1);
                tabela.addCell(coluna2);
                tabela.addCell(coluna3);
                tabela.addCell(coluna4);
                tabela.addCell(coluna5);
                tabela.addCell(coluna6);
                ArrayList<Horario> listaHora = (ArrayList<Horario>) horadao.allHorario();
                for(int i = 0; i < listaHora.size(); i++){
                    tabela.addCell(listaHora.get(i).getProfessor());
                    tabela.addCell(listaHora.get(i).getNome());
                    tabela.addCell(listaHora.get(i).getHorainicio());
                    tabela.addCell(listaHora.get(i).getHorafim());
                    tabela.addCell(listaHora.get(i).getDiadeaula());
                    tabela.addCell(listaHora.get(i).getTurno());
                }
                doc.add(tabela);
                doc.close();
            }catch(Exception e){
            System.out.println(e);
            doc.close();
            }
        }
        
        if (action.equals("/DisciplinasAdm")) {
            Administrador adm = (Administrador) session.getAttribute("UsuarioOn");
            DisciplinaDAO dao = new DisciplinaDAO();

            try {
                ArrayList<Disciplina> discs = (ArrayList<Disciplina>) dao.allDisciplina();

                request.setAttribute("disciplinas", discs);
                request.setAttribute("administrador", adm);
                RequestDispatcher rd = request.getRequestDispatcher("AdmAdicionarDisciplinas/Adddisc.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (action.equals("/AddDisciplina")) {
            Administrador adm = (Administrador) session.getAttribute("UsuarioOn");
            try {
                RequestDispatcher rd = request.getRequestDispatcher("CadastrodeDisciplina/Cadastrodisc.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                System.out.println("");
            }
        }
        if (action.equals("/CursosAdm")) {
            Administrador adm = (Administrador) session.getAttribute("UsuarioOn");
            CursoDAO dao = new CursoDAO();

            try {
                ArrayList<Curso> cursos = (ArrayList<Curso>) dao.allCurso();

                request.setAttribute("cursos", cursos);
                request.setAttribute("administrador", adm);
                RequestDispatcher rd = request.getRequestDispatcher("AdmAdicionarCursos/Addcurso.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (action.equals("/AddCurso")) {
            Administrador adm = (Administrador) session.getAttribute("UsuarioOn");
            try {
                RequestDispatcher rd = request.getRequestDispatcher("CadastrodeCurso/Cadastrocurso.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                System.out.println("");
            }
        }
        try (PrintWriter out = response.getWriter()) {
            out.println("Estou no doGet");
            out.println(action);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String action = request.getServletPath();
        HttpSession session = request.getSession();

        if (session.getAttribute("UsuarioOn") == null) {
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.forward(request, response);
        }
        if (action.equals("/TelaDoAdmServlet")) {
            RequestDispatcher rd = request.getRequestDispatcher("TeladoAdministrador/Telaadm.jsp");
            rd.forward(request, response);
        }

        if (action.equals("/CriarProfessor")) {
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String fone = request.getParameter("fone");

            System.out.println("Meu nome é " + nome + " meu email é " + email);

            Professor prof = new Professor();

            prof.setNome(nome);
            prof.setEmail(email);
            prof.setSenha(senha);
            prof.setTelefone(fone);

            ProfessorDAO profdao = new ProfessorDAO();

            profdao.inserirProfessor(prof);

            RequestDispatcher rd = request.getRequestDispatcher("/ProfessoresAdm");
            rd.forward(request, response);
        }
        if (action.equals("/ProfessoresAdm")) {
            Administrador adm = (Administrador) session.getAttribute("UsuarioOn");
            ProfessorDAO dao = new ProfessorDAO();

            try {
                ArrayList<Professor> profs = (ArrayList<Professor>) dao.allProf();

                request.setAttribute("professores", profs);
                request.setAttribute("administrador", adm);
                RequestDispatcher rd = request.getRequestDispatcher("AdmAdicionarProfessor/Addprof.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (action.equals("/CriarDisciplina")) {
            String nome = request.getParameter("nome");
            String turno = request.getParameter("turno");

            Disciplina disc = new Disciplina(nome, turno);

            DisciplinaDAO discdao = new DisciplinaDAO();

            discdao.inserirDisciplina(disc);

            RequestDispatcher rd = request.getRequestDispatcher("/DisciplinasAdm");
            rd.forward(request, response);
        }

        if (action.equals("/DisciplinasAdm")) {
            Administrador adm = (Administrador) session.getAttribute("UsuarioOn");
            DisciplinaDAO dao = new DisciplinaDAO();

            try {
                ArrayList<Disciplina> discs = (ArrayList<Disciplina>) dao.allDisciplina();

                request.setAttribute("disciplinas", discs);
                request.setAttribute("administrador", adm);
                RequestDispatcher rd = request.getRequestDispatcher("AdmAdicionarDisciplinas/Adddisc.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (action.equals("/CriarCurso")) {
            String nome = request.getParameter("nome");
            String turno = request.getParameter("turno");

            Curso curso = new Curso(nome, turno);

            CursoDAO cursodao = new CursoDAO();

            cursodao.inserirCurso(curso);

            RequestDispatcher rd = request.getRequestDispatcher("/CursosAdm");
            rd.forward(request, response);
        }
        if (action.equals("/CursosAdm")) {
            Administrador adm = (Administrador) session.getAttribute("UsuarioOn");
            CursoDAO dao = new CursoDAO();

            try {
                ArrayList<Curso> cursos = (ArrayList<Curso>) dao.allCurso();

                request.setAttribute("cursos", cursos);
                request.setAttribute("administrador", adm);
                RequestDispatcher rd = request.getRequestDispatcher("AdmAdicionarCursos/Addcurso.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (action.equals("/CriarHorario")) {
            String disciplina = request.getParameter("Disciplina");
            String curso = request.getParameter("Curso");
            String turno = request.getParameter("Turno");
            String dia = request.getParameter("Dia");
            String horainicio = request.getParameter("Horainicio");
            String horafim = request.getParameter("Horafim");
            String professor = request.getParameter("Professor");

            Horario horario = new Horario(disciplina, turno, professor, dia, horainicio, horafim);

            HorarioDAO horariodao = new HorarioDAO();

            horariodao.inserirHorario(horario);

            RequestDispatcher rd = request.getRequestDispatcher("/HorariosAdm");
            rd.forward(request, response);
        }
        if (action.equals("/HorariosAdm")) {
            Administrador adm = (Administrador) session.getAttribute("UsuarioOn");
            HorarioDAO dao = new HorarioDAO();

            try {
                ArrayList<Horario> horarios = dao.allHorario();

                request.setAttribute("horarios", horarios);
                request.setAttribute("administrador", adm);
                RequestDispatcher rd = request.getRequestDispatcher("AdmAdicionaHorario/Addhorario.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("Estou no doPost");
            out.println(action);

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
