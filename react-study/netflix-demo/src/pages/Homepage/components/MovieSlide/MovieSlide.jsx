import React from 'react'
import { Alert } from 'bootstrap';
import 'react-multi-carousel/lib/styles.css';
import './MovieSlide.style.css';
import MovieSlider from '../../../../common/MovieSlider/MovieSlider';
import { responsive } from '../../../../constants/responsive';

const MovieSlide = ({ useMovieQuery, title }) => {

    const {data, isLoading, isError, error} = useMovieQuery();

    if(isLoading) {
        return <h1>Loading...</h1>
    }
    if(isError) {
        return <Alert variant='danger'>{error.message}</Alert>;
    }

  return (
    <div>
        <MovieSlider title={title} movies={data.results} responsive={responsive} />
    </div>
  )
}

export default MovieSlide