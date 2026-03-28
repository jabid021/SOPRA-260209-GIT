// Feux d'artifice
const container = document.getElementById('fireworks-container');
const fireworks = new Fireworks.default(container, {
  rocketsPoint: { min: 50, max: 50 },
  speed: 3,
  acceleration: 1.05,
  friction: 0.95,
  gravity: 1.5,
  particles: 50,
  explosion: 5,
  intensity: 30,
  traceLength: 3,
  traceSpeed: 10,
  hue: { min: 0, max: 360 },
  delay: { min: 30, max: 60 }
});

fireworks.start();

// Zoom des images
const overlay = document.getElementById('overlay');
const overlayImg = overlay.querySelector('img');

// Ajouter un événement clic sur toutes les images
document.querySelectorAll('.zoomable').forEach(img => {
  img.addEventListener('click', () => {
    overlay.style.display = 'flex';
    overlayImg.src = img.src;
  });
});

// Fermer l'overlay au clic
overlay.addEventListener('click', () => {
  overlay.style.display = 'none';
  overlayImg.src = '';
});

// Jauges interactives
document.querySelectorAll('.distance-cell').forEach(cell => {
  const bar = cell.querySelector('.jauge-bar');
  const fullWidth = bar.getAttribute('data-width');

  // Remplir la jauge au survol
  cell.addEventListener('mouseenter', () => {
    bar.style.width = fullWidth;
  });

  // Revenir à zéro quand on sort
  cell.addEventListener('mouseleave', () => {
    bar.style.width = '0%';
  });

  // Cliquer pour "fixer" la jauge
  cell.addEventListener('click', () => {
    bar.style.width = fullWidth;
  });
});

// Musique de fond
const music = document.getElementById('bg-music');
music.volume = 0.2;
const toggleBtn = document.getElementById('toggle-music');

toggleBtn.addEventListener('click', async () => {
  try {
    if (music.paused) {
      await music.play();  // Joue la musique après le clic
      toggleBtn.textContent = '🔊 Musique ON';
    } else {
      music.pause();
      toggleBtn.textContent = '🔇 Musique OFF';
    }
  } catch(err) {
    console.log('Impossible de jouer le son :', err);
  }
});
