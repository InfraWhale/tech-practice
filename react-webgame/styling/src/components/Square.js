import React from "react";

function Square({ user }) {
  return (
    <div>
      <p>{user.name}</p>
      <p>{user.age}</p>
    </div>
  );
}

export default Square;