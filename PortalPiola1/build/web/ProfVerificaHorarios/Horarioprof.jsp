<%@page import="entidades.Professor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Horario"%>
<%@page import="java.util.List"%>
<html lang="pt-br">
    <head>
        <script src="https://kit.fontawesome.com/6dda5f6271.js" crossorigin="anonymous"></script>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="ProfVerificaHorarios/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
        <script src="./script.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/js-cookie@3.0.5/dist/js.cookie.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com%22%3E/
              <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lexend+Exa:wght@300&display=swap" rel="stylesheet">
        <title>Piola Tech School</title>

    </head>
    <body>
        <%
            List<Horario> horarios = (ArrayList<Horario>) request.getAttribute("horarios");
            Professor prof = (Professor) request.getAttribute("professor");
        %>
        <header id="cabecalho">
      
       
            <nav>
   
                <ul id="menu">
                    <li class="ajuda"><strong><h2>Área do Professor</h2></strong></li>
                    <li class="ajuda">Horários</li>
                    </li>


                </ul>
            </nav>
        </header>






        <main>

            <a href="VoltarProfessor" class="logomarca">
   <button>Voltar</button>
</a>
            
            <div class="ajuda2">
                <p id="nome">Nome: <%= prof.getNome()%></p>
                <p id="materia">Semestre: 2023.2 </p>
                    <div class="download">
                        <button>  <a href="ProfBaixarHorario">Download dos horários</a></button>

           </div>
            </div> 

            <section class="dados">


                <table>
                    <thead>
                        <tr>

                            <th>Disciplina</th>
                            <th>Horários</th>
                            <th>Dia (S)</th>
                            <th>Turno</th>

                        </tr>
                    </thead>
                    <tbody id="professores">
                        <%
                            for (int i = 0; i < horarios.size(); i++) {
                        %>
                        <tr>
                            <td><%=horarios.get(i).getNome()%></td>
                            <td><%=horarios.get(i).getHorainicio()%>-<%=horarios.get(i).getHorafim()%></td>
                            <td><%=horarios.get(i).getDiadeaula()%></td>
                            <td><%=horarios.get(i).getTurno()%></td>
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

    </body>

</html>