const express = require('express') // require -> commonJS
const cors = require('cors')
const movies = require('./movies.json')


const app = express()
app.use(express.json())
app.use(cors())

//Recuperar todad las peliculas

const getAllMovies = (req, res) => {
   const { genero } = req.query
   console.log(genero)
    if (genero){
        
        const moviesByGenero = movies.filter(
            movie => movie.genre.includes(genero)
        )
        res.json(moviesByGenero)
    }else{
        res.json(movies)
    }
   
}
app.get('/movies', getAllMovies)

//RECUPERAR PELICULA POR ID

const getMoviesByID = (req, res) => {
    const {id} = req.params
    const movie = movies.find(movie => movie.id === id)
    if(movie){
        res.json(movie)
    }else{
        res.status(404).send("<h1>404 : Pagina No Encontrada</h1>")
    }
}
app.get('/movies/:id', getMoviesByID)

//ENDPOINT SUBIR PELICULA

const updateMovie = (req, res) => {
    const newMovie = {
        id : crypto.randomUUID, 
        ...req.body
    } 
    movies.push(newMovie)

    res.status(201).json(newMovie)
  
}
app.post('/movies' , updateMovie)

const PORT = process.env.PORT ?? 1234
    app.listen(PORT, () => {
    console.log(`server listening on port http://localhost:${PORT}`)
})

