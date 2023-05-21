const userUrl = '/api/user';

const userAuth = fetch(userUrl).then(response => response.json())
userAuth.then(user => {
        let roles = ''
        user.role.forEach(r => {
            roles += ' '
            roles += r.name
        })
        document.getElementById("navbar-email").innerHTML = user.userName
        document.getElementById("navbar-roles").innerHTML = roles
    }
)

async function getUserPage() {
    let page = await fetch(userUrl);
    if (page.ok) {
        let user = await page.json();
        getInformationAboutUser(user);
    } else {
        alert(`Error, ${page.status}`)
    }
}

function getInformationAboutUser(user) {
    const tableBody = document.getElementById('userTableBody');
    let res = '';
    let roles = [];

    res =
        `<tr>
    <td>${user.id}</td>
    <td>${user.name}</td>
    <td>${user.lastName}</td>
    <td>${user.age}</td>
    <td>${user.userName}</td>
    <td>${roles}</td>   
</tr>`

    tableBody.innerHTML = res;
}

getUserPage();
