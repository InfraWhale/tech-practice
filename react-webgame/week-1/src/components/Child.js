// components/Child.js

import React from 'react';

function Child({ name }){
	return <div>내 이름은 {name} 입니다. </div>
}

// 이렇게 설정합니다.
Child.defaultProps={
	name: '기본 이름'
}

export default Child