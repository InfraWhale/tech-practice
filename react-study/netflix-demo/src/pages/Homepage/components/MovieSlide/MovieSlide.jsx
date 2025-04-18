import React from 'react'
import { Alert } from 'bootstrap';
import Carousel from 'react-multi-carousel';
import MovieCard from '../MovieCard/MovieCard';
import 'react-multi-carousel/lib/styles.css';
import './MovieSlide.style.css';

const MovieSlide = ({ useMovieQuery }) => {

    const {data, isLoading, isError, error} = useMovieQuery();
    console.log("pms data : ", data);

    if(isLoading) {
        return <h1>Loading...</h1>
    }
    if(isError) {
        return <Alert variant='danger'>{error.message}</Alert>;
    }

    const responsive = {
        desktop: {
          breakpoint: { max: 3000, min: 1024 },
          items: 6,
        },
        tablet: {
          breakpoint: { max: 1024, min: 768 },
          items: 2,
        },
        mobile: {
          breakpoint: { max: 768, min: 0 },
          items: 1,
        }
    };

  return (
    <div>
        <Carousel
            infinite={true}
            centerMode={false}
            itemClass="movie-slider p-1"
            containerClass="carousel-container"
            responsive={responsive}
        >
        {data.results.map((movie, index) => (
            <MovieCard movie={movie} key={index} />))}
        </Carousel>
    </div>
  )
}

export default MovieSlide