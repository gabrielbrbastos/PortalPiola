<%@page import="dao.HorarioDAO"%>
<%@page import="entidades.Horario"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Administrador"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://kit.fontawesome.com/6dda5f6271.js" crossorigin="anonymous"></script>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="AdmAdicionaHorario/style.css">
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
            List<Horario> horarios = (ArrayList<Horario>) request.getAttribute("horarios");
        %>
        <header id="cabecalho">
       
            <nav>

                <ul id="menu">
                    <li class="ajuda"><strong><h2>Área do Administrador</h2></strong></li>
                    <li class="ajuda">Horários</li>
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
                    <button class="bpt" ><a href="AddHorario" class="bpt">Adicionar Horário</a></button>
                </div> 

                <center>
                      <div class="download">
                          <button><a href="AdmBaixarHorario">   Download dos horários</a></button>

           </div>
                </center>
                <table>
                    <thead>
                        <tr>
                            <th>Professor</th>
                            <th>Disciplina</th>
                            <th>Horários</th>
                            <th>Dia</th>
                            <th>Turno</th>
                            <th>Ação</th>

                        </tr>
                    </thead>
                    
                    
                    <tbody id="professores">
                        <%
                            for (int i = 0; i < horarios.size(); i++) {
                        %>
                        <tr>
                            <td><%=horarios.get(i).getProfessor()%></td> 
                            <td><%=horarios.get(i).getNome()%></td>
                            <td><%=horarios.get(i).getHorainicio()%>-<%=horarios.get(i).getHorafim()%></td>
                            <td><%=horarios.get(i).getDiadeaula()%></td>
                            <td><%=horarios.get(i).getTurno()%></td>                       
                            <td>
                                <button type="button" onclick="excluir(<%= horarios.get(i).getId() %>)">Excluir</button>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </section>
        </main>

        <footer> 

        </footer>
                    <script>
                      function excluir(id){
                          window.location.href="ExcluirHorario?id="+id
                      }
      
                    </script>
    </body>
</html>