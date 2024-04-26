<%@page import="org.apache.catalina.connector.Response"%>
<%@page import="entidades.Professor"%>
<%@page import="com.mysql.cj.Session"%>
<%@page import="org.apache.catalina.connector.Request"%>
<html lang="pt-br">
<head>
    <script src="https://kit.fontawesome.com/6dda5f6271.js" crossorigin="anonymous"></script>
    <script type="text/Javascript" src="script.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/js-cookie@3.0.5/dist/js.cookie.min.js"></script>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="TelaDoProfessorCSS.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">    
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lexend+Exa:wght@300&display=swap" rel="stylesheet">
<title>Piola Tech School</title>
    
</head>



<body>
  <header id="cabecalho">
    <i id="retornar"  class="bi bi-arrow-bar-left"></i>
    <nav>
  
      <ul id="menu">

       
        <li class="ajuda"><strong><h2>Área do professor</h2></strong></li>
          <li class="ajuda">Início</li>
        </li>
      

      </ul>
    </nav>
  </header>







  <aside id="aside">
<ul class="menu">
 <li><a href="#" class="b" onclick="cliqueDuvidas()"> Horários </a></li>
 <li><a href="#"class="b" onclick="cliqueAjuda()">Ajuda</a></li>
<a class="out" href="deslogar.jsp"> Logout</a>
</ul>


  </aside>




  <main id="principal">
   <div class="aluno">
    <img src="./imagens/imagem 20.jpg" alt="aluno" class="imagem">
    <div class="conteudo">
      
    </div>
   </div>
    
     <div class="ajuda2">
       <p id="nome"><% %> </p>
       <p id="materia">Email: </p>
    </div>
    <img src="./imagens/fc568478-2d73-408c-8731-5f8362f6a312.jpg" alt="logo" class="logomarca">
 </main>
    



 


  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5/dist/js/bootstrap.min.js"></script>
  </body>
  </html>
<%

%>