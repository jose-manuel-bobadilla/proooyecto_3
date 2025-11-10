document.addEventListener('DOMContentLoaded', () => {
    const apiUrl = '/api/events'; // La URL base de la API de eventos

    const form = document.getElementById('event-form');
    const eventIdInput = document.getElementById('event-id');
    const nombreInput = document.getElementById('nombre');
    const fechaInput = document.getElementById('fecha');
    const lugarInput = document.getElementById('lugar');
    const precioBaseInput = document.getElementById('precioBase');
    const estadoInput = document.getElementById('estado');
    const eventsTableBody = document.querySelector('#events-table tbody');
    const btnClear = document.getElementById('btn-clear');

    // Función para obtener y mostrar todos los eventos
    const fetchEvents = async () => {
        try {
            const response = await fetch(apiUrl);
            if (!response.ok) throw new Error('Error al cargar los eventos.');
            const events = await response.json();

            eventsTableBody.innerHTML = ''; // Limpiar la tabla antes de llenarla
            events.forEach(event => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${event.id}</td>
                    <td>${event.nombre}</td>
                    <td>${event.fecha}</td>
                    <td>${event.lugar}</td>
                    <td>${event.precioBase.toFixed(2)}</td>
                    <td>${event.estado}</td>
                    <td>
                        <button class="action-btn edit-btn" data-id="${event.id}">✏️</button>
                        <button class="action-btn delete-btn" data-id="${event.id}">🗑️</button>
                    </td>
                `;
                eventsTableBody.appendChild(row);
            });
        } catch (error) {
            console.error('Error:', error);
            alert(error.message);
        }
    };

    // Manejar el envío del formulario (Crear o Actualizar)
    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const eventId = eventIdInput.value;
        const eventData = {
            nombre: nombreInput.value,
            fecha: fechaInput.value,
            lugar: lugarInput.value,
            precioBase: parseFloat(precioBaseInput.value),
            estado: estadoInput.value,
        };

        const method = eventId ? 'PUT' : 'POST';
        const url = eventId ? `${apiUrl}/${eventId}` : apiUrl;

        try {
            const response = await fetch(url, {
                method: method,
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(eventData),
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || 'Error al guardar el evento.');
            }

            clearForm();
            fetchEvents(); // Recargar la lista de eventos
            alert(`Evento ${eventId ? 'actualizado' : 'creado'} con éxito.`);
        } catch (error) {
            console.error('Error:', error);
            alert(error.message);
        }
    });

    // Manejar clics en los botones de la tabla (Editar y Eliminar)
    eventsTableBody.addEventListener('click', async (e) => {
        const target = e.target;
        const id = target.dataset.id;

        if (target.classList.contains('edit-btn')) {
            // Cargar datos del evento en el formulario para editar
            try {
                const response = await fetch(`${apiUrl}/${id}`);
                if (!response.ok) throw new Error('No se pudo cargar el evento para editar.');
                const event = await response.json();

                eventIdInput.value = event.id;
                nombreInput.value = event.nombre;
                fechaInput.value = event.fecha;
                lugarInput.value = event.lugar;
                precioBaseInput.value = event.precioBase;
                estadoInput.value = event.estado;
                window.scrollTo(0, 0); // Subir al inicio de la página
            } catch (error) {
                alert(error.message);
            }
        }

        if (target.classList.contains('delete-btn')) {
            if (confirm('¿Estás seguro de que quieres eliminar este evento?')) {
                try {
                    const response = await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
                    if (!response.ok) throw new Error('Error al eliminar el evento.');
                    fetchEvents(); // Recargar la lista
                    alert('Evento eliminado con éxito.');
                } catch (error) {
                    alert(error.message);
                }
            }
        }
    });

    // Limpiar el formulario
    const clearForm = () => {
        form.reset();
        eventIdInput.value = '';
    };

    btnClear.addEventListener('click', clearForm);

    // Carga inicial de eventos
    fetchEvents();
});