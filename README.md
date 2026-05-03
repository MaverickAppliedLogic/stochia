# Stochia — Aplicación de cálculo estocástico

Stochia es una aplicación Android diseñada para facilitar el uso de herramientas probabilísticas sin necesidad de conocimientos avanzados de estadística. Permite realizar simulaciones Montecarlo, análisis de distribuciones y cadenas de Markov mediante una interfaz intuitiva, guiada y accesible para cualquier usuario.


---

## ✨ Características principales

### 🔢 Simulación Montecarlo
- Selección de distribuciones.
- Validación automática de parámetros.
- Resultados claros y visuales.

### 🔗 Cadenas de Markov
- Definición visual de matrices de transición.
- Validación inteligente (filas que suman 1, tamaños válidos, etc.).
- Resultados interpretables para usuarios no técnicos.

### 📊 Distribuciones probabilísticas
- Análisis de datos introducidos por el usuario.
- Cálculo de estadísticas relevantes.
- Visualización simplificada.

### 💾 Gestión de estudios
- Guardado de simulaciones.
- Reutilización de estudios previos.
- Carrusel de resultados recientes.

### ⚙️ Arquitectura híbrida de cálculo
- Motor local (Python + Chaquopy).
- Motor remoto (API en Oracle Cloud).
- Selección automática o manual del modo de uso.

---

## 🛠 Tecnologías utilizadas

### Frontend
- **Kotlin**
- **Jetpack Compose**
- **MVVM**
- **Koin** (inyección de dependencias)
- **KStore** (persistencia reactiva)

### Backend local
- **Python**
- **Chaquopy**
- Librerías científicas: NumPy, pandas

### Backend remoto
- **Oracle Cloud VM**
- **Docker**
- **Python API**


## 🚀 Flujo de uso

1. El usuario selecciona una herramienta (Montecarlo, Markov o Distribución).  
2. Introduce los parámetros mediante un formulario guiado.  
3. La app valida los datos.  
4. Se ejecuta el cálculo (local o remoto).  
5. Se muestran los resultados.  
6. El usuario puede guardar el estudio o reutilizar uno previo.

---

## 📦 Instalación y ejecución

### Requisitos
- Android Studio Flamingo o superior  
- SDK 24+  

### Pasos
1. Clonar el repositorio  
2. Abrir en Android Studio  
3. Sincronizar Gradle  
4. Ejecutar en un dispositivo o emulador  

---

## 👤 Autor

**Marcelo Da Silva Pérez**

---

## 📄 Licencia

