import { React, useMemo } from 'react';
import { Badge } from 'react-bootstrap';
import './MovieCard.style.css';
import { useMovieGenreQuery } from '../../hooks/useMovieGenre';

const MovieCard = ({movie}) => {
    const DEFAULT_IMAGE = "https://i.pinimg.com/736x/bb/23/46/bb2346582caedef6034cb425150edcbc.jpg";
    const {data:genreData} = useMovieGenreQuery();
    //   console.log("ggg", genreData);

    //   const showGenre = (genreIdList) => {
    //     if(!genreData) return[];
    //     const genreNameList= genreIdList.map((id) => {
    //         const genreObj = genreData.find((genre) => genre.id === id);
    //         return genreObj.name;
    //     })
    //     return genreNameList;
    //   }

    const genreMap = useMemo(() => {
        if (!genreData) return {};
        return genreData.reduce((acc, genre) => {
          acc[genre.id] = genre.name;
          return acc;
        }, {});
      }, [genreData]);

  return (
    <div
    style={{
        backgroundImage: `url(${movie.poster_path
          ? `https://media.themoviedb.org/t/p/w600_and_h900_bestv2${movie.poster_path}`
          : DEFAULT_IMAGE})`,
      }}
        className="movie-card"
    >
        <div className="overlay">
        <div>
            <div className="overlay-title">{movie.title}</div>
            <div className="overlay-divider"></div>
            {/* <div className="genre-badges">
                {showGenre(movie.genre_ids).map((genre, index) => (
                    <Badge bg="danger" key={index}>{genre}</Badge>
                ))}
            </div> */}

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
