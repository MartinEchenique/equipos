# API de Equipos de Fútbol

API desarrollada con **Spring Boot 3** y **Java 17** para gestionar información sobre equipos de fútbol. Permite realizar operaciones CRUD, búsqueda por nombre, y autenticación JWT.

## Funcionalidades

- **Consultar todos los equipos**
- **Consultar un equipo por ID**
- **Buscar equipos por nombre**
- **Crear un nuevo equipo**
- **Actualizar un equipo existente**
- **Eliminar un equipo**
- **Autenticación con JWT**

## Dockerización

El proyecto está dockerizado. Para construir y ejecutar la imagen:

1. **Construir la imagen**:
   ```bash
   docker build -t football-teams-api .
   ```

2. **Ejecutar el contenedor**:
   ```bash
   docker run -p 8080:8080 football-teams-api
   ```

Accede a la API en `http://localhost:8080/swagger-ui/index.html#`.

## Base de Datos

La persistencia de datos se realiza en **H2 en memoria** para pruebas rápidas.
## Endpoints

1. **GET /equipos** - Consultar todos los equipos

**Respuesta (200 OK):**
```json
[
  {
    "id": 2,
    "nombre": "FC Barcelona",
    "liga": "La Liga",
    "pais": "España"
  }
]
```

2. **GET /equipos/{id}** - Consultar equipo por ID

**Respuesta (200 OK):**
```json
{
  "id": 2,
  "nombre": "FC Barcelona",
  "liga": "La Liga",
  "pais": "España"
}
```

**Respuesta (404 Not Found):**
```json
{
  "mensaje": "Equipo no encontrado",
  "codigo": 404
}
```

3. **GET /equipos/buscar?nombre={valor}** - Buscar equipos por nombre

**Respuesta (200 OK):**
```json
[
  {
    "id": 1,
    "nombre": "Real Madrid",
    "liga": "La Liga",
    "pais": "España"
  }
]
```

4. **POST /equipos** - Crear un nuevo equipo

**Cuerpo de la solicitud:**
```json
{
  "nombre": "Nuevo Equipo FC",
  "liga": "Nueva Liga",
  "pais": "Nuevo País"
}
```

**Respuesta (201 Created):**
```json
{
  "id": 26,
  "nombre": "Nuevo Equipo FC",
  "liga": "Nueva Liga",
  "pais": "Nuevo País"
}
```

5. **PUT /equipos/{id}** - Actualizar un equipo

**Cuerpo de la solicitud:**
```json
{
  "nombre": "Nuevo Nombre",
  "liga": "Nueva Liga",
  "pais": "Nuevo País"
}
```

**Respuesta (200 OK):**
```json
{
  "id": 1,
  "nombre": "Nuevo Nombre",
  "liga": "Nueva Liga",
  "pais": "Nuevo País"
}
```

6. **DELETE /equipos/{id}** - Eliminar un equipo

**Respuesta (204 No Content):** Sin contenido.

7. **POST /auth/login** - Autenticación JWT

**Cuerpo de la solicitud:**
```json
{
  "username": "test",
  "password": "12345"
}
```

**Respuesta (200 OK):**
```json
{
  "token": "<TOKEN>"
}
```

**Respuesta (401 Unauthorized):**
```json
{
  "mensaje": "Credenciales incorrectas",
  "codigo": 401
}
```


