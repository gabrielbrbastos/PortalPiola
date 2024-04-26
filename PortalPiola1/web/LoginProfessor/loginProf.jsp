<%@page import="entidades.Professor"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login Professor</title>
        <link rel="stylesheet" href="LoginProfessor/css/style.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Questrial">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    </head>

    <body>


        <div id="container">
            <form action="LoginProfServlet" method="post">
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="white" class="bi bi-person-fill" viewBox="0 0 16 16">
                <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
                </svg>
                </br>
                </br>
                <input type="text" class="campo" name="EmailUser" placeholder="Email">
                <input type="password" class="campo" name="SenhaUser" placeholder="Senha">
                <%
                    String erro = (String) request.getAttribute("Erro");
                    if (erro == null) {
                        erro = "";
                    }
                %>
                <button type="submit" id="btnConfirmar">Confirmar</button>
            </form>

            </br>
            <%
                String jspemail = request.getParameter("EmailUser");
                String jspSenha = request.getParameter("SenhaUser");

                Professor prof = new Professor();

                prof.setEmail(jspemail);
                prof.setSenha(jspSenha);
            %>

            <p class="msgerro"><%out.println(erro);%></p>
            <div id="setinha">
                <a href="index.html" id="setinha"> <i class="fas fa-arrow-left"></i> </a>
            </div>


        </div>


    </body>
</html>