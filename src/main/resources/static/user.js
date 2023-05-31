const userUrl = 'http://localhost:8080/api/user';


function getUserPage() {
    fetch(userUrl).then(response => response.json()).then(user =>
        getInformationAboutUser(user))
}

function getInformationAboutUser(user) {
    const tableBody = document.getElementById('userTableBody');
    let res = '';
    res =
        `<tr>
    <td>${user.id}</td>
    <td>${user.name}</td>
    <td>${user.lastName}</td>
    <td>${user.age}</td>
    <td>${user.userName}</td>
    <td id=${'role' + user.id}>${user.role.map(r => r.role).join(' ')}</td>
</tr>`

    tableBody.innerHTML = res;
}

getUserPage();
