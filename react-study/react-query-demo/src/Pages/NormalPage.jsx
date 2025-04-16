import React, { useEffect, useState } from "react";
import { usePostQuery } from "../hooks/usePosts";

const NormalPage = () => {
//   const [isLoading, setIsLoading] = useState(false); // 로딩 상태
//   const [data, setData] = useState(null); // API에서 받은 데이터 저장

//   // 1. API 호출 함수
//   const fetchPost = async () => {
//     setIsLoading(true); // 로딩 시작

//     const url = "http://localhost:3004/posts"; // API 주소
//     const response = await fetch(url); // 데이터 요청
//     const data = await response.json(); // JSON 변환

//     setIsLoading(false); // 로딩 종료
//     setData(data); // 데이터 저장
//   };

//   // 2. 컴포넌트가 처음 렌더링 될 때 API 호출
//   useEffect(() => {
//     fetchPost();
//   }, []);

const {data, isLoading, error, isError} = usePostQuery();

  // 3. 로딩 중이면 로딩 문구 출력
  if (isLoading) {
    return <h1>Loading...</h1>;
  }

  // 4. 데이터 출력 (Optional chaining으로 null 방지)
  return (
    <div>
      {data?.map((item, index) => (
        <div key={index}>{item.title}</div>
      ))}
    </div>
  );
};

export default NormalPage;
