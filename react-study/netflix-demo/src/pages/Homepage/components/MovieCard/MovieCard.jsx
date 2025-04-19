import React from 'react';
import { Badge } from 'react-bootstrap';
import './MovieCard.style.css';

const MovieCard = ({movie}) => {
    const genreMap = {
        28: "Action",
        12: "Adventure",
        16: "Animation",
        35: "Comedy",
        80: "Crime",
        99: "Documentary",
        18: "Drama",
        10751: "Family",
        14: "Fantasy",
        36: "History",
        27: "Horror",
        10402: "Music",
        9648: "Mystery",
        10749: "Romance",
        878: "Sci-Fi",
        10770: "TV Movie",
        53: "Thriller",
        10752: "War",
        37: "Western",
      };


  return (
    <div
        style={{
            backgroundImage:
                "url(" + 
                `https://media.themoviedb.org/t/p/w600_and_h900_bestv2${movie.poster_path}` + 
                ")"
        }}
        className="movie-card"
    >
        <div className="overlay">
        <div>
            <div className="overlay-title">{movie.title}</div>
            <div className="overlay-divider"></div>
            <div className="genre-badges">
            {movie.genre_ids.map((id) => (
                <Badge bg="danger" key={id}>{genreMap[id]}</Badge>
            ))}
            </div>
        </div>

        <div className="rating-info">
            <div>⭐ {movie.vote_average}</div>
            <div>🔥 {movie.popularity.toFixed(0)}</div>
            <div>{movie.adult ? "🔞 18+" : "👶 Under 18"}</div>
        </div>
        </div>
    </div>
  )
}

export default MovieCard
