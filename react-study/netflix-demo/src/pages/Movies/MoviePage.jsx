import React, { useState, useEffect, useRef } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';
import { Spinner, Alert, Container, Row, Col } from 'react-bootstrap';
import MovieCard from '../../common/MovieCard/MovieCard';
import ReactPaginate from 'react-paginate';
import { useSearchMovieQuery } from '../../hooks/useSearchMovie';
import './MoviePage.style.css';

const MoviePage = () => {
  const [query] = useSearchParams();
  const [page, setPage] = useState(1);
  const keyword = query.get("q");
  const navigate = useNavigate();

  const { data, isLoading, isError, error } = useSearchMovieQuery({ keyword, page });

  // ✅ 현재 화면에 보여줄 데이터
  const [visibleData, setVisibleData] = useState(null);

  // ✅ 이전에 성공했던 검색 키워드 기억
  const lastSuccessfulKeywordRef = useRef(null);

  const handlePageClick = ({ selected }) => {
    setPage(selected + 1);
  };

  const isMobile = window.innerWidth <= 768;

  const pageCount = visibleData?.total_pages
    ? Math.min(visibleData.total_pages, 500)
    : 1;

  // ✅ 검색 결과 수신 시 처리
  useEffect(() => {
    if (!data) return;

    if (keyword && data.results.length === 0) {
      alert("검색 결과가 없습니다.");

      // ✅ 직전 성공 검색 기록이 있다면 해당 키워드로 이동
      if (lastSuccessfulKeywordRef.current) {
        navigate(`/movies?q=${lastSuccessfulKeywordRef.current}`);
      } else {
        navigate("/movies");
      }
    } else {
      // ✅ 정상 검색 결과일 경우 화면 갱신
      lastSuccessfulKeywordRef.current = keyword;
      setVisibleData(data);
    }
  }, [data, keyword, navigate]);

  // ✅ 검색어 변경 시 페이지 초기화
  useEffect(() => {
    setPage(1);
  }, [keyword]);

  if (isLoading && !visibleData) {
    return (
      <div className="spinner-area">
        <Spinner animation="border" variant="danger" style={{ width: '5rem', height: '5rem' }} />
      </div>
    );
  }

  if (isError) {
    return <Alert variant="danger">{error.message}</Alert>;
  }

  return (
    <Container>
      <Row>
        <Col lg={4} xs={12}>
          필터
        </Col>
        <Col lg={8} xs={12}>
          <Row>
            {visibleData?.results.map((movie, index) => (
              <Col key={index} lg={4} xs={12}>
                <MovieCard movie={movie} />
              </Col>
            ))}
          </Row>

          {visibleData?.total_pages > 1 && (
            <div className="pagination-wrapper">
              <ReactPaginate
                nextLabel=">"
                onPageChange={handlePageClick}
                pageRangeDisplayed={isMobile ? 2 : 3}
                marginPagesDisplayed={isMobile ? 1 : 2}
                pageCount={pageCount}
                previousLabel="<"
                pageClassName="page-item"
                pageLinkClassName="page-link"
                previousClassName="page-item"
                previousLinkClassName="page-link"
                nextClassName="page-item"
                nextLinkClassName="page-link"
                breakLabel="..."
                breakClassName="page-item"
                breakLinkClassName="page-link"
                containerClassName="pagination"
                activeClassName="active"
                renderOnZeroPageCount={null}
                forcePage={page - 1}
              />
            </div>
          )}
        </Col>
      </Row>
    </Container>
  );
};

export default MoviePage;
