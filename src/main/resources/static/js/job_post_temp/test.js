var allCookies = document.cookie; // 현재 페이지에 설정된 모든 쿠키를 가져옵니다.

// 원하는 쿠키 이름으로 값을 추출합니다.
function getCookie(name) {
    var cookies = document.cookie.split(';');
    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i].trim();
        if (cookie.indexOf(name + '=') === 0) {
            return cookie.substring(name.length + 1, cookie.length);
        }
    }
    return null; // 해당 쿠키가 없을 경우
}

var myCookieValue = getCookie('myTokenCookie');
console.log("myCookieValue"+myCookieValue);