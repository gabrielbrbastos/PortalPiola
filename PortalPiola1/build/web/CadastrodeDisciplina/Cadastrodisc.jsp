<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cadastro de Disciplina</title>
        <link rel="stylesheet" href="CadastrodeDisciplina/css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Questrial">
    </head>
    <body>
        <div id="container">
            <form action="CriarDisciplina" method="post" id="formDisc">
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="white" class="bi bi-person-fill" viewBox="0 0 16 16">
                <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
                </svg>
                </br>
                </br>
                <input type="text" class="campo" id="nome" name="nome" placeholder="Nome da disciplina">
                <select id="turno" name="turno">
                    <option value="" disabled selected>Selecione um turno</option>

                    <option value="Matutino">Matutino</option>
                    <option value="Vespertino">Vespertino</option>
                    <option value="Noturno">Noturno</option>

                </select>
                <p id="msgerro">&nbsp;</p>
                <button type="button" id="btnConfirmar" onclick="validar()">Confirmar</button>
            </form>

            </br>


            <div id="setinha">
                <a href="javascript:history.back()" id="setinha"> <i class="fas fa-arrow-left"></i> </a>
            </div>

        </div>
        <script>
            function validar() {
                var campoNome = document.getElementById('nome');
                var campoTurno = document.getElementById('turno');
                var msgErro = document.getElementById('msgerro');

                if (campoNome.value === "") {
                    msgErro.innerHTML = "Informe um nome";
                } else if (campoTurno.value === "") {
                    msgErro.innerHTML = "Selecione um turno";
                } else {
                    document.forms["formDisc"].submit();
                }
            }
        </script>

    </body>

</html>