
<%@page import="entidades.Administrador"%>
<%@page import="dao.AdmDAO"%>
<%@page import="bancodedados.Conexao"%>
<%@page import="servlet.LoginAdmServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  
          Administrador a = (Administrador) session.getAttribute("UsuarioOn");
        
        if (a == null){
            response.sendRedirect("index.html");
            }else{
              out.println("Bem vindo");
           }
        %>
      
        <a href="deslogar.jsp">Deslogar</a>
    </body>
</html>
