import React from 'react'
import Banner from './components/Banner/Banner'
import './Homepage.style.css';
import MovieSlide from './components/MovieSlide/MovieSlide';
import { usePopularMoviesQuery } from '../../hooks/usePopularMovies';
import { useTopRatedMoviesQuery } from '../../hooks/useTopRatedMovies';
import { useUpcomingMoviesQuery } from '../../hooks/useUpcomingMovies';

// 1. 배너 => popular 영화를 들고와서 첫번째 아이템 보여주자
// 2. popular movie
// 3. top rated
// 4. upcoming

const Homepage = () => {
  return (
    <div>
      <Banner />
      <MovieSlide useMovieQuery={usePopularMoviesQuery} title="Popular Movies"/>
      <MovieSlide useMovieQuery={useTopRatedMoviesQuery} title="Top Rated"/>
      <MovieSlide useMovieQuery={useUpcomingMoviesQuery} title="Upcoming"/>
    </div>
  )
}

export default Homepage