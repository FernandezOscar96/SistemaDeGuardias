document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar-container');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridWeek',
        selectable: true,
        selectHelper: true,
        events: [], // Lista de eventos inicial vacía
        select: function(info) {
            var maxPersonasPorDia = 3; // Límite de personas por día
            var eventosDelDia = calendar.getEvents().filter(event => event.startStr === info.startStr);
            
            if (eventosDelDia.length < maxPersonasPorDia) {
                var nombrePersona = prompt("Ingrese el nombre de la persona para la guardia:");
                if (nombrePersona) {
                    calendar.addEvent({
                        title: nombrePersona,
                        start: info.start,
                        end: info.end,
                        allDay: info.allDay
                    });
                }
            } else {
                alert('Guardia completa para este día.');
            }
        }
    });

    calendar.render();
});

var greenTimeout, redTimeout;

function toggleStatus(color) {
    var greenButton = document.getElementById('green-button');
    var redButton = document.getElementById('red-button');
    var inicioGuardia = document.getElementById('inicio-guardia');
    var finGuardia = document.getElementById('fin-guardia');
    var listaRegistro = document.getElementById('lista-registro');

    if (color === 'green') {
        if (!greenButton.disabled) {
            greenButton.classList.add('green');
            greenButton.classList.remove('white');
            redButton.classList.add('white');
            redButton.classList.remove('red');

            // Obtener la fecha y hora actual
            var now = new Date();
            var fecha = now.toLocaleDateString();
            var hora = now.toLocaleTimeString();
            
            // Mostrar "Inicio de guardia" con la hora y fecha actuales
            inicioGuardia.textContent = "Inicio de guardia: " + fecha + " " + hora;

            // Agregar registro de inicio de guardia
            var li = document.createElement("li");
            li.textContent = "Entrada: " + fecha + " " + hora;
            listaRegistro.appendChild(li);

            // Deshabilitar el botón verde y habilitar el botón rojo
            greenButton.disabled = true;
            redButton.disabled = false;

            // Cancelar el temporizador del botón rojo si existe y establecer un nuevo temporizador para el verde
            clearTimeout(redTimeout);
            greenTimeout = setTimeout(function() {
                greenButton.disabled = false;
            }, 300000); // 5 minutos
        }
    } else {
        if (!redButton.disabled) {
            redButton.classList.add('red');
            redButton.classList.remove('white');
            greenButton.classList.add('white');
            greenButton.classList.remove('green');

            // Obtener la fecha y hora actual
            var now = new Date();
            var fecha = now.toLocaleDateString();
            var hora = now.toLocaleTimeString();
            
            // Mostrar "Fin de guardia" con la hora y fecha actuales
            finGuardia.textContent = "Fin de guardia: " + fecha + " " + hora;

            // Agregar registro de fin de guardia
            var li = document.createElement("li");
            li.textContent = "Salida: " + fecha + " " + hora;
            listaRegistro.appendChild(li);

            // Deshabilitar el botón rojo y habilitar el botón verde
            redButton.disabled = true;
            greenButton.disabled = false;

            // Cancelar el temporizador del botón verde si existe y establecer un nuevo temporizador para el rojo
            clearTimeout(greenTimeout);
            redTimeout = setTimeout(function() {
                redButton.disabled = false;
            }, 300000); // 5 minutos
        }
    }
}

function goToUsers() {
    alert("probando");
    //window.location.href = '../pages/gestionUsuarios.html';
}

function goToProfile() {
    alert("Navegando al perfil...");
}

function logout() {
    window.location.href = '../index.html';
}