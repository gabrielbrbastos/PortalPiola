<%@page import="entidades.Administrador"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="Sair/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
        integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Registro Professor</title>
    <link href="https://fonts.googleapis.com/css2?family=Lexend+Exa:wght@300&display=swap" rel="stylesheet">
</head>

<body>
    <%
    Administrador adm = new Administrador();
    request.getAttribute("UsuarioOn");
    
    adm.getNome();
    adm.getEmail();
    %>
    <div class="notificationCard">
        <p class="notificationHeading">Deseja sair?</p>
        <svg class="bellIcon" viewBox="0 0 448 512"><path d="M224 0c-17.7 0-32 14.3-32 32V51.2C119 66 64 130.6 64 208v18.8c0 47-17.3 92.4-48.5 127.6l-7.4 8.3c-8.4 9.4-10.4 22.9-5.3 34.4S19.4 416 32 416H416c12.6 0 24-7.4 29.2-18.9s3.1-25-5.3-34.4l-7.4-8.3C401.3 319.2 384 273.9 384 226.8V208c0-77.4-55-142-128-156.8V32c0-17.7-14.3-32-32-32zm45.3 493.3c12-12 18.7-28.3 18.7-45.3H224 160c0 17 6.7 33.3 18.7 45.3s28.3 18.7 45.3 18.7s33.3-6.7 45.3-18.7z"></path></svg>
      <p class="notificationPara">Ao clicar em sair voc� sera redirecionado para a p�gina inicial</p>
        <div class="buttonContainer">
            <button id="sair" class="AllowBtn" onclick="cliqueSair()"><a href="deslogar.jsp"> Sair</a></button> 
          <button id="voltar" class="NotnowBtn" onclick="voltar()"><a href="VoltarProfessor">Voltar</a></button>
        </div>
      </div>
</body>

</html>