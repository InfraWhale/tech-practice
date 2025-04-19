import React from 'react'
import { usePopularMoviesQuery } from '../../../../hooks/usePopularMovies'
import Alert from 'react-bootstrap/Alert';
import "./Banner.style.css"

const Banner = () => {

    const { data, isLoading, isError, error } = usePopularMoviesQuery();
    console.log("ddd", data);

    if(isLoading) {
        return <h1>Loading ...</h1>
    }
    if(isError) {
        return <Alert variant='danger'>{error.message}</Alert>;
    }

    const randomIndex = Math.floor(Math.random() * data.results.length);
    const movie = data.results[randomIndex];

  return (
    <div 
        style={{
            backgroundImage:
                "url(" + 
                `https://media.themoviedb.org/t/p/w1066_and_h600_bestv2${movie.poster_path}` + 
                ")",
        }}
        className="banner"
    >
        <div className="text-white banner-text-area" >
            <h1>{movie.title}</h1>
            <p>{movie.overview}</p>
        </div>
    </div>
  )
}

export default Banner