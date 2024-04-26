<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cadastro de Professor</title>
        <link rel="stylesheet" href="CadastrodeProfessor/css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Questrial">
    </head>
    <body>
        <div id="container">
            <form name="formProf" action="CriarProfessor" method="post">
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="white" class="bi bi-person-fill" viewBox="0 0 16 16">
                <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
                </svg>
                </br>
                </br>
                <input type="text" class="campo" id="nome" name="nome" placeholder="Nome">
                <input type="text" class="campo" id="email" name="email" placeholder="Email">
                <input type="password" class="campo" id="senha" name="senha" placeholder="Senha">
                <input type="text" class="campo" id="fone" name="fone" placeholder="Telefone">
                <button type="button" id="btnConfirmar" onclick="validar()">Confirmar</button>
            </form>

            </br>

            <p id="msgerro" class="msgerro">&nbsp;</p>

            <div id="setinha">
                <a href="javascript:history.back()" id="setinha"> <i class="fas fa-arrow-left"></i> </a>
            </div>


        </div>

        <script>
            function validar() {
                var campoNome = document.getElementById('nome');
                var campoEmail = document.getElementById('email');
                var campoSenha = document.getElementById('senha');
                var campoFone = document.getElementById('fone');
                var msgErro = document.getElementById('msgerro');

                if (campoNome.value === "") {
                    msgErro.innerHTML = "Informe um nome";
                } else if (campoEmail.value === "") {
                    msgErro.innerHTML = "Informe um E-mail";
                } else if (campoSenha.value === "") {
                    msgErro.innerHTML = "Informe uma senha";
                } else if (campoFone.value === "") {
                    msgErro.innerHTML = "Informe um telefone";
                } else {
                    document.forms["formProf"].submit();
                }
            }
        </script>

    </body>
</html>

