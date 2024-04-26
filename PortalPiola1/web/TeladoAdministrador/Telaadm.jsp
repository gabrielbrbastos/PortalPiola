<%@page import="entidades.Administrador"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://kit.fontawesome.com/6dda5f6271.js" crossorigin="anonymous"></script>
        <script type="text/Javascript" src="script.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/js-cookie@3.0.5/dist/js.cookie.min.js"></script>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="TeladoAdministrador/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">    
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lexend+Exa:wght@300&display=swap" rel="stylesheet">
        <title>Piola Tech School</title>

    </head>
    <body>

        <%
            Administrador adm = (Administrador) request.getAttribute("administrador");
        %>
        <header id="cabecalho">
      
            <nav>

                <ul id="menu">


                    <li class="ajuda"><strong><h2>�rea do Administrador</h2></strong></li>
                    <li class="ajuda">In�cio</li>
                    </li>


                </ul>
            </nav>
        </header>







        <aside id="aside">
            <ul class="menu">
                <li><a href="ProfessoresAdm" class="b" onclick="cliqueDuvidas()"> Professores </a></li>

                <li><a href="DisciplinasAdm" class="b" onclick="cliqueDuvidas()"> Disciplinas</a></li>
                <li><a href="HorariosAdm"class="b" onclick="cliqueAjuda()">Hor�rios</a></li>
                <li><a href="CursosAdm" class="b" onclick="cliqueDuvidas()"> Cursos</a></li>
                <a href="LogoutAdm" class="out" onclick="cliqueSair()"> Logout</a>
            </ul>


        </aside>




        <main id="principal">
            <div class="aluno">
                <img src="./imagens/imagem 20.jpg" alt="aluno" class="imagem">
                <div class="conteudo">

                </div>
            </div>

            <div class="ajuda2">
                <p id="nome">Nome: <%= adm.getNome()%> </p>
                <p id="materia">Email: <%= adm.getEmail()%></p>
            </div>
            <img src="./imagens/fc568478-2d73-408c-8731-5f8362f6a312.jpg" alt="logo" class="logomarca">
        </main>







        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5/dist/js/bootstrap.min.js"></script>
    </body>
</html>