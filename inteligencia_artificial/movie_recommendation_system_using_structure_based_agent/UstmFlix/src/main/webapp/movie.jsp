<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>

    <style>
        body {
            background-color: #291112;
            color: white;
        }

        #area {
            display: flex;
            flex-direction: column;
            gap: 1rem;
        }

        .movie {
            padding: .5rem;
        }

        .movie {

            color: #D1D1D1;
            border: #D1D1D1 solid 2px;
            border-radius: 1rem;

            display: flex;
            gap: .5rem;
            flex-direction: column;
            justify-content: space-around;
        }

        .movie h2 {
            font-size: 2rem;
        }

        .movie p {
            font-size: 1rem;
            font-weight: bold;
        }

        .voltar {
            display: flex;
            width: 90px;
            justify-content: center;
            align-items: center;
            height: 40px;
            background-color: bisque;
            margin: .5rem;
        }


    </style>
</head>
<body>
<div>
    <h1 style="padding: 2rem; text-align: center">USTM-FLIX</h1>
</div>
<div>
    <h2 style="padding: 1rem;">UTILIZADOR: ${utilizadorNome}</h2>
</div>
<div id="area">

  <div style="display: flex; gap: 1rem">
      <article class="movie">
          <h2>
              ${movie.title}
          </h2>
          <p>${movie.directorName}</p>
          <p>${movie.actorOneName}, ${movie.actorTwoName}, ${movie.actorThreeName}</p>
          <span>${movie.genreFormatted()}</span>
      </article>

      <div>
          <form action="sugestoes">
              <div>
                <label for="">Gostou do Filme de ${movie.directorName}</label>
                <input type="checkbox" name="${movie.directorName}" class="DirectorLike">
              </div>
              <div>
                  <label for="">Gostou do Filme com o Actor(a) ${movie.actorOneName}</label>
                  <input type="checkbox" class="ActorOneNameLike" name="${movie.actorOneName}">
              </div>
              <div>
                <label for="">Gostou do Filme com Actor(a) ${movie.actorTwoName}}</label>
                <input type="checkbox" class="ActorTwoNameLike" name="${movie.actorTwoName}">
              </div>
              <div>
                  <label for="">Gostou do Filme com Actor(a) ${movie.actorThreeName}</label>
                  <input type="checkbox" name="${movie.actorThreeName}">
              </div>
              <c:forEach items="${movie.genreList}" var="genre">
                  <div>
                      <label for="">Gostou do genero ${genre}</label>
                      <input type="checkbox" name="${genre}">
                  </div>
              </c:forEach>

              <input type="text"  name="Enviar sugestões">

          </form>
      </div>
  </div>

    <a href="./?userId=<%= request.getParameter("userId")%>" class="voltar">Voltar</a>


    <diV>
        <form action="">

        </form>
    </diV>
</div>
</body>
</html>