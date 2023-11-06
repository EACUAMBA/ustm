<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>

    <style>
        body {
            background-color: #291112;
            color: white;
        }

        #movies {
            display: grid;
            grid-template-columns: 1fr 1fr 1fr 1fr;
            grid-gap: 1rem;
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


    </style>
</head>
<body>
<div>
    <h1 style="padding: 2rem; text-align: center">USTM-FLIX</h1>
</div>
<div>
    <h2 style="padding: 1rem;">UTILIZADOR: ${utilizadorNome}</h2>
</div>
<div id="movies">
    <h2>
        ${movie.title}
    </h2>
    <p>${movie.directorName}</p>
    <span>${movie.genreFormatted()}</span>
</div>
</body>
</html>