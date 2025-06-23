console.log("boardComment.js in");

// CSRF 토큰 처리
const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');

// 댓글 등록 버튼 이벤트
document.getElementById('cmtAddBtn').addEventListener('click', () => {
    const cmtText = document.getElementById('cmtText');
    const cmtWriter = document.querySelector('.input-group-text');

    if (cmtText.value.trim() === '') {
        alert("댓글을 입력해주세요.");
        cmtText.focus();
        return;
    }

    const cmtData = {
        bno: bnoValue,
        writer: cmtWriter.innerText,
        content: cmtText.value
    };

    postCommentToServer(cmtData).then(result => {
        if (result === '1') {
            alert("댓글 등록 성공!");
            cmtText.value = "";
            spreadCommentList(cmtData.bno);
        }
    });
});

// 댓글 목록 출력
function spreadCommentList(bno, page = 1) {
    getCommentListFromServer(bno, page).then(result => {
        const ul = document.getElementById('cmtListArea');

        if (result.cmtList.length > 0) {
            if (page === 1) ul.innerHTML = '';

            result.cmtList.forEach(cvo => {
                let li = `<li class="list-group-item" data-cno="${cvo.cno}">`;
                li += `<div class="mb-3">`;
                li += `<div class="fw-bold">${cvo.writer}</div>`;
                li += `${cvo.content}`;
                li += `</div>`;
                li += `<span class="badge text-bg-primary">${cvo.regDate}</span>`;
                if (cvo.writer === loginNick) {
                    li += `<button type="button" class="btn btn-outline-warning btn-sm mod">%</button>`;
                    li += `<button type="button" class="btn btn-outline-danger btn-sm del">X</button>`;
                }
                li += `</li>`;
                ul.innerHTML += li;
            });

            const moreBtn = document.getElementById('moreBtn');
            if (result.pgvo.pageNo < result.realEndPage) {
                moreBtn.style.visibility = "visible";
                moreBtn.dataset.page = page + 1;
            } else {
                moreBtn.style.visibility = "hidden";
            }
        } else {
            ul.innerHTML = `<li class="list-group-item">Comment List Empty</li>`;
        }
    });
}

// 댓글 수정 & 삭제 이벤트 핸들러
document.addEventListener('click', (e) => {
    // 더보기
    if (e.target.id === 'moreBtn') {
        const page = parseInt(e.target.dataset.page);
        spreadCommentList(bnoValue, page);
    }

    // 댓글 수정 버튼
    if (e.target.classList.contains('mod')) {
        const li = e.target.closest('li');
        const cno = li.dataset.cno;
        const content = li.querySelector('.mb-3').childNodes[1].textContent.trim();
        const writer = li.querySelector('.fw-bold').innerText;

        document.getElementById('cmtTextMod').value = content;
        document.getElementById('exampleModalLabel').innerHTML = `no.${cno} <b>${writer}</b>`;
        document.getElementById('cmtModBtn').setAttribute("data-cno", cno);

        const myModal = new bootstrap.Modal(document.getElementById('myModal'));
        myModal.show();
    }

    // 댓글 수정 확인 버튼
    if (e.target.id === 'cmtModBtn') {
        const cmtData = {
            cno: e.target.dataset.cno,
            content: document.getElementById('cmtTextMod').value
        };

        updateCommentToServer(cmtData).then(result => {
            if (result === '1') {
                console.log("댓글 수정 성공!");
                spreadCommentList(bnoValue);
                const closeBtn = document.querySelector('.btn-close');
                if (closeBtn) closeBtn.click();
            } else {
                alert("댓글 수정 실패!");
            }
        });
    }

    // 댓글 삭제
    if (e.target.classList.contains('del')) {
        const li = e.target.closest('li');
        const cno = li.dataset.cno;

        removeCommentToServer(cno).then(result => {
            if (result === '1') {
                console.log("댓글 삭제 성공");
                spreadCommentList(bnoValue);
            } else {
                alert("댓글 삭제 실패");
            }
        });
    }
});


// 서버 통신 함수

// 댓글 등록
async function postCommentToServer(cmtData) {
    try {
        const url = "/comment/post";
        const config = {
            method: "post",
            headers: {
                'Content-Type': 'application/json; charset=utf-8',
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        return await resp.text();
    } catch (err) {
        console.error(err);
    }
}

// 댓글 목록 요청
async function getCommentListFromServer(bno, page) {
    try {
        const resp = await fetch(`/comment/${bno}/${page}`);
        return await resp.json();
    } catch (err) {
        console.error(err);
    }
}

// 댓글 수정
async function updateCommentToServer(cmtData) {
    try {
        const url = "/comment/modify";
        const config = {
            method: "put",
            headers: {
                'Content-Type': 'application/json; charset=utf-8',
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        return await resp.text();
    } catch (err) {
        console.error(err);
    }
}

// 댓글 삭제
async function removeCommentToServer(cno) {
    try {
        const url = `/comment/${cno}`;
        const config = {
            method: "delete",
            headers: {
                [csrfHeader]: csrfToken
            }
        };
        const resp = await fetch(url, config);
        return await resp.text();
    } catch (err) {
        console.error(err);
    }
}
