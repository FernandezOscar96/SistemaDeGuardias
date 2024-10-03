    function goToHome() {
        console.log("Navegando a Inicio...");
        window.location.href = '../../pages/inicio.html';
    }
    
    function goToUsers() {
    window.location.href = '../pages/personal/gestionUsuarios.html';
    }

    function goToPerfile() {
        window.location.href = 'perfil.html';
    }

    function goToServices(){
        window.location.href = '../pages/servicios/gestionServicios.html';
    }

    function logout() {
            fetch('../LogoutServlet', {
                method: 'POST'
            })
            .then(response => {
                if (response.ok) {
                    window.location.href = '../index.html';
                } else {
                    console.error('Error al cerrar sesión');
                }
            })
            .catch(error => {
                console.error('Error en la solicitud:', error);
            });
        }
