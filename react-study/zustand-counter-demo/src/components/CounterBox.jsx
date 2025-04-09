import React from 'react'
import counterStore from '../stores/CounterStore';

const CounterBox = () => {
    const { count } = counterStore();

  return (
    <h2>counterBox : {count} </h2>
  )
}

export default CounterBox