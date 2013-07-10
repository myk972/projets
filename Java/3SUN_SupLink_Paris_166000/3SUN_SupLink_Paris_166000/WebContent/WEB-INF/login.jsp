<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Login</title>
    <link type="text/css" rel="stylesheet" href="/form.css" />
    <header>
        <a href="/3SUN_SupLink_Paris_166000/inscription">s'inscrire</a>
    </header>
</head> <body>
<form method="post" action="login"> <fieldset>
    <legend>Login</legend>
    <p>Entrez vos identifiants</p>
    <label for="email">Adresse email <span class="requis"></span></label>
    <input type="text" id="email" name="email" value="${client.email}" size="20" maxlength="60" />
    <span class="erreur">${formulaire.erreurs['email']}</span>
    <br />
    <label for="motdepasse">Mot de passe <span class="requis"></span></label>
    <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
    <span class="erreur">${formulaire.erreurs['motdepasse']}</span>
    <br />
    <input type="submit" value="Login" class="sansLabel" />
    <br /> </fieldset>
</form> </body>
</html>