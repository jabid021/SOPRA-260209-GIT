
// ============================================
// MÉTÉO SIMULÉE
// Pour utiliser la vraie API : remplace fetchFakeWeather par fetchRealWeather
// et mets ta clé dans API_KEY
// ============================================

const API_KEY = 'd65c98bdd7ddf5947eeb5b9c36669bf6';

const circuits = [
    {
        id: 'weather-monaco',
        city: 'Monaco',
        lat: 43.734, lon: 7.421,
        // Données simulées réalistes
        fake: { temp: 18, feels: 17, desc: 'Partiellement nuageux', icon: '⛅', humidity: 72, wind: 12 }
    },
    {
        id: 'weather-silverstone',
        city: 'Silverstone',
        lat: 52.068, lon: -1.017,
        fake: { temp: 11, feels: 9, desc: 'Pluie légère', icon: '🌧️', humidity: 88, wind: 22 }
    },
    {
        id: 'weather-monza',
        city: 'Monza',
        lat: 45.617, lon: 9.281,
        fake: { temp: 21, feels: 20, desc: 'Ensoleillé', icon: '☀️', humidity: 55, wind: 8 }
    },
    {
        id: 'weather-spa',
        city: 'Spa',
        lat: 50.437, lon: 5.971,
        fake: { temp: 9, feels: 6, desc: 'Brouillard', icon: '🌫️', humidity: 95, wind: 5 }
    }
];

function renderWeather(elementId, data) {
    const el = document.getElementById(elementId);
    if (!el) return;
    el.innerHTML = `
        <span class="weather-icon">${data.icon}</span>
        <div class="weather-info">
            <div class="weather-temp">${data.temp}°C <span style="font-size:0.7rem;color:var(--muted)">Ressenti ${data.feels}°C</span></div>
            <div class="weather-desc">${data.desc}</div>
        </div>
        <div class="weather-extra">
            💧 ${data.humidity}%<br>
            💨 ${data.wind} km/h
        </div>
    `;
}

async function fetchRealWeather(circuit) {
    try {
        const url = `https://api.openweathermap.org/data/2.5/weather?lat=${circuit.lat}&lon=${circuit.lon}&appid=${API_KEY}&units=metric&lang=fr`;
        const res = await fetch(url);
        if (!res.ok) throw new Error('API error');
        const json = await res.json();

        const iconMap = {
            '01': '☀️', '02': '⛅', '03': '☁️', '04': '☁️',
            '09': '🌧️', '10': '🌦️', '11': '⛈️', '13': '❄️', '50': '🌫️'
        };
        const iconCode = json.weather[0].icon.slice(0, 2);

        renderWeather(circuit.id, {
            temp: Math.round(json.main.temp),
            feels: Math.round(json.main.feels_like),
            desc: json.weather[0].description,
            icon: iconMap[iconCode] || '🌡️',
            humidity: json.main.humidity,
            wind: Math.round(json.wind.speed * 3.6)
        });
    } catch (e) {
        // Fallback sur les données simulées si l'API plante
        fetchFakeWeather(circuit);
    }
}

function fetchFakeWeather(circuit) {
    // Simule un délai réseau réaliste
    setTimeout(() => {
        renderWeather(circuit.id, circuit.fake);
    }, 400 + Math.random() * 800);
}

// Lance la météo au chargement
window.addEventListener('DOMContentLoaded', () => {
    circuits.forEach(circuit => {
        // Change fetchFakeWeather → fetchRealWeather quand tu as ta vraie clé
        fetchRealWeather(circuit);
    });
});
