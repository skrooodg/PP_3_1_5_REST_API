const url = 'http://localhost:8080/admin';
const currentUser = fetch(url).then(response => response.json())

// const addNewUserForm = document.getElementById('NewUserForm');
// const role_new = document.querySelector('#roles').selectedOptions;


currentUser.then(user => {
        let roles = ''
        user.role.forEach(r => {
            roles += ' '
            roles += r.role
        })
        document.getElementById("navbar-email").innerHTML = user.userName
        document.getElementById("navbar-roles").innerHTML = roles
    }
)
function getAllUsers() {
    fetch(url)
        .then(res => res.json())
        .then(data => {
            loadTable(data)
        })
}

async function getAdminPage() {
    let page = await fetch(url);
    if(page.ok) {
        let listAllUsers = await  page.json();
        loadTable(listAllUsers);
    } else {
        alert(`Error, ${page.status}`)
    }
}
function loadTable(listAllUsers) {
    const tableBody = document.getElementById('tableBodyAdmin');
    let res = '';
    for (let user of listAllUsers) {
        let roles = [];

        res +=
            `<tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
                <td>${user.userName}</td>
                <td>${roles}</td>
                <td>
                    <button type="button" class="btn btn-primary" data-bs-toogle="modal"
                    data-bs-target="#editModal" 
                        >Edit</button>
                </td>
                <td>
                    <button class="btn btn-danger" data-bs-toogle="modal"
                    data-bs-target="#deleteModal" 
                        >Delete</button>
                </td>
   
            </tr>`
    }
    tableBody.innerHTML = res;
}
getAdminPage();
getUserPage();



// Добавление пользователя
document.getElementById('newUserForm').addEventListener('submit', (e) => {
    e.preventDefault()
    let role = document.getElementById('newRoles')
    let rolesAddUser = []
    let rolesAddUserValue = ''
    for (let i = 0; i < role.options.length; i++) {
        if (role.options[i].selected) {
            rolesAddUser.push({id: role.options[i].value, name: role.options[i].innerHTML})
            rolesAddUserValue += role.options[i].innerHTML + ' '
        }
    }
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            username: document.getElementById('newName').value,
            lastname: document.getElementById('newLastName').value,
            age: document.getElementById('newAge').value,
            email: document.getElementById('newUserName').value,
            password: document.getElementById('newPassword').value,
            roles: rolesAddUser
        })
    })
        .then(response => response.json())
        .then(user => {
            let newRow = document.createElement('tr')
            newRow.innerHTML = `<tr>
                           <td>${user.id}</td>
                           <td>${user.name}</td>
                           <td>${user.lastName}</td>
                           <td>${user.age}</td>
                           <td>${user.userName}</td>
                           <td>${user.password}</td>
                           <td>
                           <button class="btn btn-info" type="button"
                           data-bs-toggle="modal" data-bs-target="#modalEdit"
                           onclick="editModal(${user.id})">Добавить</button></td>
                           <td>
                           <button class="btn btn-danger" type="button"
                           data-bs-toggle="modal" data-bs-target="#modalDelete"
                           onclick="deleteModal(${user.id})">Удалить</button></td>
            </tr>`
            document.getElementById("tableBodyAdmin").append(newRow)
            document.getElementById('newUserForm').reset()

        })
    document.getElementById("profile-tab").click()
})

function getRoles(rols) {
    let roles = [];
    if (rols.indexOf("ADMIN") >= 0) {
        roles.push({
            "id": 1,
            "name": "ROLE_ADMIN",
            "users": null,
            "authority": "ROLE_ADMIN"
        });
    }
    if (rols.indexOf("USER") >= 0) {
        roles.push({
            "id": 2,
            "name": "ROLE_USER",
            "users": null,
            "authority": "ROLE_USER"
        });
    }
    return roles;
}

//Редактирование пользователя
function editModal(id) {
    fetch(url + '/' + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(u => {

            document.getElementById('editId').value = u.id;
            document.getElementById('editName').value = u.name;
            document.getElementById('editLastName').value = u.lastName;
            document.getElementById('editAge').value = u.age;
            document.getElementById('editEmail').value = u.userName;
            document.getElementById('editPassword').value = u.password;

        })
    });
}

async function editUser() {
    let idValue = document.getElementById("editId").value;
    let nameValue = document.getElementById("editName").value;
    let lastNameValue = document.getElementById("editLastName").value;
    let ageValue = document.getElementById('editAge').value;
    let emailValue = document.getElementById("editEmail").value;
    let passwordValue = document.getElementById("editPassword").value;
    let roles = getRoles(Array.from(document.getElementById("editRole").selectedOptions).map(role => role.value));

    let user = {
        id: idValue,
        name: nameValue,
        lastName: lastNameValue,
        age: ageValue,
        userName: emailValue,
        password: passwordValue,
        roles: roles
    }

    await fetch(url, {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(user)
    });
    getAllUsers()
}


// Удаление пользователя
function deleteModal(id) {
    fetch(url + '/' + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(u => {
            document.getElementById('deleteId').value = u.id;
            document.getElementById('deleteName').value = u.name;
            document.getElementById('deleteLastName').value = u.lastName;
            document.getElementById('deleteAge').value = u.age;
            document.getElementById('deleteUserName').value = u.userName;
        })
    });
}

async function deleteUser() {
    await fetch(url + '/' + document.getElementById('idDeleteUser').value, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(document.getElementById('deleteModal').value)
    })

    getAllUsers()
    document.getElementById("deleteBtn").click();
}



