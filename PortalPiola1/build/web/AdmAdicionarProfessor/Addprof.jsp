<%-- 
    Document   : Addprof
    Created on : 2 de dez de 2023, 23:36:36
    Author     : rafae
--%>

<%@page import="entidades.Professor"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <script src="https://kit.fontawesome.com/6dda5f6271.js" crossorigin="anonymous"></script>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="AdmAdicionarProfessor/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
        <script src="./script.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/js-cookie@3.0.5/dist/js.cookie.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lexend+Exa:wght@300&display=swap" rel="stylesheet">
        <title>Piola Tech School</title>

    </head>
    <body>
        <%
            ArrayList<Professor> profs = (ArrayList<Professor>) request.getAttribute("professores");
        %>
        <header id="cabecalho">
          
            <nav>

                <ul id="menu">
                    <li class="ajuda"><strong><h2>Área do Administrador</h2></strong></li>
                    <li class="ajuda">Professores</li>
                    </li>


                </ul>
            </nav>
        </header>






        <main>

<a href="VoltarAdm" class="logomarca">
   <button>Voltar</button>
</a>
            <section class="dados">


                <div class="bp">
                    <button class="bpt" ><a href="AddProfessor" class="bpt">Adicionar Professor</a></button>
                </div> 


                <table>
                    <thead>
                        <tr>

                            <th>Nome</th>
                            <th>Email</th>
                            <th>Telefone</th>
                            <th>Ação<th>

                        </tr>
                    </thead>
                    <tbody id="professores">
                        <%
                            for (int i = 0; i < profs.size(); i++) {
                        %>
                        <tr>
                            <td><%=profs.get(i).getNome()%></td>
                            <td><%=profs.get(i).getEmail()%></td>
                            <td><%=profs.get(i).getTelefone()%></td>
                            <td>
                                <button type="button" onclick="excluir(<%= profs.get(i).getId()%>)">Excluir</button>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
                </table>
            </section>
        </main>

        <footer> 

        </footer>
        <script>
            function excluir(id) {
                window.location.href = "ExcluirProfessor?id=" + id
            }

        </script>

    </body>

</html>