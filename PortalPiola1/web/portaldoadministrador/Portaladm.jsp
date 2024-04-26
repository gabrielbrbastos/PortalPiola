<%@page import="entidades.Disciplina"%>
<%@page import="entidades.Curso"%>
<%@page import="entidades.Professor"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="portaldoadministrador/css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Lexend+Exa:wght@300&display=swap" rel="stylesheet">
        <title>Área do Administrador</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    </head>
    <body>
        <%
            ArrayList<Professor> profs = (ArrayList<Professor>) request.getAttribute("professores");
            ArrayList<Curso> cursos = (ArrayList<Curso>) request.getAttribute("cursos");
            ArrayList<Disciplina> discs = (ArrayList<Disciplina>) request.getAttribute("discs");

        %>

        <header id="cabecalho">
            <i id="retornar"  class="bi bi-arrow-bar-left"></i>
            <nav>

                <ul id="menu">
                    <li class="ajuda"><h2>Criação de horários</h2></li>
                    </li>


                </ul>

            </nav>
        </header>




    <center>


        <div class="form-container">

            <form action="CriarHorario" id="formHorario" method="post">
                <div class="input-group">
                    <select id="Disciplina" name="Disciplina">
                        <option value="" disabled selected>Selecione uma disciplina</option>
                        <%                            for (int i = 0; i < discs.size(); i++) {
                        %>

                        <option value="<%= discs.get(i).getNome()%>"><%= discs.get(i).getNome()%></option>

                        <%
                            }
                        %>
                    </select>
                    <select id="Curso" name="Curso">
                        <option value="" disabled selected>Selecione um curso</option>
                        <%
                            for (int i = 0; i < cursos.size(); i++) {
                        %>

                        <option value="<%= cursos.get(i).getNome()%>"><%= cursos.get(i).getNome()%></option>

                        <%
                            }
                        %>
                    </select>
                    <select id="Turno" name="Turno">
                        <option value="" disabled selected>Selecione um turno</option>

                        <option value="Matutino">Matutino</option>
                        <option value="Vespertino">Vespertino</option>
                        <option value="Noturno">Noturno</option>

                    </select>
                </div>
                <div class="input-group">
                    <select id="Dia" name="Dia">
                        <option value="" disabled selected>Selecione um dia</option>

                        <option value="Segunda">Segunda</option>
                        <option value="Terça">Terça</option>
                        <option value="Quarta">Quarta</option>
                        <option value="Quista">Quinta</option>
                        <option value="Sexta">Sexta</option>
                        <option value="Sábado">Sábado</option>
                        <option value="Domingo">Domingo</option>

                    </select>
                    <input type="text" name="Horainicio" placeholder="Hora Inicio" id="Horainicio">
                    <input type="text" name="Horafim" placeholder="Hora Fim" id="Horafim">
                </div>
                <div class="input-group">
                    <select id="Professor" name="Professor">
                        <option value="" disabled selected>Selecione um professor</option>
                        <%
                            for (int i = 0; i < profs.size(); i++) {
                        %>

                        <option value="<%= profs.get(i).getNome()%>"><%= profs.get(i).getNome()%></option>

                        <%
                            }
                        %>
                    </select>
                </div>
                <p id="msgerro">&nbsp;</p>
                <div class="button-group">
                    <button type="button" class="confirm-button" onclick="validar()">Confirmar</button>
                    <button type="button" onclick="limparCampos()">Limpar</button>
                      <a href="javascript:history.back()" class="no-link-style">
        <button type="button">Voltar</button>
    </a>
 
                </div>
            </form>


        </div>
    </center>

    <script>
        function limparCampos() {
            var campoDisciplina = document.getElementById('Disciplina');
            var campoCurso = document.getElementById('Curso');
            var campoTurno = document.getElementById('Turno');
            var campoDia = document.getElementById('Dia');
            var campoHorainicio = document.getElementById('Horainicio');
            var campoHorafim = document.getElementById('Horafim');
            var campoProfessor = document.getElementById('Professor');


            campoDisciplina.value = '';
            campoCurso.value = '';
            campoTurno.value = '';
            campoDia.value = '';
            campoHorainicio.value = '';
            campoHorafim.value = '';
            campoProfessor.value = '';
        }

        function validar() {
            var campoDisciplina = document.getElementById('Disciplina');
            var campoCurso = document.getElementById('Curso');
            var campoTurno = document.getElementById('Turno');
            var campoDia = document.getElementById('Dia');
            var campoHorainicio = document.getElementById('Horainicio');
            var campoHorafim = document.getElementById('Horafim');
            var campoProfessor = document.getElementById('Professor');
            var msgErro = document.getElementById('msgerro');

            if (campoDisciplina.value === "") {
                msgErro.innerHTML = "Selecione uma disciplina";
            } else if (campoCurso.value === "") {
                msgErro.innerHTML = "Selecione um curso";
            } else if (campoTurno.value === "") {
                msgErro.innerHTML = "Selecione um turno";
            } else if (campoDia.value === "") {
                msgErro.innerHTML = "Selecione um dia";
            } else if (campoHorainicio.value === "") {
                msgErro.innerHTML = "Informe a hora de inicio";
            } else if (campoHorafim.value === "") {
                msgErro.innerHTML = "Informe a hora final";
            } else if (campoProfessor.value === "") {
                msgErro.innerHTML = "Selecione um professor";
            } else {
                document.forms["formHorario"].submit();
            }
        }
    </script>



</body>
</html>