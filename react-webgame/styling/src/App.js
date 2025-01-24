import GlobalStyle from "./GlobalStyle";
import BlogPost from "./BlogPost";

function App() {
	const title = '전역 스타일링 제목입니다.';
	const contents = '전역 스타일링 내용입니다.';
  return (
    <>
      <GlobalStyle />
      <BlogPost title={title} contents={contents} />
    </>
  );
}

export default App;