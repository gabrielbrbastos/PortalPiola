<%@page import="entidades.Curso"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://kit.fontawesome.com/6dda5f6271.js" crossorigin="anonymous"></script>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="AdmAdicionarCursos/style.css">
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
            List<Curso> cursos = (ArrayList<Curso>) request.getAttribute("cursos");
        %>
        <header id="cabecalho">
           
            <nav>

                <ul id="menu">
                    <li class="ajuda"><strong><h2>Área do Administrador</h2></strong></li>
                    <li class="ajuda">Cursos</li>
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
                    <button class="bpt" ><a href="AddCurso" class="bpt">Adicionar Cursos</a></button>
                </div> 
                
    

                <table>
                    <thead>
                        <tr>

                            <th>Nome</th>
                            <th>Turno</th>

                        </tr>
                    </thead>
                    <tbody id="professores">
                        <%
                            for (int i = 0; i < cursos.size(); i++) {
                        %>
                        <tr>
                            <td><%=cursos.get(i).getNome()%></td>
                            <td><%=cursos.get(i).getTurno()%></td>
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

        </script>

    </body>
</html>