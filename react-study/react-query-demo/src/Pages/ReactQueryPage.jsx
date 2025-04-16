import React from 'react'
// import { useQuery } from "@tanstack/react-query";
// import axios from 'axios';
import { usePostQuery } from '../hooks/usePosts';

const ReactQueryPage = () => {

    // const fetchPost = (queryData) => {
    //     console.log("📡 API 요청 발생!", queryData);
    //     const id = queryData.queryKey[1];
    //     return axios.get(`http://localhost:3004/posts/${id}`);
    // }

    // const {data, isLoading, isError, error, refetch} = useQuery({
    //     queryKey:['posts', 1],
    //     queryFn:fetchPost,
    //     retry: 1,
    //     select:(data)=> {
    //         return data.data;
    //     },
    //     // gcTime:5000, // ttl
    //     // staleTime:5000, // stale로 변하기까지의 시간
    //     // refetchInterval:3000, // 초 간격 자동 호출
    //     // refetchOnMount: false // false면 처음에 호출한 이후로 호출이 안됨
    //     // refetchOnWindowFocus: true // focus 다시 잡히면 다시 호출
    //     // enabled : false // false면 초기에 호출 안됨. 기본값 true
    // });

    // const {data, isLoading, error, isError, refetch} = usePostQuery(1);
    const {data, isLoading, error, isError, refetch} = usePostQuery();

    console.log("data ", data, isLoading);
    console.log("error", error, isError);

    if(isLoading) {
        return<h1>Loading...</h1>
    }
    if(isError){
        return <h1>{error.message}</h1>
    }

  return (
    <div>
        {data?.map((item) => (
            <div>{item.title}</div>
        ))}
        <button onClick={refetch}>post 리스트 다시 들고오기</button>
    </div>
  )
}

export default ReactQueryPage