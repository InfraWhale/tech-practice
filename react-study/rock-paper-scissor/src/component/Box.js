import React from 'react'

const defalultImg = "https://r2.jjalbot.com/2023/03/rPJFWk6G1x.webp";

const Box = (props) => {
    let className;
    switch(props.result) {
        case "win" :
            className = "box win-box";
            break;
        case "lose" :
            className = "box lose-box";
            break;
        case "tie" :
            className = "box tie-box";
            break;
        default :
            className = "box";
    }

  return (
    <div className = {className}>
        <h1>{props.title}</h1>
        <img className="item-img" src={props.item? props.item.img : defalultImg} />
        <h2>{props.result}</h2>
    </div>
  )
}

export default Box