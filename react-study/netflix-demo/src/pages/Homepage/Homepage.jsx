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
      <h1 className='slide-title'>Popular Movies</h1>
      {/* <PopularMovieSlide /> */}
      <MovieSlide useMovieQuery={usePopularMoviesQuery} />
      <h1 className='slide-title'>Top Rated</h1>
      <MovieSlide useMovieQuery={useTopRatedMoviesQuery} />
      {/* <TopRatedMovieSlide /> */}
      <h1 className='slide-title'>Upcoming</h1>
      <MovieSlide useMovieQuery={useUpcomingMoviesQuery} />
      {/* <UpcomingMovieSlide /> */}
    </div>
  )
}

export default Homepage