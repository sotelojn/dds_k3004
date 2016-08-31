
<html>
  <head>
   	<title>Sistema de POIs</title
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
  </head>
  <body>
 
  		<h1>Sistema de POIs 2.0</h1>
        <h2>Login</h2>
   <script>
  function checker(String password,String user)
        {
            var userK = "Kevin";
            var passK = "1234";
          if (passK == password && userK == user>) {
                document.login.action="login.jsp" ;
                document.login.submit();
            } else {
                alert("Usuario incorrecto");
                document.login.password.focus(); 
                return false;
            }
        }

    </script>
      <form id="login" name="login" action="html\terminal\portal.html" >
            
            <p>Usuario: <input type="text" id="user" name="user size="20" required/></p>
            <p>Contraseña: <input id="Passwd-hidden" type="password" class="hidden" name="password" size="20" required /></p>
          	
          	

   <input type="submit" name="continue" value="Continuar" onclick="checker(password,user);">

      </form>
  
      <form name="cancel" action="index.jsp" method="get" style='float:left;'>
   <input type="submit" name="cancelar" value="Cancelar" >
    </form>
 
   </body>
</html>