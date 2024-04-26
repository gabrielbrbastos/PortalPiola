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
import dao.HorarioDAO;
import entidades.Horario;
import entidades.Professor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "TelaDoProfessorServlet", urlPatterns = {"/TelaDoProfessorServlet", "/Horarios", "/VoltarProfessor", "/LogoutProf", "/AjudaProfessor", "/ProfBaixarHorario"})
public class TelaDoProfessorServlet extends HttpServlet {

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
        String action = request.getServletPath();
        try (PrintWriter out = response.getWriter()) {
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
        //processRequest(request, response);
        String action = request.getServletPath();

        HttpSession session = request.getSession();

        if (session.getAttribute("UsuarioOnn") == null) {
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.forward(request, response);
        }
        if (action.equals("/VoltarProfessor")) {
            Professor prof = (Professor) session.getAttribute("UsuarioOnn");
            request.setAttribute("professor", prof);
            RequestDispatcher rd = request.getRequestDispatcher("TeladoProfessor/TelaDoProfessor.jsp");
            rd.forward(request, response);
        }

        if (action.equals("/AjudaProfessor")) {
            Professor prof = (Professor) session.getAttribute("UsuarioOnn");
            request.setAttribute("professor", prof);
            RequestDispatcher rd = request.getRequestDispatcher("Ajuda/Ajuda.jsp");
            rd.forward(request, response);
        }

        if (action.equals("/Horarios")) {
            Professor prof = (Professor) session.getAttribute("UsuarioOnn");
            HorarioDAO dao = new HorarioDAO();

            try {
                ArrayList<Horario> horarios = dao.horarioProfessor(prof.getNome());

                request.setAttribute("horarios", horarios);
                request.setAttribute("professor", prof);
                RequestDispatcher rd = request.getRequestDispatcher("ProfVerificaHorarios/Horarioprof.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        
         if (action.equals("/ProfBaixarHorario")) {
            Professor prof = (Professor) session.getAttribute("UsuarioOnn");
            request.setAttribute("professor", prof);
            
            
            HorarioDAO horadao = new HorarioDAO();
            Document doc = new Document();
            try{
                response.setContentType("application/pdf");
                response.addHeader("Content-Dispoition", "inline;filename" + "horarios.pdf");
                PdfWriter.getInstance(doc, response.getOutputStream());
                doc.open();
                doc.add(new Paragraph(""));
                PdfPTable tabela = new PdfPTable(5);
                PdfPCell coluna1 = new PdfPCell(new Paragraph("Disciplina"));
                PdfPCell coluna2 = new PdfPCell(new Paragraph("Inicio"));
                PdfPCell coluna3 = new PdfPCell(new Paragraph("Fim"));
                PdfPCell coluna4 = new PdfPCell(new Paragraph("Dia"));
                PdfPCell coluna5 = new PdfPCell(new Paragraph("Turno"));
                tabela.addCell(coluna1);
                tabela.addCell(coluna2);
                tabela.addCell(coluna3);
                tabela.addCell(coluna4);
                tabela.addCell(coluna5);
                ArrayList<Horario> listaHora = (ArrayList<Horario>) horadao.allHorario();
                for(int i = 0; i < listaHora.size(); i++){
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
        
        if (action.equals("/LogoutProf")) {
            Professor prof = (Professor) session.getAttribute("UsuarioOnn");
            try {
                RequestDispatcher rd = request.getRequestDispatcher("SairProf/SairProf.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                System.out.println("");
            }
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

        String action = request.getServletPath();
        HttpSession session = request.getSession();

        if (session.getAttribute("UsuarioOnn") == null) {
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.forward(request, response);
        }

        if (action.equals("/TelaDoProfessorServlet")) {
            RequestDispatcher rd = request.getRequestDispatcher("TeladoProfessor/TelaDoProfessor.jsp");
            rd.forward(request, response);
        }

        try (PrintWriter out = response.getWriter()) {
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
