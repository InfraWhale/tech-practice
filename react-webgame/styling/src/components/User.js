import Button from "./Button.js";
import "./App.css";

const User = ({ user, handleDelete}) => {
    return (
    <div className="square-style">
        <div>{user.age}살 - </div>
        <div>{user.name}</div>
        <Button color="red" onClick={() => handleDelete(user.id)}>
        삭제하기
        </Button>
    </div>
    );
}

export default User;