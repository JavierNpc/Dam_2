const express = require('express') // require -> commonJS
const cors = require('cors')
const movies = require('./movies.json')


const app = express()
app.use(express.json())
app.use(cors())

//Recuperar todad las peliculas

const getAllMovies = (req, res) => {

   const {genero} = req.query

   res.json(movies)
}
app.get('/movies', getAllMovies)


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





const PORT = process.env.PORT ?? 1234
    app.listen(PORT, () => {
    console.log(`server listening on port http://localhost:${PORT}`)
})

