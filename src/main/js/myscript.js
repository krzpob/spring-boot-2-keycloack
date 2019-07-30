var keycloak = Keycloak();
keycloak.init({ onLoad: 'login-required' }).success(function(authenticated) {
    alert(authenticated ? 'authenticated' : 'not authenticated');
}).error(function() {
    alert('failed to initialize');
});

var loadData = function () {
    document.getElementById('username').innerText = keycloak.subject;

    var url = 'http://localhost:8081/user';

    var req = new XMLHttpRequest();
    req.open('GET', url, true);
    req.withCredentials=true;
    req.setRequestHeader('Accept', 'application/json');
    req.setRequestHeader('Authorization', 'Bearer ' + keycloak.token);

    req.onreadystatechange = function () {
        if (req.readyState == 4) {
            if (req.status == 200) {
                alert('Success');
                alert(req.response);
                console.log('Success');
                console.log(req.response);
            } else if (req.status == 403) {
                alert('Forbidden');
            }
        }
    };

    req.send();
};

function click () {
    alert("Klick");
    keycloak.updateToken(30).success(function() {
        alert("Test");
        loadData();
    }).error(function() {
        alert('Failed to refresh token');
    });
}