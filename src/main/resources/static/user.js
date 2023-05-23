// const userUrl = 'http://localhost:8080/api/user';
//
// // fetch(userUrl).then(response => response.json())
//     // .then(user => {
//     //         let roles = ''
//     //         // user.role.forEach(r => {
//     //         //     roles += ' '
//     //         //     roles += r.name
//     //         // })
//     //         document.getElementById("navbar-email").innerHTML = user.userName
//     //         document.getElementById("navbar-roles").innerHTML = roles
//     //     }
//     // )
//
// function getUserPage() {
//     fetch(userUrl).then(response => response.json()).then(user =>
//         getInformationAboutUser(user))
// }
//
// function getInformationAboutUser(user) {
//     const tableBody = document.getElementById('userTableBody');
//     let res = '';
//     let roles = [];
//
//     res =
//         `<tr>
//     <td>${user.id}</td>
//     <td>${user.name}</td>
//     <td>${user.lastName}</td>
//     <td>${user.age}</td>
//     <td>${user.userName}</td>
//     <td>${roles}</td>
// </tr>`
//
//     tableBody.innerHTML = res;
// }
//
// getUserPage();
