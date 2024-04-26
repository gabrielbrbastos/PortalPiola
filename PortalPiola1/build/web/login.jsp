<%-- 
    Document   : controle
    Created on : 07/11/2023
    Author     : Gabriel Borges
--%>

<%@page import="dao.AdmDAO"%>
<%@page import="entidades.Administrador"%>
<%@ page import="java.sql.*" %>

        <%
        String erro = (String) request.getAttribute("Erro");
        %>
      <html>
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Administrador</title>
    <link rel="stylesheet" href="./style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Questrial">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

    </head>
    <body>
      <div id="container">
            <form action="LoginAdmServlet" method="post">
             <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="white" class="bi bi-person-fill" viewBox="0 0 16 16">
                <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
             </svg>
             </br>
             </br>
             <input type="text" class="campo" name="EmailUser" placeholder="Email">
             <input type="password" class="campo" name="SenhaUser" placeholder="Senha">
             <button href="login.jsp" type="submit" id="btnConfirmar">Confirmar</button>
            </form>
    </br>
           <a><%out.println(erro);%></a>
           <br>
        <div id="setinha">
            <a href="index.html" id="setinha"> <i class="fas fa-arrow-left"></i> </a>
        </div>
    

    
                 </div>
    </body>
     
</html>

        <%
        String jspemail = request.getParameter("EmailUser");
        String jspSenha = request.getParameter("SenhaUser");
        
        Administrador adm = new Administrador();
        
        adm.setEmail(jspemail);
        adm.setSenha(jspSenha);
        %>
  

