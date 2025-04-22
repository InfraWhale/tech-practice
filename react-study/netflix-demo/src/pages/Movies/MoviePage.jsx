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

  // ✅ 현재 화면에 보여줄 데이터 상태
  const [visibleData, setVisibleData] = useState(null);

  // ✅ 이전 URL 저장용 (정상적인 상대경로로)
  const prevUrlRef = useRef(window.location.pathname + window.location.search);

  // ✅ 페이지네이션 클릭 핸들러
  const handlePageClick = ({ selected }) => {
    setPage(selected + 1);
  };

  const isMobile = window.innerWidth <= 768;

  const pageCount = visibleData?.total_pages
  ? Math.min(visibleData.total_pages, 500) // 인기 영화는 500이 한계
  : 1;

  // ✅ data 변경 시 처리
  useEffect(() => {
    if (!data) return;

    if (keyword && data.results.length === 0) {
      alert("검색 결과가 없습니다.");

      // ✅ 이전 검색 결과가 있던 주소로 이동
      navigate(prevUrlRef.current);
    } else {
      // ✅ 정상적인 검색 결과라면 현재 URL 저장 + 화면 갱신
      prevUrlRef.current = window.location.pathname + window.location.search;
      setVisibleData(data);
    }
  }, [data, keyword, navigate]);

  // ✅ keyword 변경 시 page 초기화
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

          {visibleData?.results.length > 0 && (
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
