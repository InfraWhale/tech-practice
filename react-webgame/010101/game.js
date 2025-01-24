const canvas = document.getElementById('gameCanvas');
const ctx = canvas.getContext('2d');

// 초기 블록의 위치와 속도 설정
const block = {
    x: 300,
    y: canvas.height - 120,
    width: 40,
    height: 20,
    falling: false,
    vy: 2 // 떨어질 때 속도
};

function drawGround() {
    ctx.beginPath();
    ctx.moveTo(0, canvas.height - 20);
    ctx.lineTo(canvas.width, canvas.height - 20);
    ctx.strokeStyle = "green";
    ctx.lineWidth = 5;
    ctx.stroke();
}

function drawBlock() {
    if (block.falling) {
        block.y += block.vy;
        if (block.y > canvas.height) {
            block.y = canvas.height + block.height;
        }
    }
    ctx.fillStyle = "blue";
    ctx.fillRect(block.x, block.y, block.width, block.height);
}

function drawProjectile(x, y) {
    ctx.clearRect(0, 0, canvas.width, canvas.height); // 화면 지우기
    drawGround();
    drawBlock();

    // 포탄 그리기
    ctx.beginPath();
    ctx.arc(x, y, 5, 0, Math.PI * 2);
    ctx.fillStyle = "red";
    ctx.fill();
}

function fire() {
    const angle = parseFloat(document.getElementById('angle').value);
    const power = parseFloat(document.getElementById('power').value);

    const radians = (Math.PI / 180) * angle;
    let x = 50;
    let y = canvas.height - 30;
    let vx = Math.cos(radians) * power * 0.5;
    let vy = -Math.sin(radians) * power * 0.5;
    const gravity = 0.1;

    function animate() {
        vy += gravity;
        x += vx;
        y += vy;

        if (!block.falling && x > block.x && x < block.x + block.width && y > block.y && y < block.y + block.height) {
            block.falling = true;
        }

        if (x < canvas.width && y < canvas.height - 20) {
            drawProjectile(x, y);
            requestAnimationFrame(animate);
        } else {
            drawProjectile(x, canvas.height - 20);
        }
    }

    animate();
}

// 페이지 로드 시 초기 요소를 그려주는 함수
function initializeGame() {
    drawGround();
    drawBlock();
}

// 페이지 로드 시 자동으로 initializeGame() 실행
initializeGame();